package com.school.service;

import com.alibaba.fastjson.JSONObject;
import com.school.model.college;
import com.school.dao.collegeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class collegeService {
    @Autowired
    private collegeDao collegeDao;

    public List<JSONObject> getList() throws Exception {
        List<college> params = collegeDao.getList();
        List<college> col = new ArrayList<>();
        List<college> dep = new ArrayList<>();
        List<college> pro = new ArrayList<>();
        for(int i=0; i<params.size();i++){
            college a = params.get(i);
            if(a.getLevel()==1){
                col.add(a);
            }else if(a.getLevel()==2){
                dep.add(a);
            }else{
                pro.add(a);
            }
        }
        List<JSONObject> list = new ArrayList<>();
        for(int i=0;i<col.size();i++){
            college a = col.get(i);
            JSONObject ar = new JSONObject();
            ar.put("name",a.getName());
            List<JSONObject> department = new ArrayList<>();
            for(int j=0;j<dep.size();j++){
                college b = dep.get(j);
                if(b.getParentId()==a.getId()){
                    JSONObject jso = new JSONObject();
                    jso.put("name",b.getName());
                    List<String> fes = new ArrayList<>();
                    for(int k=0;k<pro.size();k++){
                        college c = pro.get(k);
                        if(c.getParentId()==b.getId()) {
                            fes.add(c.getName());
                        }
                    }
                    jso.put("profession",fes);
                    department.add(jso);
                }
            }
            ar.put("department",department);
            list.add(ar);
        }
        return list;
    }
}
