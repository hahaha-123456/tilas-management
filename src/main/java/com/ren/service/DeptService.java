package com.ren.service;

import com.ren.pojo.Dept;

import java.util.List;

//部门业务规则
public interface DeptService
{
    void delete(Integer id);

    /*查询所有部门数据*/
    List<Dept> list();

    void add(Dept dept);
}