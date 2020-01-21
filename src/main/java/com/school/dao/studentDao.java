package com.school.dao;

import com.alibaba.fastjson.JSONObject;
import com.school.model.punRecord;
import com.school.model.punRecord1;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;
import java.util.Map;

@MapperScan
public interface studentDao {
    List<JSONObject> getAmdClass() throws Exception;
    List<JSONObject> getStudents(JSONObject params) throws Exception;
    List<Map> getCreditList(JSONObject params) throws Exception;
    List<Map> getCreditListSp(JSONObject params) throws Exception;
    List<Map> getCreditCon(JSONObject params) throws Exception;
    List<Map> getCreditConSp(JSONObject params) throws Exception;
    List<JSONObject> getStudentBj(JSONObject params) throws Exception;
    List<JSONObject> getStudentSp(JSONObject params) throws Exception;
    List<JSONObject> getStudentSpB(JSONObject params) throws Exception;
    List<Map> getStudentC(JSONObject params) throws Exception;
    Map getUserInfo(int id)throws Exception;
    int updateImg(JSONObject params) throws Exception;
    String getImage(int p) throws Exception;
    Map getStudentInfo(int id) throws Exception;
    int updateInfo(JSONObject params) throws Exception;
    int updateInfoS(JSONObject params) throws Exception;
    Map getDetail(int id) throws Exception;
    List<JSONObject> getPrePra() throws Exception;
    Map getPrePrb(int id) throws Exception;
    Map matchStudent(JSONObject params) throws Exception;
    int punEnt(JSONObject params) throws Exception;
    List<punRecord> punA() throws Exception;
    List<punRecord> punB() throws Exception;
    List<punRecord> punASp(JSONObject params) throws Exception;
    List<punRecord> punBSp(JSONObject params) throws Exception;
    int writeOff(JSONObject params) throws Exception;
    punRecord1 getRecord(int id) throws Exception;
    List<Map> getCouSt(JSONObject params) throws Exception;
}
