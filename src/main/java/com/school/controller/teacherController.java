package com.school.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.school.model.User;
import com.school.service.teacherService;
import com.school.util.CommonUtil;
import com.school.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/teacher")
public class teacherController {
    @Autowired
    private teacherService teacherService;

    @RequestMapping(value = "/getTeacherInfo.do",method = RequestMethod.POST)
    public Map<String, Object> getStudentInfo(HttpServletRequest request) throws Exception{
        HttpSession session = request.getSession();
        User user= (User) session.getAttribute("user");
        int id = user.getId();
        Map ar = teacherService.getTeacherInfo(id);
        String img = id + "_" + teacherService.getImage(id);
        ar.put("image", img);
        return ar;
    }

    @RequestMapping(value = "/upLoad.do", method = RequestMethod.POST)
    @ResponseBody
    public String upload(HttpServletRequest req) throws Exception {
        HttpSession session = req.getSession();
        User user= (User) session.getAttribute("user");
        int id = user.getId();
        MultipartHttpServletRequest mreq = (MultipartHttpServletRequest) req;
        MultipartFile file = mreq.getFile("file");
        String fileName = file.getOriginalFilename();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String shortN = sdf.format(new Date()) + ((int) (Math.random() * 10)) + ((int) (Math.random() * 10)) + fileName.substring(fileName.lastIndexOf('.'));
        String fname = id +"_"+ shortN;
        String name = req.getSession().getServletContext().getRealPath("/") +
                "upload/" + fname;
        FileOutputStream fos = new FileOutputStream(name);
        fos.write(file.getBytes());
        fos.flush();
        fos.close();
        JSONObject params = new JSONObject();
        params.put("id",id);
        params.put("name",shortN);
        int re = teacherService.updateImg(params);
        return fname;
    }

    @RequestMapping(value="/updateInfo.do",method = RequestMethod.POST)
    public JsonResult updateInfo(HttpServletRequest request) throws Exception{
        JsonResult ar = new JsonResult();
        JSONObject params = new JSONObject();
        HttpSession session = request.getSession();
        User user= (User) session.getAttribute("user");
        int id = user.getId();
        params.put("homePhone",request.getParameter("homePhone"));
        params.put("phone",request.getParameter("phone"));
        params.put("location",request.getParameter("location"));
        params.put("id",id);
        teacherService.updateInfo(params);
        ar.setSuccess(true);
        return ar;
    }

    @RequestMapping(value = "/getStudentScore.do",method = RequestMethod.POST)
    public JSONObject getStudentScore(HttpServletRequest request,@RequestParam(value="pn",defaultValue = "1")Integer pn,
                                          @RequestParam(value="pageSize",defaultValue = "10")Integer pageSize) throws Exception{
        PageHelper.startPage(pn,pageSize);
        JSONObject params = new JSONObject();
        params.put("code",request.getParameter("courseCode"));
        params.put("id",Integer.parseInt(request.getParameter("id")));
        List<JSONObject> result = teacherService.getStudentScore(params);
        PageInfo<JSONObject> pageInfo=new PageInfo<JSONObject>(result,10);

        return CommonUtil.successJson(pageInfo);
    }

    @RequestMapping(value = "/getSSAna.do",method = RequestMethod.POST)
    public Map getSSAna(HttpServletRequest request) throws Exception{
        JSONObject params = new JSONObject();
        params.put("type",request.getParameter("type"));
        params.put("code",request.getParameter("courseCode"));
        params.put("id",Integer.parseInt(request.getParameter("id")));
        Map result = teacherService.getSSAna(params);
        return result;
    }

    @RequestMapping(value = "/getStudentScoreT.do",method = RequestMethod.POST)
    public JSONObject getStudentScoreT(HttpServletRequest request,@RequestParam(value="pn",defaultValue = "1")Integer pn,
                                      @RequestParam(value="pageSize",defaultValue = "10")Integer pageSize) throws Exception{
        PageHelper.startPage(pn,pageSize);
        JSONObject params = new JSONObject();
        params.put("code",request.getParameter("courseCode"));
        params.put("id",Integer.parseInt(request.getParameter("id")));
        params.put("type",request.getParameter("type"));
        params.put("spiVal",request.getParameter("spiVal"));
        List<JSONObject> result = teacherService.getStudentScoreT(params);
        PageInfo<JSONObject> pageInfo=new PageInfo<JSONObject>(result,10);

        return CommonUtil.successJson(pageInfo);
    }

}
