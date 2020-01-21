package com.school.service;

import com.alibaba.fastjson.JSONObject;
import com.school.dao.teacherDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class teacherService {
    @Autowired
    private teacherDao teacherDao;
    public int updateImg(JSONObject params) throws Exception{
        int ar = teacherDao.updateImg(params);
        return  ar;
    }

    public String getImage(int p) throws Exception{
        String img = teacherDao.getImage(p);
        return img;
    }
    public Map<String, Object> getTeacherInfo(int id) throws Exception{
        Map ar = teacherDao.getTeacherInfo(id);
        return ar;
    }
    public int updateInfo(JSONObject params) throws Exception{
        int a = teacherDao.updateInfo(params);
        return a;
    }
    public List<JSONObject> getStudentScore(JSONObject params) throws Exception{
        List<JSONObject> ar = teacherDao.getStudentScore(params);
        return ar;
    }

    public Map getSSAna(JSONObject params) throws Exception{
        Map a = new HashMap();
        if(params.get("type").toString().equals("0")){
            Map temp = teacherDao.getSSAna(params);
            if(temp==null){

            }else {
                ArrayList list = new ArrayList();
                List<Map> p = new ArrayList<>();
                int sa = Integer.parseInt(temp.get("A").toString());
                if (sa != 0) {
                    list.add("95分及以上");
                    Map c = new HashMap();
                    c.put("name", "95分及以上");
                    c.put("value", sa);
                    p.add(c);
                }
                int sb = Integer.parseInt(temp.get("B").toString());
                if (sb != 0) {
                    list.add("85分-94分");
                    Map c = new HashMap();
                    c.put("name", "85分-94分");
                    c.put("value", sb);
                    p.add(c);
                }
                int sc = Integer.parseInt(temp.get("C").toString());
                if (sc != 0) {
                    list.add("75分-84分");
                    Map c = new HashMap();
                    c.put("name", "75分-84分");
                    c.put("value", sc);
                    p.add(c);
                }
                int sd = Integer.parseInt(temp.get("D").toString());
                if (sd != 0) {
                    list.add("60分-74分");
                    Map c = new HashMap();
                    c.put("name", "60分-74分");
                    c.put("value", sd);
                    p.add(c);
                }
                int se = Integer.parseInt(temp.get("E").toString());
                if (se != 0) {
                    list.add("60分以下");
                    Map c = new HashMap();
                    c.put("name", "60分以下");
                    c.put("value", se);
                    p.add(c);
                }
                a.put("level", list);
                a.put("people", p);
            }
        }else{
            List<Map> temp = teacherDao.getSSAna1(params);
            if(temp==null || (temp.size()==1 && temp.get(0).get("value").toString()=="0")){

            }else{
                String[] level = new String[temp.size()];
                for(int i=0;i<temp.size();i++){
                    level[i] = temp.get(i).get("name").toString();
                }
                a.put("level",level);
                a.put("people",temp);
            }
        }
        return a;
    }

    public List<JSONObject> getStudentScoreT(JSONObject params) throws Exception{
        List<JSONObject> ar = teacherDao.getStudentScoreT(params);
        return ar;
    }
}
