package com.school.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.school.model.User;
import com.school.model.courseInfo;
import com.school.service.courseService;
import com.school.util.CommonUtil;
import com.school.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/course")
public class courseController {
    @Autowired
    private courseService courseService;

    @RequestMapping(value = "/getCourseList.do",method = RequestMethod.POST)
    public JSONObject getCourseList(HttpServletRequest request,@RequestParam(value="pn",defaultValue = "1")Integer pn,
                                    @RequestParam(value="pageSize",defaultValue = "10")Integer pageSize) throws Exception {
        PageHelper.startPage(pn,pageSize);
        JSONObject params = new JSONObject();
        params.put("college",request.getParameter("college"));
        params.put("year",request.getParameter("schoolYear"));
        params.put("nature", request.getParameter("courseType"));
        params.put("semester",Integer.parseInt(request.getParameter("semester")));
        List<JSONObject> result = courseService.getCourse(params);
        PageInfo<JSONObject> pageInfo=new PageInfo<JSONObject>(result,10);

        return CommonUtil.successJson(pageInfo);
    }

    @RequestMapping(value = "/getCourseListT.do",method = RequestMethod.POST)
    public JSONObject getCourseListT(HttpServletRequest request,@RequestParam(value="pn",defaultValue = "1")Integer pn,
                                     @RequestParam(value="pageSize",defaultValue = "10")Integer pageSize) throws Exception{
        PageHelper.startPage(pn,pageSize);
        JSONObject params = new JSONObject();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int id = user.getId();
        params.put("id",id);
        params.put("year",request.getParameter("schoolYear"));
        params.put("semester",Integer.parseInt(request.getParameter("semester")));
        List<JSONObject> result = courseService.getCourseT(params);

        PageInfo<JSONObject> pageInfo=new PageInfo<JSONObject>(result,10);

        return CommonUtil.successJson(pageInfo);
    }

    @RequestMapping(value = "/getCourseListR.do",method = RequestMethod.POST)
    public List<JSONObject> getCourseListR(HttpServletRequest request) throws  Exception{
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int id = user.getId();
        List<JSONObject> ar = courseService.getCourseR(id);
        return ar;
    }

    @RequestMapping(value = "/getCourseSp.do",method = RequestMethod.POST)
    public Map<String,Object> getCourseSp(HttpServletRequest request,@RequestParam(value="pn",defaultValue = "1")Integer pn,
                                          @RequestParam(value="pageSize",defaultValue = "10")Integer pageSize) throws Exception{
        PageHelper.startPage(pn,pageSize);
        JSONObject params = new JSONObject();
        params.put("type",request.getParameter("type"));
        params.put("spiVal",request.getParameter("spiVal"));
        List<JSONObject> result = courseService.getCourseSp(params);
        PageInfo<JSONObject> pageInfo=new PageInfo<JSONObject>(result,10);

        return CommonUtil.successJson(pageInfo);
    }

    @RequestMapping(value = "/getCourseC.do",method = RequestMethod.POST)
    public Map<String, Object>  getCourseC(HttpServletRequest request) throws Exception{
        JSONObject params = new JSONObject();
        params.put("type",request.getParameter("type"));
        params.put("spiVal",request.getParameter("spiVal"));
        List<JSONObject> ar = courseService.getCourseC(params);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("data", ar);
        resultMap.put("code", "0");
        resultMap.put("msg", "");
        resultMap.put("count", ar.size());
        return resultMap;
    }

    @RequestMapping(value = "/getCourseSpT.do",method = RequestMethod.POST)
    public Map<String,Object> getCourseSpT(HttpServletRequest request,@RequestParam(value="pn",defaultValue = "1")Integer pn,
                                          @RequestParam(value="pageSize",defaultValue = "10")Integer pageSize) throws Exception{
        PageHelper.startPage(pn,pageSize);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int id = user.getId();
//        int id = 1301;
        JSONObject params = new JSONObject();
        params.put("id",id);
        params.put("type",request.getParameter("type"));
        params.put("spiVal",request.getParameter("spiVal"));
        List<JSONObject> result = courseService.getCourseSpT(params);
        PageInfo<JSONObject> pageInfo=new PageInfo<JSONObject>(result,10);

        return CommonUtil.successJson(pageInfo);
    }

