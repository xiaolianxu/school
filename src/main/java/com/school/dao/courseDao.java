package com.school.dao;

import com.alibaba.fastjson.JSONObject;
import com.school.model.courseInfo;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;
import java.util.Map;

@MapperScan
public interface courseDao {
    List<JSONObject> getCourse(JSONObject params) throws Exception;
    List<JSONObject> getCourseT(JSONObject params) throws Exception;
    List<JSONObject> getCourseR(int id) throws Exception;
    List<JSONObject> getCourseSp(JSONObject params) throws Exception;
    List<JSONObject> getCourseC(JSONObject params) throws Exception;
    List<JSONObject> getCourseSpT(JSONObject params) throws Exception;
    List<courseInfo> getCourseInfo(String code) throws Exception;
    List getCollege() throws Exception;
    List<Map> getZx() throws Exception;
    List<Map> getB() throws Exception;
    List<Map> getAllCourse() throws Exception;
    List<Map> getTeacher() throws Exception;
    List<Map> getCourseBj(JSONObject params) throws Exception;
    Map getScoreBj(JSONObject params) throws Exception;
    Map getScoreBj1(JSONObject params) throws Exception;
    Map getScoreBjOne(JSONObject params) throws Exception;
    Map getScoreBj1One(JSONObject params) throws Exception;
    List<Map> getScoreBjTop(JSONObject params) throws Exception;
    List<Map> getScoreBjBottom(JSONObject params) throws Exception;
    List<Map> getScoreBj1Top(JSONObject params) throws Exception;
    List<Map> getScoreBj1Bottom(JSONObject params) throws Exception;
    String getCourseName(JSONObject params) throws Exception;
    List<Map> getCourseSt(JSONObject params) throws Exception;
    Map getScoreStOne(JSONObject params) throws Exception;
    Map getScoreStOne1(JSONObject params) throws Exception;
    Map getScoreStInfo(JSONObject params) throws Exception;
    Map getScoreStInfo1(JSONObject params) throws Exception;
    Map getScoreStr(JSONObject params) throws Exception;
    Map getScoreStr1(JSONObject params) throws Exception;

}
