package com.ren.controller;

import com.ren.anno.Log;
import com.ren.pojo.Dept;
import com.ren.pojo.Result;
import com.ren.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

//部门管理控制器
@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController
{
    @Resource
    private DeptService deptService;
    /*private static Logger log= LoggerFactory.getLogger(DeptController.class);*/
    @GetMapping
    public Result list()
    {
        log.info("查询全部部门数据");
        List<Dept> deptList = deptService.list();
        return Result.success(deptList);
    }
    @Log
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id)
    {
        log.info("根据id删除部门");
        deptService.delete(id);
        return Result.success();
    }
    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept)
    {
        log.info("新增部门：{}",dept);
        deptService.add(dept);
        return Result.success();
    }

}