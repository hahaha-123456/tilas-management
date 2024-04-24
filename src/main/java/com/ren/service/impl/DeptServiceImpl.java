package com.ren.service.impl;

import com.ren.mapper.DeptMapper;
import com.ren.pojo.Dept;
import com.ren.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

//部门业务实现类
@Slf4j
@Service
public class DeptServiceImpl implements DeptService
{
    @Resource
    private DeptMapper deptMapper;
    @Override
    public List<Dept> list()
    {
        List<Dept> deptList=deptMapper.list();
        return deptList;
    }
    @Override
    public void delete(Integer id)
    {
        deptMapper.deleteById(id);
    }
    @Override
    public void add(Dept dept)
    {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.Insert(dept);
    }
}