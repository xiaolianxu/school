package com.school.dao;

import com.alibaba.fastjson.JSONObject;
import com.school.model.reviewd;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;
import java.util.Map;
import com.school.model.review;

@MapperScan
public interface scoreDao {
    List<JSONObject> getScore(JSONObject params) throws Exception;
    List<JSONObject> getAllScore(JSONObject params) throws Exception;
    List<JSONObject> getAllScoreNot(JSONObject params) throws Exception;
    List<JSONObject> getScoreList(JSONObject params) throws Exception;
    List<JSONObject> getScoreList1(JSONObject params) throws Exception;
    List<JSONObject> getScoreStudent(int id) throws Exception;
    List<JSONObject> getAltRecord(JSONObject params) throws Exception;
    Map creditSub(JSONObject params) throws Exception;
    int pushCreditSub(JSONObject params) throws Exception;
    int updateCreditSub(JSONObject params) throws Exception;
    int getCredit(JSONObject params) throws Exception;
    int pushScore(JSONObject params) throws Exception;
    List<review> getReview() throws Exception;
    List<review> getDetails(JSONObject params) throws Exception;
    List<reviewd> getReviewd(@Param("type") int type) throws Exception;
    int dealWith(JSONObject params) throws Exception;
    List<Map> getAllScoreN(JSONObject params) throws Exception;
    List<Map> getAllScoreZ(JSONObject params) throws Exception;
    List<Map> prePr(int id) throws Exception;
    List<JSONObject> getBjScore(JSONObject params) throws Exception;
    List<JSONObject> getReportList(JSONObject params) throws Exception;
    Integer findReportListCount(JSONObject params) throws Exception;
}
