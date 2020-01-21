package com.school.dao;

import com.alibaba.fastjson.JSONObject;
import com.school.model.User;
import com.school.model.analysis;
import com.school.model.analysis2;
import com.school.model.rewRecord;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;
import java.util.Map;


@MapperScan
public interface UserDao {
    User selectUser(JSONObject params);
    analysis selectSex(JSONObject params);
    List<analysis2> selectAna(JSONObject params);
    int updatePsd(JSONObject params);
    List<Map> getFDY(JSONObject params);
    List<Map> getAllFDY();
    int putUser(List<Map> params);
    int addUser(List<Map> params);
    int addUserImage(List<Map> params);
    int putUser1(List<Map> params);
    int putUser3(JSONObject params);
    int addUserImage3(Map params);
    int addUser3(Map params);
    int putUser4(JSONObject params);
    int upLoadCourse(List<Map> params);
    int upLoadCourseInfo(List<Map> params);
    int upLoadCourse1(JSONObject params);
    int upLoadCourseInfo1(JSONObject params);
    int upLoadScore1(JSONObject params);
    int upLoadScore(List<Map> params);
    int upLoadClass1(JSONObject params);
    int upLoadClass(List<Map> params);
    int upLoadReward(List<Map> params);
    int upLoadReward1(JSONObject params);
    List<rewRecord> getRewRecord(JSONObject params);
    int yanzheng(JSONObject params);
    Map yanzheng1(JSONObject params);
    Map yanzheng2(JSONObject params);
}
