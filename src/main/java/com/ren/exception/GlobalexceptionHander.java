package com.ren.exception;

import com.ren.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalexceptionHander
{
    @ExceptionHandler(Exception.class)
    public Result e(Exception e)
    {
        e.printStackTrace();
        return Result.error("对不起，操作失败，请联系管理员");
    }

}
