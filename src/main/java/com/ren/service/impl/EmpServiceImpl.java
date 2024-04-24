package com.ren.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ren.mapper.EmpMapper;
import com.ren.pojo.Emp;
import com.ren.pojo.PageBean;
import com.ren.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

//员工业务实现类
@Slf4j
@Service
public class EmpServiceImpl implements EmpService
{
    @Resource
    private EmpMapper empMapper;
    @Override
    public PageBean page(Integer page, Integer pageSize, String name, Short
            gender, LocalDate begin, LocalDate end)
    {
        /*Long count = empMapper.count();
        Integer start = (page - 1) * pageSize;
        List<Emp> empList=empMapper.list(start,pageSize);*/
        PageHelper.startPage(page,pageSize);

        List<Emp> emplist = empMapper.list(name,gender,begin,end);
        Page<Emp> p=(Page<Emp>) emplist;
        PageBean pageBean=new PageBean(p.getTotal(),p.getResult());
        return pageBean;
    }

    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }

    @Override
    public void save(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
    }

    @Override
    public Emp GetByid(Integer id) {
        return empMapper.Byid(id);
    }

    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
    }

    @Override
    public Emp login(Emp emp)
    {
        Emp Emplogin=empMapper.getbymsg(emp);
        return Emplogin;
    }

}