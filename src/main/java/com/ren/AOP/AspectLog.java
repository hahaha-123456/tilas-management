package com.ren.AOP;

import com.alibaba.fastjson.JSONObject;
import com.ren.mapper.OperateLogMapper;
import com.ren.pojo.OperateLog;
import com.ren.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Component
@Aspect
public class AspectLog
{
    @Resource
    private HttpServletRequest request;

    @Resource
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(com.ren.anno.Log)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {

        String jwt=request.getHeader("token");
        Claims claims =JwtUtils.parseJWT(jwt);
        Integer operateUser=(Integer)claims.get("id");

        LocalDateTime operateTime = LocalDateTime.now();
        String className=joinPoint.getTarget().getClass().getName();
        String methodName=joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        String methodParams= Arrays.toString(args);

        Long begin=System.currentTimeMillis();
        Object result=joinPoint.proceed();
        Long end=System.currentTimeMillis();
        String returnValue = JSONObject.toJSONString(result);
        Long costTime=end-begin;

        OperateLog operateLog=new OperateLog(null,operateUser,operateTime,className,
                methodName,methodParams,returnValue,costTime);
        operateLogMapper.insert(operateLog);
        log.info("AOP记录操作日志：{}",operateLog);

        return result;
    }
}
