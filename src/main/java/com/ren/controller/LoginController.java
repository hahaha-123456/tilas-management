package com.ren.controller;

import com.ren.pojo.Emp;
import com.ren.pojo.Result;
import com.ren.service.EmpService;
import com.ren.utils.JwtUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController
{
    @Resource
    private EmpService empService;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp)
    {
        Emp e = empService.login(emp);

        //判断：登录用户是否存在
        if(e !=null )
        {
//自定义信息
            Map<String , Object> claims = new HashMap<>();
            claims.put("id", e.getId());
            claims.put("username",e.getUsername());
            claims.put("name",e.getName());
//使用JWT工具类，生成身份令牌
            String token = JwtUtils.generateJwt(claims);
            return Result.success(token);
        }
        return Result.error("用户名或密码错误，请重新输入");
    }
}
