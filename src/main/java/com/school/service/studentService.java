package com.school.service;

import com.alibaba.fastjson.JSONObject;
import com.school.dao.studentDao;
import com.school.model.punRecord;
import com.school.model.punRecord1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class studentService {
    @Autowired
    private studentDao studentDao;
    public List<JSONObject> getAmdClass() throws Exception{
        List<JSONObject> list = studentDao.getAmdClass();
        return list;
    }

    public List<Map> getCouSt(JSONObject params) throws Exception{
        return this.studentDao.getCouSt(params);
    }

    public List<JSONObject> getStudents(JSONObject params) throws Exception{
        List<JSONObject> list = studentDao.getStudents(params);
        return list;
    }
    public Map matchStudent(JSONObject params) throws Exception{
        Map a = studentDao.matchStudent(params);
        return a;
    }
    public List<Map> getCreditCon(JSONObject params) throws Exception{
        List<Map> list = studentDao.getCreditCon(params);
        if(list.size()==1&&null==list.get(0)||list.size()==0) {
            list = new ArrayList<>();
        }else{
            for (int i = 0; i < list.size(); i++) {
                Map a = list.get(i);
                String str = a.get("descr").toString();
                String[] temp = str.split("\\],\\[");
                double b = 0;
                for (int j = 0; j < temp.length - 1; j++) {
                    b += Double.parseDouble(temp[j].split(",")[1]);
                }
                b += Double.parseDouble(temp[temp.length - 1].split("\\]\\]")[0].split(",")[1]);
                a.put("descr", b);
            }
        }
        return list;
    }

    public List<Map> getCreditConSp(JSONObject params) throws Exception{
        List<Map> list = studentDao.getCreditConSp(params);
        if(list.size()==1&&null==list.get(0)||list.size()==0) {
            list = new ArrayList<>();
        }else{
            for (int i = 0; i < list.size(); i++) {
                Map a = list.get(i);
                String str = a.get("descr").toString();
                String[] temp = str.split("\\],\\[");
                double b = 0;
                for (int j = 0; j < temp.length - 1; j++) {
                    b += Double.parseDouble(temp[j].split(",")[1]);
                }
                b += Double.parseDouble(temp[temp.length - 1].split("\\]\\]")[0].split(",")[1]);
                a.put("descr", b);
            }
        }
        return list;
    }

    public List<Map> getCreditList(JSONObject params) throws Exception{
        List<Map> list = studentDao.getCreditList(params);
        if(list.size()==1&&null==list.get(0)||list.size()==0) {
            list = new ArrayList<>();
        }else{
            for (int i = 0; i < list.size(); i++) {
                Map a = list.get(i);
                String str = a.get("descr").toString();
                String[] temp = str.split("\\],\\[");
                double b = 0;
                for (int j = 0; j < temp.length - 1; j++) {
                    b += Double.parseDouble(temp[j].split(",")[1]);
                }
                b += Double.parseDouble(temp[temp.length - 1].split("\\]\\]")[0].split(",")[1]);
                a.put("descr", b);
            }
        }
        return list;
    }

    public List<Map> getCreditListSp(JSONObject params) throws Exception{
        List<Map> list = studentDao.getCreditListSp(params);
        if(list.size()==1&&null==list.get(0)||list.size()==0) {
            list = new ArrayList<>();
        }else{
            for (int i = 0; i < list.size(); i++) {
                Map a = list.get(i);
                String str = a.get("descr").toString();
                String[] temp = str.split("\\],\\[");
                double b = 0;
                for (int j = 0; j < temp.length - 1; j++) {
                    b += Double.parseDouble(temp[j].split(",")[1]);
                }
                b += Double.parseDouble(temp[temp.length - 1].split("\\]\\]")[0].split(",")[1]);
                a.put("descr", b);
            }
        }
        return list;
    }

    public List<JSONObject>  getStudentBj(JSONObject params) throws Exception{
        List<JSONObject> list = studentDao.getStudentBj(params);
        return list;
    }

    public List<JSONObject> getStudentSp(JSONObject params) throws Exception{
        List<JSONObject> list = studentDao.getStudentSp(params);
        return list;
    }

    public List<Map> getStudentC(JSONObject params) throws Exception{
        List<Map> list = studentDao.getStudentC(params);
        return list;
    }

    public List<JSONObject> getStudentSpB(JSONObject params) throws Exception{
        List<JSONObject> list = studentDao.getStudentSpB(params);
        return list;
    }

    public Map<String, Object> getUserInfo(int id) throws Exception{
        Map ar = studentDao.getUserInfo(id);
        return ar;
    }
    public int updateImg(JSONObject params) throws Exception{
        int ar = studentDao.updateImg(params);
        return  ar;
    }

    public String getImage(int p) throws Exception{
        String img = studentDao.getImage(p);
        return img;
    }
    public Map<String, Object> getStudentInfo(int id) throws Exception{
        Map ar = studentDao.getStudentInfo(id);
        return ar;
    }

    public int updateInfo(JSONObject params) throws Exception{
        int a = studentDao.updateInfo(params);
        return a;
    }

    public int punEnt(JSONObject params) throws Exception{
        int a = studentDao.punEnt(params);
        return a;
    }

    public int updateInfoS(JSONObject params) throws Exception{
        int a = studentDao.updateInfoS(params);
        return a;
    }

    public Map getDetail(int id) throws Exception{
        Map a = studentDao.getDetail(id);
        return a;
    }

    public JSONObject prePr(int id) throws Exception{
        JSONObject result = new JSONObject();
        List<JSONObject> a = studentDao.getPrePra();
        Map b = studentDao.getPrePrb(id);
        result.put("list",a);
        result.put("descr",b);
        return result;
    }

    public List<punRecord> punRecord(String status) throws Exception{
        List<punRecord> ar = new ArrayList<>();
        if(status.equals("0")){
            ar = studentDao.punA();
        }else{
            ar = studentDao.punB();
        }
        return ar;
    }

    public List<punRecord> punRecordSp(JSONObject params) throws Exception{
        List<punRecord> ar = new ArrayList<>();
        String status = params.getString("status");
        if(status.equals("0")){
            ar = studentDao.punASp(params);
        }else{
            ar = studentDao.punBSp(params);
        }
        return ar;
    }

    public int writeOff(JSONObject params) throws Exception{
        int a = studentDao.writeOff(params);
        return a;
    }
    public punRecord1 getRecord(int id) throws Exception{
        punRecord1 a = studentDao.getRecord(id);
        return a;
    }
}
