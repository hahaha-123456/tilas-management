package com.ren.mapper;

import com.ren.pojo.Dept;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DeptMapper
{
    @Delete("Delete from dept where id=#{id}")
    void deleteById(Integer id);

    @Select("Select * from Dept")
    List<Dept> list();

    @Insert("Insert into dept (name, create_time, update_time) values (#{name},#{createTime},#{updateTime})")
    void Insert(Dept dept);
}