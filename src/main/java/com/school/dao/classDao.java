package com.school.dao;

import com.alibaba.fastjson.JSONObject;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

@MapperScan
public interface classDao {
    List<JSONObject> getClassList(JSONObject params) throws Exception;
    List<JSONObject> getClassSp(JSONObject params) throws Exception;
    List<JSONObject> getClassByC(JSONObject params) throws Exception;
    List<JSONObject> getClassBy(int id) throws Exception;
    int updateClass(JSONObject params) throws Exception;
}
