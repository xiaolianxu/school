package com.school.service;

import com.alibaba.fastjson.JSONObject;
import com.school.dao.scoreDao;
import com.school.model.review;
import com.school.model.reviewd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class scoreService {
    @Autowired
    private scoreDao scoreDao;

    public List<JSONObject> getScore(JSONObject params) throws Exception{
        List<JSONObject> ar = new ArrayList<>();
        ar = scoreDao.getScore(params);
        return ar;
    }

    public Map<String, Object> creditSub(JSONObject params) throws Exception{
        Map ar =  scoreDao.creditSub(params);
        return ar;
    }

    public List<JSONObject> getAltRecord(JSONObject params) throws Exception{
        List<JSONObject> ar = scoreDao.getAltRecord(params);
        return ar;
    }

    public int pushCreditSub(JSONObject params) throws Exception{
        int a = scoreDao.pushCreditSub(params);
        return a;
    }
    public int updateCreditSub(JSONObject params) throws Exception{
        int a = scoreDao.updateCreditSub(params);
        return a;
    }

    public List<JSONObject> getAllScore(JSONObject params) throws Exception{
        List<JSONObject> ar = scoreDao.getAllScore(params);
        return ar;
    }
    public List<JSONObject> getAllScoreNot(JSONObject params) throws Exception{
        List<JSONObject> ar = scoreDao.getAllScoreNot(params);
        return ar;
    }

    public List<Map> getAllScoreN(JSONObject params) throws Exception{
        List<Map> ar = scoreDao.getAllScoreN(params);
        for(int i=0;i<ar.size();i++){
            Map a = ar.get(i);
            double time = Double.parseDouble(a.get("time").toString());
            String da = a.get("data").toString();
            String temp = (da.split("\\}")[0]).split("\\{")[1];
            String temp1 = temp.substring(1,temp.indexOf("周"));
            String[] b = temp1.split("-");
            double hour = time*(Integer.parseInt(b[1])-Integer.parseInt(b[0])+1);
            a.put("classHour",hour);
            String sem = a.get("school_year").toString() +"学年，第"+ a.get("semester").toString()+"学期";
            a.put("sem",sem);
            int type = Integer.parseInt(a.get("type").toString());
            if(type==1){
                String s = a.get("score").toString();
                if(s.equals("优秀")) {
                    a.put("score", 95);
                }else if(s.equals("良好")){
                    a.put("score", 85);
                }else if(s.equals("中等")){
                    a.put("score", 75);
                }else if(s.equals("及格")){
                    a.put("score",60);
                }else{
                    a.put("score",0);
                }
            }
        }
        return ar;
    }

    public List<Map> getAllScoreZ(JSONObject params) throws Exception{
        List<Map> ar = scoreDao.getAllScoreZ(params);
        for(int i=0;i<ar.size();i++){
            Map a = ar.get(i);
            double time = Double.parseDouble(a.get("time").toString());
            String da = a.get("data").toString();
            String temp = (da.split("\\}")[0]).split("\\{")[1];
            String temp1 = temp.substring(1,temp.indexOf("周"));
            String[] b = temp1.split("-");
            double hour = time*(Integer.parseInt(b[1])-Integer.parseInt(b[0])+1);
            a.put("classHour",hour);
            String sem = a.get("school_year").toString() +"学年，第"+ a.get("semester").toString()+"学期";
            a.put("sem",sem);
            int type = Integer.parseInt(a.get("type").toString());
            if(type==1){
                String s = a.get("score").toString();
                if(s.equals("优秀")) {
                    a.put("score", 95);
                }else if(s.equals("良好")){
                    a.put("score", 85);
                }else if(s.equals("中等")){
                    a.put("score", 75);
                }else if(s.equals("及格")){
                    a.put("score",60);
                }else{
                    a.put("score",0);
                }
            }
        }
        return ar;
    }

    public  int getCredit(JSONObject params) throws Exception{
        int a = scoreDao.getCredit(params);
        return a;
    }

    public int pushScore(JSONObject params) throws Exception{
        int a = scoreDao.pushScore(params);
        return a;
    }
    public List<review> getReview() throws Exception{
        List<review> ar = scoreDao.getReview();
        return ar;
    }

    public List<reviewd> getReviewd(int type) throws Exception{
        List<reviewd> ar = scoreDao.getReviewd(type);
        return ar;
    }

    public int dealWith(JSONObject params) throws Exception{
        int a = scoreDao.dealWith(params);
        return a;
    }

    public List<review> getDetails(JSONObject params) throws Exception{
        List<review> ar = scoreDao.getDetails(params);
        return ar;
    }

    public List<JSONObject> getScoreList(JSONObject params) throws Exception{
        List<JSONObject> ar = scoreDao.getScoreList(params);
        return ar;
    }

    public List<JSONObject> getScoreList1(JSONObject params) throws Exception{
        List<JSONObject> ar = scoreDao.getScoreList1(params);
        return ar;
    }

    public List<JSONObject> getScoreStudent(int id) throws Exception{
        List<JSONObject> ar = scoreDao.getScoreStudent(id);
        return ar;
    }

    public List<Map> prePr(int id) throws Exception{
        List<Map> a = scoreDao.prePr(id);
        return a;
    }

    public List<JSONObject> getBjScore(JSONObject params) throws Exception{
        List<JSONObject> ar = scoreDao.getBjScore(params);
        return ar;
    }

    public List<JSONObject> getReportList(JSONObject params) throws Exception{
        return this.scoreDao.getReportList(params);
    }

    public Integer findReportListCount(JSONObject params) throws Exception{
        return this.scoreDao.findReportListCount(params);
    }

}