    @RequestMapping(value = "/getCourseInfo.do",method = RequestMethod.POST)
    public Map<String, Object>  getCourseInfo(HttpServletRequest request) throws Exception{
        String code = request.getParameter("code");
        List<courseInfo> ar = courseService.getCourseInfo(code);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("data", ar);
        resultMap.put("code", "0");
        resultMap.put("msg", "");
        resultMap.put("count", ar.size());
        return resultMap;
    }

    @RequestMapping(value = "/getAll.do",method = RequestMethod.POST)
    public JSONObject getAll(HttpServletRequest request)throws Exception{
        JSONObject ar = new JSONObject();
        ar = courseService.getAll();
        return ar;
    }

    @RequestMapping(value = "/getCourseType.do")
    public List<Map> getCourseType(HttpServletRequest request) throws Exception{
        List<Map> ar = courseService.getCourseType();
        return ar;
    }

    @RequestMapping(value = "/getB.do")
    public JSONObject getB(HttpServletRequest request)throws Exception{
        JSONObject ar = new JSONObject();
        ar = courseService.getB();
        return ar;
    }

    @RequestMapping(value = "/getAllCourse.do")
    public List<Map> getAllCourse(HttpServletRequest request) throws Exception{
        List<Map> ar = courseService.getAllCourse();
        return ar;
    }

    @RequestMapping(value = "/getTeacher.do")
    public List<Map> getTeacher(HttpServletRequest request) throws Exception{
        List<Map> ar = courseService.getTeacher();
        return ar;
    }

    @RequestMapping(value = "/getCourseBj.do")
    public JsonResult getCourseBj(HttpServletRequest request) throws Exception{
        JsonResult ar = new JsonResult();
        JSONObject params = new JSONObject();
        int bj = Integer.parseInt(request.getParameter("bj"));
        params.put("bj",bj);
        params.put("year",request.getParameter("schoolYear"));
        params.put("semester",Integer.parseInt(request.getParameter("semester")));
        List<Map> course = courseService.getCourseBj(params);
        if(course.size()==0){
            ar.setSize(0);
            ar.setData("无数据！");
            ar.setSuccess(false);
        }else{
            List<JSONObject> zz = new ArrayList<>();
            for(int i=0;i<course.size();i++){
                JSONObject pp = new JSONObject();
                pp.put("bj",bj);
                pp.put("course",course.get(i).get("course_code"));
                pp.put("type",course.get(i).get("type"));
                JSONObject result = courseService.getScoreBj(pp);
                zz.add(result);
            }
            ar.setSize(zz.size());
            ar.setData(zz);
            ar.setSuccess(true);
        }
        return ar;
    }

    @RequestMapping(value = "/getCourseSt.do")
    public JsonResult getCourseSt(HttpServletRequest request) throws Exception{
        JsonResult ar = new JsonResult();
        JSONObject params = new JSONObject();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        params.put("id",user.getId());
        params.put("year",request.getParameter("schoolYear"));
        params.put("semester",Integer.parseInt(request.getParameter("semester")));
        List<Map> course = courseService.getCourseSt(params);
        if(course.size()==0){
            ar.setSize(0);
            ar.setData("无数据！");
            ar.setSuccess(false);
        }else{
            List<JSONObject> zz = new ArrayList<>();
            for(int i=0;i<course.size();i++){
                JSONObject pp = new JSONObject();
                pp.put("course",course.get(i).get("course_code"));
                pp.put("type",course.get(i).get("type"));
                pp.put("courseId",course.get(i).get("course_id"));
                pp.put("id",user.getId());
                JSONObject result = courseService.getScoreSt(pp);
                zz.add(result);
            }
            ar.setSize(zz.size());
            ar.setData(zz);
            ar.setSuccess(true);
        }
        return ar;
    }
}
