package com.school.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.school.model.User;
import com.school.model.review;
import com.school.model.reviewd;
import com.school.service.scoreService;
import com.school.util.CommonUtil;
import com.school.util.JsonResult;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/score")
public class scoreController {
    @Autowired
    private scoreService scoreService;

    @RequestMapping(value = "/getScore.do",method = RequestMethod.POST)
    public List<JSONObject> getScore(HttpServletRequest request) throws Exception{
        JSONObject params = new JSONObject();
        params.put("schoolYear",request.getParameter("schoolYear"));
        params.put("semester",Integer.parseInt(request.getParameter("semester")));
        params.put("id",Integer.parseInt(request.getParameter("id")));
        List<JSONObject> ar = scoreService.getScore(params);
        return ar;
    }
    @RequestMapping(value = "/getCredit.do",method = RequestMethod.POST)
    public int getCredit(HttpServletRequest request) throws Exception{
        String type = request.getParameter("type");
        String[] valueArr = type.split(",");
        JSONObject params = new JSONObject();
        params.put("type",valueArr);
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        int id = user.getId();
        params.put("id",id);
        int a = scoreService.getCredit(params);
        return a;
    }

    @RequestMapping(value = "/getAllScoreN.do",method = RequestMethod.POST)
    public Map<String, Object> getAllScoreN(HttpServletRequest request) throws Exception{
        String type = request.getParameter("str");
        int id = Integer.parseInt(request.getParameter("id"));
        String[] valueArr = type.split(",");
        JSONObject params = new JSONObject();
        params.put("type",valueArr);
        params.put("id",id);
        List<Map> ar = scoreService.getAllScoreN(params);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("data", ar);
        resultMap.put("code", "0");
        resultMap.put("msg", "");
        resultMap.put("count", ar.size());
        return resultMap;
    }

    @RequestMapping(value = "/getAllScoreZ.do",method = RequestMethod.POST)
    public Map<String, Object> getAllScoreZ(HttpServletRequest request) throws Exception{
        String type = request.getParameter("str");
        int id = Integer.parseInt(request.getParameter("id"));
        String[] valueArr = type.split(",");
        JSONObject params = new JSONObject();
        params.put("code",valueArr);
        params.put("id",id);
        List<Map> ar = scoreService.getAllScoreZ(params);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("data", ar);
        resultMap.put("code", "0");
        resultMap.put("msg", "");
        resultMap.put("count", ar.size());
        return resultMap;
    }

    @RequestMapping(value = "/pushScore.do",method = RequestMethod.POST)
    public int pushScore(HttpServletRequest request) throws Exception{
        JSONObject params = new JSONObject();
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        int id = user.getId();
        params.put("id",id);
        params.put("code",request.getParameter("code"));
        int a = scoreService.pushScore(params);
        return a;
    }

    @RequestMapping(value = "/getReview",method = RequestMethod.POST)
    public JSONObject getReview(HttpServletRequest request,@RequestParam(value="pn",defaultValue = "1")Integer pn,
                                @RequestParam(value="pageSize",defaultValue = "10")Integer pageSize) throws Exception{
        PageHelper.startPage(pn,pageSize);
        List<review> result = scoreService.getReview();
        PageInfo<review> pageInfo=new PageInfo<review>(result,10);
        return CommonUtil.successJson(pageInfo);
    }

    @RequestMapping(value = "/getReviewd",method = RequestMethod.POST)
    public JSONObject getReviewd(HttpServletRequest request,@RequestParam(value="pn",defaultValue = "1")Integer pn,
                                @RequestParam(value="pageSize",defaultValue = "10")Integer pageSize) throws Exception{
        PageHelper.startPage(pn,pageSize);
        int type = Integer.parseInt(request.getParameter("type"));
        List<reviewd> result = scoreService.getReviewd(type);
        PageInfo<reviewd> pageInfo=new PageInfo<reviewd>(result,10);
        return CommonUtil.successJson(pageInfo);
    }

    @RequestMapping(value = "/creditSub.do",method = RequestMethod.POST)
    public Map<String, Object> creditSub(HttpServletRequest request) throws Exception{
        JSONObject params = new JSONObject();
        params.put("id",Integer.parseInt(request.getParameter("id")));
        params.put("name",request.getParameter("name"));
        params.put("code",request.getParameter("code"));
        Map ar = scoreService.creditSub(params);
        return ar;
    }

