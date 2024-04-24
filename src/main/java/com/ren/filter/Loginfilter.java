package com.ren.filter;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import com.ren.pojo.Result;
import com.ren.utils.JwtUtils;
import io.jsonwebtoken.JwtParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
/*@WebFilter(urlPatterns = "/*")*/
public class Loginfilter implements Filter
{

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request1=(HttpServletRequest) request;
        HttpServletResponse response1=(HttpServletResponse) response;

        String url=request1.getRequestURI().toString();
        log.info("请求路径：{}",url);

        if(url.contains("/login"))
        {
            chain.doFilter(request1,response1);
            return;
        }

        String token=request1.getHeader("token");
        log.info("获得请求头信息:{}",token);

        if(!StringUtils.hasLength(token))
        {
            log.info("token不存在");
            Result result = Result.error("NOT_LOGIN");

            String json = JSONObject.toJSONString(result);
            response.setContentType("application/json;charset=utf-8");

            response.getWriter().write(json);
            return;
        }

        try {
            JwtUtils.parseJWT(token);
        } catch (Exception e) {
            log.info("解析失败");
            Result result = Result.error("NOT_LOGIN");

            String json = JSONObject.toJSONString(result);
            response.setContentType("application/json;charset=utf-8");

            response.getWriter().write(json);
            return;
        }
        chain.doFilter(request1,response1);
    }
}
