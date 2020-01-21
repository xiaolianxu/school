package com.school.service;

import com.alibaba.fastjson.JSONObject;
import com.school.dao.classDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class classService {
    @Autowired
    private classDao classDao;

    public List<JSONObject> getClassList(JSONObject params) throws Exception{
        List<JSONObject> ar = classDao.getClassList(params);
        return ar;
    }

    public List<JSONObject> getClassSp(JSONObject params) throws Exception{
        List<JSONObject> ar = classDao.getClassSp(params);
        return ar;
    }
    public List<JSONObject> getClassByC(JSONObject params) throws Exception{
        List<JSONObject> ar  = classDao.getClassByC(params);
        return ar;
    }

    public List<JSONObject> getClassBy(int id) throws Exception{
        List<JSONObject> ar  = classDao.getClassBy(id);
        return ar;
    }

    public int updateClass(JSONObject params) throws Exception{
        int a = classDao.updateClass(params);
        return a;
    }
}
