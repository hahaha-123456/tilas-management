package com.ren.service;

import com.ren.pojo.Emp;
import com.ren.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;

//员工业务规则
public interface EmpService {
    PageBean page(Integer page, Integer pageSize, String name, Short
            gender, LocalDate begin, LocalDate end);

    void delete(List<Integer> ids);

    void save(Emp enp);

    public Emp GetByid(Integer id);

    void update(Emp emp);

    Emp login(Emp emp);
}
