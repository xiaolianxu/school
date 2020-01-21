package com.school.dao;
import com.alibaba.fastjson.JSONObject;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;
import java.util.Map;

@MapperScan
public interface teacherDao {
    int updateImg(JSONObject params) throws Exception;
    String getImage(int p) throws Exception;
    Map getTeacherInfo(int id) throws Exception;
    int updateInfo(JSONObject params) throws Exception;
    List<JSONObject> getStudentScore(JSONObject params) throws Exception;
    List<JSONObject> getStudentScoreT(JSONObject params) throws Exception;
    Map getSSAna(JSONObject params) throws Exception;
    List<Map> getSSAna1(JSONObject params) throws Exception;
}
