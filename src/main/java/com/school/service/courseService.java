package com.school.service;

import com.alibaba.fastjson.JSONObject;
import com.school.dao.courseDao;
import com.school.model.courseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class courseService {
    @Autowired
    private courseDao courseDao;
    public List<JSONObject> getCourse(JSONObject params) throws Exception{
        List<JSONObject> list = new ArrayList<>();
        list = courseDao.getCourse(params);
        return list;
    }

    public List<Map> getCourseBj(JSONObject params) throws Exception{
        List<Map> list = courseDao.getCourseBj(params);
        return list;
    }

    public List<Map> getCourseSt(JSONObject params) throws Exception{
        List<Map> list = courseDao.getCourseSt(params);
        return list;
    }

    public List<JSONObject> getCourseT(JSONObject params) throws Exception{
        List<JSONObject> list = new ArrayList<>();
        list = courseDao.getCourseT(params);
        return list;
    }
    public List<Map> getAllCourse() throws Exception{
        List<Map> list = courseDao.getAllCourse();
        return list;
    }

    public List<Map> getTeacher() throws Exception{
        List<Map> list = courseDao.getTeacher();
        return list;
    }

    public List<JSONObject> getCourseR(int id) throws Exception{
        List<JSONObject> list = courseDao.getCourseR(id);
        return list;
    }

    public List<JSONObject> getCourseSp(JSONObject params) throws Exception{
        List<JSONObject> list = new ArrayList<>();
        list = courseDao.getCourseSp(params);
        return list;
    }

    public List<JSONObject> getCourseC(JSONObject params) throws Exception{
        List<JSONObject> list = new ArrayList<>();
        list = courseDao.getCourseC(params);
        return list;
    }

    public List<JSONObject> getCourseSpT(JSONObject params) throws Exception{
        List<JSONObject> list = new ArrayList<>();
        list = courseDao.getCourseSpT(params);
        return list;
    }

    public List<courseInfo> getCourseInfo(String code) throws Exception{
        List<courseInfo> list = new ArrayList<>();
        list = courseDao.getCourseInfo(code);
        return list;
    }

    public JSONObject getAll() throws Exception{
        JSONObject ar = new JSONObject();
        List a = courseDao.getCollege();
        ar.put("college",a);
        List<Map> b = courseDao.getZx();
        ar.put("courseType",b);
        return ar;
    }

    public JSONObject getB() throws Exception{
        JSONObject ar = new JSONObject();
        List a = courseDao.getCollege();
        ar.put("college",a);
        List<Map> b = courseDao.getB();
        ar.put("code",b);
        return ar;
    }

    public List<Map> getCourseType() throws Exception{
        List<Map> b = new ArrayList();
        b = courseDao.getZx();
        return b;
    }

    public JSONObject getScoreBj(JSONObject params) throws Exception{
        int type = Integer.parseInt(params.get("type").toString());
        JSONObject ar = new JSONObject();
        Map result = new HashMap();
        Map result1 = new HashMap();
        List<Map> all = new ArrayList<>();
        List<Map> one = new ArrayList<>();
        if(type==0){
            result = courseDao.getScoreBj(params);
            result1 = courseDao.getScoreBjOne(params);
            Map a1 = new HashMap();
            a1.put("name","95分及以上");
            a1.put("value",Integer.parseInt(result.get("A").toString()));
            Map a2 = new HashMap();
            a2.put("name","85分-94分");
            a2.put("value",Integer.parseInt(result.get("B").toString()));
            Map a3 = new HashMap();
            a3.put("name","75分-84分");
            a3.put("value",Integer.parseInt(result.get("C").toString()));
            Map a4 = new HashMap();
            a4.put("name","60分-74分");
            a4.put("value",Integer.parseInt(result.get("D").toString()));
            Map a5 = new HashMap();
            a5.put("name","60分以下");
            a5.put("value",Integer.parseInt(result.get("E").toString()));
            all.add(a1);all.add(a2);all.add(a3);all.add(a4);all.add(a5);
            a1.put("value",Integer.parseInt(result1.get("A").toString()));
            a2.put("value",Integer.parseInt(result1.get("B").toString()));
            a3.put("value",Integer.parseInt(result1.get("C").toString()));
            a4.put("value",Integer.parseInt(result1.get("D").toString()));
            a5.put("value",Integer.parseInt(result1.get("E").toString()));
            one.add(a1);one.add(a2);one.add(a3);one.add(a4);one.add(a5);
            List<Map> result2 = courseDao.getScoreBjTop(params);
            ar.put("top",result2);
            List<Map> result3 = courseDao.getScoreBjBottom(params);
            ar.put("bottom",result3);
        }else{
            result = courseDao.getScoreBj1(params);
            result1 = courseDao.getScoreBj1One(params);
            Map a1 = new HashMap();
            a1.put("name","优秀");
            a1.put("value",Integer.parseInt(result.get("A").toString()));
            Map a2 = new HashMap();
            a2.put("name","良好");
            a2.put("value",Integer.parseInt(result.get("B").toString()));
            Map a3 = new HashMap();
            a3.put("name","中等");
            a3.put("value",Integer.parseInt(result.get("C").toString()));
            Map a4 = new HashMap();
            a4.put("name","及格");
            a4.put("value",Integer.parseInt(result.get("D").toString()));
            Map a5 = new HashMap();
            a5.put("name","不及格");
            a5.put("value",Integer.parseInt(result.get("E").toString()));
            all.add(a1);all.add(a2);all.add(a3);all.add(a4);all.add(a5);
            a1.put("value",Integer.parseInt(result1.get("A").toString()));
            a2.put("value",Integer.parseInt(result1.get("B").toString()));
            a3.put("value",Integer.parseInt(result1.get("C").toString()));
            a4.put("value",Integer.parseInt(result1.get("D").toString()));
            a5.put("value",Integer.parseInt(result1.get("E").toString()));
            one.add(a1);one.add(a2);one.add(a3);one.add(a4);one.add(a5);
            List<Map> result2 = courseDao.getScoreBj1Top(params);
            ar.put("top",result2);
            List<Map> result3 = courseDao.getScoreBj1Bottom(params);
            ar.put("bottom",result3);
        }
        ar.put("all",all);
        ar.put("one",one);
        String courseName = courseDao.getCourseName(params);
        ar.put("courseName",courseName);
        return ar;
    }

    public JSONObject getScoreSt(JSONObject params) throws Exception{
        int type = Integer.parseInt(params.get("type").toString());
        JSONObject ar = new JSONObject();
        Map result = new HashMap();
        Map result1 = new HashMap();
        List<Map> all = new ArrayList<>();
        List<Map> one = new ArrayList<>();
        if(type==0){
            Map num = courseDao.getScoreStInfo(params);
            String sNumber = courseDao.getScoreStr(params).get("rownum").toString();
            num.put("number",Integer.parseInt(sNumber));
            result = courseDao.getScoreBj(params);
            result1 = courseDao.getScoreStOne(params);
            int a = Integer.parseInt(result.get("A").toString());
            int b = Integer.parseInt(result.get("B").toString());
            int c = Integer.parseInt(result.get("C").toString());
            int d = Integer.parseInt(result.get("D").toString());
            int e = Integer.parseInt(result.get("E").toString());
            Map a1 = new HashMap();a1.put("name","95分及以上");a1.put("value",a);
            Map a2 = new HashMap();a2.put("name","85分-94分");a2.put("value",b);
            Map a3 = new HashMap();a3.put("name","75分-84分");a3.put("value",c);
            Map a4 = new HashMap();a4.put("name","60分-74分");a4.put("value",d);
            Map a5 = new HashMap();a5.put("name","60分以下");a5.put("value",e);
            all.add(a1);all.add(a2);all.add(a3);all.add(a4);all.add(a5);
            int a0 = Integer.parseInt(result1.get("A").toString());
            int b1 = Integer.parseInt(result1.get("B").toString());
            int c1 = Integer.parseInt(result1.get("C").toString());
            int d1 = Integer.parseInt(result1.get("D").toString());
            int e1 = Integer.parseInt(result1.get("E").toString());
            a1.put("value",a0);a2.put("value",b1);a3.put("value",c1);a4.put("value",d1);a5.put("value",e1);
            one.add(a1);one.add(a2);one.add(a3);one.add(a4);one.add(a5);
            num.put("people",a+b+c+d+e);
            num.put("sPeople",a0+b1+c1+d1+e1);
            ar.put("num",num);
        }else{
            Map num = courseDao.getScoreStInfo1(params);
            String sNumber = courseDao.getScoreStr1(params).get("rownum").toString();
            num.put("number",Integer.parseInt(sNumber));
            result = courseDao.getScoreBj1(params);
            result1 = courseDao.getScoreStOne1(params);
            int a = Integer.parseInt(result.get("A").toString());
            int b = Integer.parseInt(result.get("B").toString());
            int c = Integer.parseInt(result.get("C").toString());
            int d = Integer.parseInt(result.get("D").toString());
            int e = Integer.parseInt(result.get("E").toString());
            Map a1 = new HashMap();a1.put("name","优秀");a1.put("value",a);
            Map a2 = new HashMap();a2.put("name","良好");a2.put("value",b);
            Map a3 = new HashMap();a3.put("name","中等");a3.put("value",c);
            Map a4 = new HashMap();a4.put("name","及格");a4.put("value",d);
            Map a5 = new HashMap();a5.put("name","不及格");a5.put("value",e);
            all.add(a1);all.add(a2);all.add(a3);all.add(a4);all.add(a5);
            int a0 = Integer.parseInt(result1.get("A").toString());
            int b1 = Integer.parseInt(result1.get("B").toString());
            int c1 = Integer.parseInt(result1.get("C").toString());
            int d1 = Integer.parseInt(result1.get("D").toString());
            int e1 = Integer.parseInt(result1.get("E").toString());
            a1.put("value",a0);a2.put("value",b1);a3.put("value",c1);a4.put("value",d1);a5.put("value",e1);
            one.add(a1);one.add(a2);one.add(a3);one.add(a4);one.add(a5);
            num.put("people",a+b+c+d+e);
            num.put("sPeople",a0+b1+c1+d1+e1);
            ar.put("num",num);
        }
        ar.put("all",all);
        ar.put("one",one);
        String courseName = courseDao.getCourseName(params);
        ar.put("courseName",courseName);
        ar.put("courseId",params.get("courseId").toString());
        return ar;
    }
}
