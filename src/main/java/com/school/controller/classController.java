package com.school.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.school.model.User;
import com.school.service.classService;
import com.school.util.CommonUtil;
import com.school.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.school.util.ErrorEnum;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/class")
public class classController {
    @Autowired
    private classService classService;

    @RequestMapping(value = "/getClassList.do",method = RequestMethod.POST)
    public JSONObject getCourseList(HttpServletRequest request,@RequestParam(value="pn",defaultValue = "1")Integer pn,
                                    @RequestParam(value="pageSize",defaultValue = "10")Integer pageSize) throws Exception {
        PageHelper.startPage(pn,pageSize);
        JSONObject params = new JSONObject();
        params.put("col",request.getParameter("col"));
        params.put("dep",request.getParameter("dep"));
        params.put("pro",request.getParameter("pro"));
        params.put("nj",Integer.parseInt(request.getParameter("nj")));
        List<JSONObject> result = classService.getClassList(params);
        System.out.println(result);
        PageInfo<JSONObject> pageInfo=new PageInfo<JSONObject>(result,10);

        return CommonUtil.successJson(pageInfo);
    }

    @RequestMapping(value = "/getClassSp.do",method = RequestMethod.POST)
    public JSONObject getClassSp(HttpServletRequest request,@RequestParam(value="pn",defaultValue = "1")Integer pn,
                                 @RequestParam(value="pageSize",defaultValue = "10")Integer pageSize) throws Exception {
        PageHelper.startPage(pn,pageSize);
        JSONObject params = new JSONObject();
        params.put("type",request.getParameter("type"));
        params.put("spiVal",request.getParameter("spiVal"));
        if(params.getString("spiVal")==null||params.getString("spiVal").equals("")){
            return CommonUtil.errorJson(ErrorEnum.E_90003);
        }
        List<JSONObject> result = classService.getClassSp(params);
        PageInfo<JSONObject> pageInfo=new PageInfo<JSONObject>(result,10);
        return CommonUtil.successJson(pageInfo);
    }

    @RequestMapping(value = "/getClassByC.do",method = RequestMethod.POST)
    public JSONObject getClassByC(HttpServletRequest request,@RequestParam(value="pn",defaultValue = "1")Integer pn,
                                 @RequestParam(value="pageSize",defaultValue = "10")Integer pageSize) throws Exception {
        PageHelper.startPage(pn,pageSize);
        JSONObject params = new JSONObject();
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        params.put("userId",user.getId());
        params.put("spiVal",request.getParameter("spiVal"));
        List<JSONObject> result = classService.getClassByC(params);
        PageInfo<JSONObject> pageInfo=new PageInfo<JSONObject>(result,10);
        return CommonUtil.successJson(pageInfo);
    }

    @RequestMapping(value = "/getClassBy.do",method = RequestMethod.POST)
    public List<JSONObject> getClassBy(HttpServletRequest request) throws Exception {
        JSONObject params = new JSONObject();
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        int id =user.getId();
//        int id = 14041;
        List<JSONObject> result = classService.getClassBy(id);
        return result;
    }

    @RequestMapping(value = "/updateClass.do",method = RequestMethod.POST)
    public JsonResult updateClass(@RequestBody JSONObject params, HttpServletRequest request) throws Exception{
        JsonResult ar = new JsonResult();
        if(params.get("people")==null || params.get("people").equals("")){
            ar.setSize(0);
            ar.setData("人数不能为空！");
            ar.setSuccess(false);
        }else {
            int people = params.getInteger("people");
            params.put("people",people);
            int user_id = params.getInteger("user_id");
            params.put("user_id",user_id);
            int id = params.getInteger("id");
            params.put("id",id);
            int i = classService.updateClass(params);
            ar.setSize(0);
            ar.setData("信息修改成功！");
            ar.setSuccess(true);
        }
        return ar;
    }

}
