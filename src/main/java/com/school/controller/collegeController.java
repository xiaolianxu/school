package com.school.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.school.service.collegeService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/college")
public class collegeController {
    @Autowired
    private collegeService collegeService;

    @RequestMapping(value = "/getList.do",method = RequestMethod.POST)
    public List<JSONObject>  getCollegeList(HttpServletRequest request) throws Exception{
        List<JSONObject> ar = collegeService.getList();
        return ar;
    }
}