    @RequestMapping(value = "/pushCreditSub.do",method = RequestMethod.POST)
    public int pushCreditSub(HttpServletRequest request) throws Exception{
        JSONObject params = new JSONObject();
        params.put("id",Integer.parseInt(request.getParameter("sId")));
        params.put("courseId",request.getParameter("courseId"));
        params.put("nScore",request.getParameter("nScore"));
        int ar = scoreService.pushCreditSub(params);
        return ar;
    }
    @RequestMapping(value = "/updateCreditSub.do",method = RequestMethod.POST)
    public int updateCreditSub(HttpServletRequest request) throws Exception{
        JSONObject params = new JSONObject();
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        params.put("id",user.getId());
        params.put("sId",Integer.parseInt(request.getParameter("sId")));
        params.put("courseId",request.getParameter("courseId"));
        params.put("oScore",request.getParameter("oScore"));
        params.put("nScore",request.getParameter("nScore"));
        params.put("level",request.getParameter("level"));
        params.put("desc",request.getParameter("desc"));
        int ar = scoreService.updateCreditSub(params);
        return ar;
    }

    @RequestMapping(value = "/getAltRecord.do",method = RequestMethod.POST)
    public JSONObject getAltRecord(HttpServletRequest request, @RequestParam(value = "pn", defaultValue = "1") Integer pn,
                                     @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) throws Exception {
        PageHelper.startPage(pn, pageSize);
        JSONObject params = new JSONObject();
        params.put("type", request.getParameter("type"));
        params.put("spiVal", request.getParameter("spiVal"));
        List<JSONObject> result = scoreService.getAltRecord(params);

        PageInfo<JSONObject> pageInfo = new PageInfo<JSONObject>(result, 10);

        return CommonUtil.successJson(pageInfo);
    }

    @RequestMapping(value = "/dealWith.do",method = RequestMethod.POST)
    public JsonResult dealWith(HttpServletRequest request) throws Exception{
        JsonResult ar = new JsonResult();
        JSONObject params = new JSONObject();
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        if(null==user){
            ar.setSize(0);
            ar.setData("请重新登录！");
            ar.setSuccess(false);
        }else{
            params.put("userId",user.getId());
            params.put("id",Integer.parseInt(request.getParameter("id")));
            params.put("deal",Integer.parseInt(request.getParameter("deal")));
            int a = scoreService.dealWith(params);
            ar.setSize(0);
            ar.setData("审核成功！");
            ar.setSuccess(true);
        }

        return ar;
    }


    @RequestMapping(value = "/getDetails.do",method = RequestMethod.POST)
    public JSONObject getDetails(HttpServletRequest request,@RequestParam(value="pn",defaultValue = "1")Integer pn,
                          @RequestParam(value="pageSize",defaultValue = "10")Integer pageSize) throws Exception{
        PageHelper.startPage(pn,pageSize);

        JSONObject params = new JSONObject();
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        params.put("userId",user.getId());
        params.put("type",Integer.parseInt(request.getParameter("type")));
        List<review> result = scoreService.getDetails(params);
        PageInfo<review> pageInfo=new PageInfo<review>(result,10);
        return CommonUtil.successJson(pageInfo);
    }

    @RequestMapping(value = "/getBjScore.do",method = RequestMethod.POST)
    public JSONObject getBjScore(HttpServletRequest request,@RequestParam(value="pn",defaultValue = "1")Integer pn,
                                   @RequestParam(value="pageSize",defaultValue = "10")Integer pageSize) throws Exception{
        PageHelper.startPage(pn,pageSize);
        JSONObject params = new JSONObject();
        params.put("bj", Integer.parseInt(request.getParameter("bj")));
        params.put("schoolYear",request.getParameter("xn"));
        params.put("semester",Integer.parseInt(request.getParameter("xq")));
        List<JSONObject> result = scoreService.getBjScore(params);
        PageInfo<JSONObject> pageInfo=new PageInfo<JSONObject>(result,10);
        return CommonUtil.successJson(pageInfo);
    }

