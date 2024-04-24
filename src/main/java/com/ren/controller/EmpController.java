package com.ren.controller;

import com.ren.anno.Log;
import com.ren.pojo.Emp;
import com.ren.pojo.PageBean;
import com.ren.pojo.Result;
import com.ren.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

//员工管理控制器
@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController
{
    @Resource
    private EmpService empService;

    @GetMapping
    public Result page(@RequestParam (defaultValue ="1")Integer page,
                       @RequestParam (defaultValue ="10")Integer pageSize,
    String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd")  LocalDate end)
    {

            log.info("分页查询,参数:{},{},{},{},{},{}",page,pageSize,name, gender, begin, end);
        PageBean pageBean=empService.page(page,pageSize,name, gender, begin, end);
        return Result.success(pageBean);
    }
    @Log
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids)
    {
        empService.delete(ids);
        return Result.success();
    }
    @PostMapping
    public Result save(@RequestBody Emp emp)
    {
        log.info("新增员工，emp:{}",emp);
        empService.save(emp);
        return Result.success();
    }
    @GetMapping("/{id}")
    public Result GetByid(@PathVariable Integer id)
    {
        Emp emp=empService.GetByid(id);
        return Result.success(emp);
    }
    @Log
    @PutMapping
    public Result update(@RequestBody Emp emp)
    {
        empService.update(emp);
        return Result.success();
    }
}
