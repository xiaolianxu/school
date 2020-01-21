package com.school.dao;

import com.school.model.college;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

@MapperScan
public interface collegeDao {
    List<college> getList() throws Exception;
}