    @RequestMapping(value = "/getScoreList.do",method = RequestMethod.POST)
    public JSONObject getScoreList(HttpServletRequest request,@RequestParam(value="pn",defaultValue = "1")Integer pn,
                                 @RequestParam(value="pageSize",defaultValue = "10")Integer pageSize) throws Exception{
        PageHelper.startPage(pn,pageSize);
        List<JSONObject> result = new ArrayList<>();
        JSONObject params = new JSONObject();
        params.put("code", request.getParameter("code"));
        String list = request.getParameter("list");
        if(list!="") {
            String[] valueArr = list.split(",");
            int[] intArr = new int[valueArr.length];
            for (int i = 0; i < valueArr.length; i++) {
                intArr[i] = Integer.parseInt(valueArr[i]);
            }
            params.put("list", intArr);
        }else{
            params.put("list","");
        }
        result = scoreService.getScoreList(params);
        PageInfo<JSONObject> pageInfo=new PageInfo<JSONObject>(result,10);
        return CommonUtil.successJson(pageInfo);
    }

    @RequestMapping(value = "/getScoreList1.do",method = RequestMethod.POST)
    public JSONObject getScoreList1(HttpServletRequest request,@RequestParam(value="pn",defaultValue = "1")Integer pn,
                                   @RequestParam(value="pageSize",defaultValue = "10")Integer pageSize) throws Exception{
        PageHelper.startPage(pn,pageSize);
        List<JSONObject> result = new ArrayList<>();
        JSONObject params = new JSONObject();
        String list = request.getParameter("list");
        String[] valueArr = list.split(",");
        int[] intArr = new int[valueArr.length];
        for (int i = 0; i < valueArr.length; i++) {
            intArr[i] = Integer.parseInt(valueArr[i]);
        }
        params.put("list", intArr);
        result = scoreService.getScoreList1(params);
        PageInfo<JSONObject> pageInfo=new PageInfo<JSONObject>(result,10);
        return CommonUtil.successJson(pageInfo);
    }

    @RequestMapping(value = "/getScoreStudent.do",method = RequestMethod.POST)
    public JSONObject getScoreStudent(HttpServletRequest request,@RequestParam(value="pn",defaultValue = "1")Integer pn,
                                   @RequestParam(value="pageSize",defaultValue = "10")Integer pageSize) throws Exception{
        PageHelper.startPage(pn,pageSize);
        int id = Integer.parseInt(request.getParameter("id"));
        List<JSONObject> result = scoreService.getScoreStudent(id);
        PageInfo<JSONObject> pageInfo=new PageInfo<JSONObject>(result,10);
        return CommonUtil.successJson(pageInfo);
    }

    @RequestMapping(value = "/prePr.do",method = RequestMethod.POST)
    public List<Map> prePr(HttpServletRequest request) throws Exception{
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        int id = user.getId();
        List<Map> a = scoreService.prePr(id);
        return a;
    }

    @RequestMapping(value = "/prePr1.do",method = RequestMethod.POST)
    public List<Map> prePr1(HttpServletRequest request) throws Exception{
        int id = Integer.parseInt(request.getParameter("id"));
        List<Map> a = scoreService.prePr(id);
        return a;
    }

    @RequestMapping(value = "/getAllScore.do")
    public JSONObject getAllScore(HttpServletRequest request) throws Exception{
        JSONObject params = new JSONObject();
        String type = request.getParameter("type");
        String[] valueArr = type.split(",");
        params.put("type",valueArr);
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        int id = user.getId();
        params.put("id",id);
        List<JSONObject> result = scoreService.getAllScore(params);
        return CommonUtil.successJson(result);
    }

    @RequestMapping(value = "/getAllScoreNot.do")
    @ResponseBody
    public Map<Object, Object> getAllScoreNot(HttpServletRequest request, Integer page, Integer limit, String str) throws Exception {
        JSONObject params = new JSONObject();
        String[] valueArr = str.split(",");
        params.put("type", valueArr);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int id = user.getId();
        params.put("id", id);
        params.put("page", (page - 1) * 10);
        params.put("limit", limit);
        List<JSONObject> result = scoreService.getReportList(params);
        Integer count = scoreService.findReportListCount(params);
        Map<Object, Object> map = new HashedMap();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", count);
        map.put("data", result);
        return map;
    }

}
