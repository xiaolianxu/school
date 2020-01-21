package com.school.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.school.model.User;
import com.school.model.punRecord;
import com.school.model.punRecord1;
import com.school.service.collegeService;
import com.school.service.studentService;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/student")
public class studentController {
    @Autowired
    private collegeService collegeService;
    @Autowired
    private studentService studentService;

    @RequestMapping(value = "/getList.do",method = RequestMethod.POST)
    public JSONObject getList(HttpServletRequest request) throws Exception {
        JSONObject ar = new JSONObject();
        List<JSONObject> a = collegeService.getList();
        List<JSONObject> b = studentService.getAmdClass();
        ar.put("college", a);
        ar.put("admClass", b);
        return ar;
    }

    @RequestMapping(value = "/getDetail.do",method = RequestMethod.POST)
    public  Map getDetail(HttpServletRequest request) throws Exception{
        int id = Integer.parseInt(request.getParameter("id"));
        Map ar = studentService.getDetail(id);
        return ar;
    }

    @RequestMapping(value = "/getStudentList.do",method = RequestMethod.POST)
    public JSONObject getCourseList(HttpServletRequest request, @RequestParam(value = "pn", defaultValue = "1") Integer pn,
                                    @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) throws Exception {
        PageHelper.startPage(pn, pageSize);
        JSONObject params = new JSONObject();
        params.put("col", request.getParameter("col"));
        params.put("dep", request.getParameter("dep"));
        params.put("pro", request.getParameter("pro"));
        params.put("nj", Integer.parseInt(request.getParameter("year")));
        params.put("bj", Integer.parseInt(request.getParameter("bj")));
        List<JSONObject> result = studentService.getStudents(params);

        PageInfo<JSONObject> pageInfo = new PageInfo<JSONObject>(result, 10);

        return CommonUtil.successJson(pageInfo);
    }

    @RequestMapping(value = "/getCreditList.do",method = RequestMethod.POST)
    public JSONObject getCreditList(HttpServletRequest request, @RequestParam(value = "pn", defaultValue = "1") Integer pn,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) throws Exception {
        PageHelper.startPage(pn, pageSize);
        JSONObject params = new JSONObject();
        params.put("col", request.getParameter("col"));
        params.put("dep", request.getParameter("dep"));
        params.put("pro", request.getParameter("pro"));
        params.put("nj", Integer.parseInt(request.getParameter("year")));
        params.put("bj", Integer.parseInt(request.getParameter("bj")));
        List<Map> result = studentService.getCreditList(params);

        PageInfo<Map> pageInfo = new PageInfo<Map>(result, 10);

        return CommonUtil.successJson(pageInfo);
    }

    @RequestMapping(value = "/getCreditCon.do",method = RequestMethod.POST)
    public JSONObject getCreditCon(HttpServletRequest request, @RequestParam(value = "pn", defaultValue = "1") Integer pn,
                                    @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) throws Exception {
        PageHelper.startPage(pn, pageSize);
        JSONObject params = new JSONObject();
        if(request.getParameter("flag").equals("0")){
            params.put("bj",Integer.parseInt(request.getParameter("bj")));
            params.put("flag",0);
        }else if(request.getParameter("flag").equals("1")){
            String type = request.getParameter("bj");
            String[] valueArr = type.split(",");
            int[] intArr = new int[valueArr.length];
            for (int i = 0; i < valueArr.length; i++) {
                intArr[i] = Integer.parseInt(valueArr[i]);
            }
            params.put("bj",intArr);
            params.put("flag",1);
        }
        List<Map> result = studentService.getCreditCon(params);

        PageInfo<Map> pageInfo = new PageInfo<Map>(result, 10);

        return CommonUtil.successJson(pageInfo);
    }

    @RequestMapping(value = "/getStudentBj.do",method = RequestMethod.POST)
    public JSONObject getStudentBj(HttpServletRequest request, @RequestParam(value = "pn", defaultValue = "1") Integer pn,
                                    @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) throws Exception {
        PageHelper.startPage(pn, pageSize);
        JSONObject params = new JSONObject();
        if(request.getParameter("flag").equals("0")){
            params.put("bj",Integer.parseInt(request.getParameter("bj")));
            params.put("flag",0);
        }else if(request.getParameter("flag").equals("1")){
            String type = request.getParameter("bj");
            String[] valueArr = type.split(",");
            int[] intArr = new int[valueArr.length];
            for (int i = 0; i < valueArr.length; i++) {
                intArr[i] = Integer.parseInt(valueArr[i]);
            }
            params.put("bj",intArr);
            params.put("flag",1);
        }
        List<JSONObject> result = studentService.getStudentBj(params);
        PageInfo<JSONObject> pageInfo = new PageInfo<JSONObject>(result, 10);
        return CommonUtil.successJson(pageInfo);
    }

    @RequestMapping(value = "/getStudentSpB.do",method = RequestMethod.POST)
    public JSONObject getStudentSpB(HttpServletRequest request, @RequestParam(value = "pn", defaultValue = "1") Integer pn,
                                   @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) throws Exception {
        PageHelper.startPage(pn, pageSize);
        String str = request.getParameter("bj");
        String[] valueArr = str.split(",");
        int[] intArr = new int[valueArr.length];
        for (int i = 0; i < valueArr.length; i++) {
            intArr[i] = Integer.parseInt(valueArr[i]);
        }
        JSONObject params = new JSONObject();
        params.put("bj",intArr);
        params.put("type", request.getParameter("type"));
        params.put("spiVal", request.getParameter("spiVal"));
        List<JSONObject> result = studentService.getStudentSpB(params);
        PageInfo<JSONObject> pageInfo = new PageInfo<JSONObject>(result, 10);
        return CommonUtil.successJson(pageInfo);
    }

    @RequestMapping(value = "/getStudentSp.do",method = RequestMethod.POST)
    public JSONObject getStudentSp(HttpServletRequest request, @RequestParam(value = "pn", defaultValue = "1") Integer pn,
                                   @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) throws Exception {
        PageHelper.startPage(pn, pageSize);
        JSONObject params = new JSONObject();
        params.put("type", request.getParameter("type"));
        params.put("spiVal", request.getParameter("spiVal"));
        List<JSONObject> result = studentService.getStudentSp(params);

        PageInfo<JSONObject> pageInfo = new PageInfo<JSONObject>(result, 10);

        return CommonUtil.successJson(pageInfo);
    }

    @RequestMapping(value = "/getCreditListSp.do",method = RequestMethod.POST)
    public JSONObject getCreditListSp(HttpServletRequest request, @RequestParam(value = "pn", defaultValue = "1") Integer pn,
                                   @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) throws Exception {
        PageHelper.startPage(pn, pageSize);
        JSONObject params = new JSONObject();
        params.put("type", request.getParameter("type"));
        params.put("spiVal", request.getParameter("spiVal"));
        List<Map> result = studentService.getCreditListSp(params);

        PageInfo<Map> pageInfo = new PageInfo<Map>(result, 10);

        return CommonUtil.successJson(pageInfo);
    }

    @RequestMapping(value = "/getCreditConSp.do",method = RequestMethod.POST)
    public JSONObject getCreditConSp(HttpServletRequest request, @RequestParam(value = "pn", defaultValue = "1") Integer pn,
                                      @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) throws Exception {
        PageHelper.startPage(pn, pageSize);
        String str = request.getParameter("bj");
        String[] valueArr = str.split(",");
        int[] intArr = new int[valueArr.length];
        for (int i = 0; i < valueArr.length; i++) {
            intArr[i] = Integer.parseInt(valueArr[i]);
        }
        JSONObject params = new JSONObject();
        params.put("bj",intArr);
        params.put("type", request.getParameter("type"));
        params.put("spiVal", request.getParameter("spiVal"));
        List<Map> result = studentService.getCreditConSp(params);

        PageInfo<Map> pageInfo = new PageInfo<Map>(result, 10);

        return CommonUtil.successJson(pageInfo);
    }

    @RequestMapping(value = "/getStudentC.do",method = RequestMethod.POST)
    public List<Map> getStudentC(HttpServletRequest request) throws Exception{
        JSONObject params = new JSONObject();
        params.put("type", request.getParameter("type"));
        params.put("spiVal", request.getParameter("spiVal"));
        List<Map> ar = studentService.getStudentC(params);
        return ar;
    }

    @RequestMapping(value = "/getCouSt.do",method = RequestMethod.POST)
    public List<Map> getCouSt(HttpServletRequest request) throws Exception{
        String str = request.getParameter("bj");
        String[] valueArr = str.split(",");
        int[] intArr = new int[valueArr.length];
        for (int i = 0; i < valueArr.length; i++) {
            intArr[i] = Integer.parseInt(valueArr[i]);
        }
        JSONObject params = new JSONObject();
        params.put("bj",intArr);
        params.put("type", request.getParameter("type"));
        params.put("spiVal", request.getParameter("spiVal"));
        List<Map> ar = studentService.getCouSt(params);
        return ar;
    }

    @RequestMapping(value = "/getUserInfo.do",method = RequestMethod.POST)
    public Map getUserInfo(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        User user= (User) session.getAttribute("user");
        int id = user.getId();
        Map ar = studentService.getUserInfo(id);
        ar.put("id",id);
        return ar;
    }

    @RequestMapping(value = "/matchStudent.do",method = RequestMethod.POST)
    public Map matchStudent(HttpServletRequest request) throws Exception {
        JSONObject params = new JSONObject();
        params.put("id",Integer.parseInt(request.getParameter("id")));
        params.put("name",request.getParameter("name"));
        Map ar = studentService.matchStudent(params);
        return ar;
    }

    @RequestMapping(value = "/punEnt.do",method = RequestMethod.POST)
    public int punEnt(HttpServletRequest request) throws Exception {
        JSONObject params = new JSONObject();
        HttpSession session = request.getSession();
        User user= (User) session.getAttribute("user");
        params.put("sId",Integer.parseInt(request.getParameter("sId")));
        params.put("level",request.getParameter("level"));
        params.put("descr",request.getParameter("descr"));
        params.put("reviewId",user.getId());
        int ar = studentService.punEnt(params);
        return ar;
    }

    @RequestMapping(value = "/getStudentInfo.do",method = RequestMethod.POST)
    public Map<String, Object> getStudentInfo(HttpServletRequest request) throws Exception{
        HttpSession session = request.getSession();
        User user= (User) session.getAttribute("user");
        int id = user.getId();
        Map ar = studentService.getStudentInfo(id);
        String img = id + "_" + studentService.getImage(id);
        ar.put("image", img);
        return ar;
    }

    @RequestMapping(value = "/getStudentInfoS.do",method = RequestMethod.POST)
    public Map<String, Object> getStudentInfoS(HttpServletRequest request) throws Exception{
        int id = Integer.parseInt(request.getParameter("id"));
        Map ar = studentService.getStudentInfo(id);
        String img = id + "_" + studentService.getImage(id);
        ar.put("image", img);
        return ar;
    }

    @RequestMapping(value="/updateInfo.do",method = RequestMethod.POST)
    public JsonResult updateInfo(HttpServletRequest request) throws Exception{
        JsonResult ar = new JsonResult();
        JSONObject params = new JSONObject();
        HttpSession session = request.getSession();
        User user= (User) session.getAttribute("user");
        int id = user.getId();
        params.put("homePhone",request.getParameter("homePhone"));
        params.put("mName",request.getParameter("mName"));
        params.put("mOffice",request.getParameter("mOffice"));
        params.put("fName",request.getParameter("fName"));
        params.put("fOffice",request.getParameter("fOffice"));
        params.put("fPhone",request.getParameter("fPhone"));
        params.put("mPhone",request.getParameter("mPhone"));
        params.put("address",request.getParameter("address"));
        params.put("id",id);
        studentService.updateInfo(params);
        ar.setSuccess(true);
        return ar;
    }

    @RequestMapping(value="/updateInfoS.do",method = RequestMethod.POST)
    public JsonResult updateInfoS(HttpServletRequest request) throws Exception{
        JsonResult ar = new JsonResult();
        JSONObject params = new JSONObject();
        params.put("department",request.getParameter("department"));
        params.put("profession",request.getParameter("profession"));
        params.put("admClass",request.getParameter("admClass"));
        params.put("status",request.getParameter("status"));
        params.put("grade",request.getParameter("grade"));
        params.put("id",Integer.parseInt(request.getParameter("id")));
        studentService.updateInfoS(params);
        ar.setSuccess(true);
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
        int re = studentService.updateImg(params);
        return fname;
    }

    @RequestMapping(value = "/prePr.do",method = RequestMethod.POST)
    public JSONObject prePr(HttpServletRequest request) throws Exception{
        HttpSession session = request.getSession();
        User user= (User) session.getAttribute("user");
        int id = user.getId();
        JSONObject a = studentService.prePr(id);
        return a;
    }

    @RequestMapping(value = "/prePr1.do",method = RequestMethod.POST)
    public JSONObject prePr1(HttpServletRequest request) throws Exception{
        int id = Integer.parseInt(request.getParameter("id"));
        JSONObject a = studentService.prePr(id);
        return a;
    }

    @RequestMapping(value = "/punRecord",method = RequestMethod.POST)
    public JSONObject punRecord(HttpServletRequest request, @RequestParam(value = "pn", defaultValue = "1") Integer pn,
                                     @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) throws Exception {
        PageHelper.startPage(pn, pageSize);
        String status = request.getParameter("status");
        List<punRecord> result = studentService.punRecord(status);

        PageInfo<punRecord> pageInfo = new PageInfo<punRecord>(result, 10);

        return CommonUtil.successJson(pageInfo);
    }
    @RequestMapping(value = "/punRecordSp",method = RequestMethod.POST)
    public JSONObject punRecordSp(HttpServletRequest request, @RequestParam(value = "pn", defaultValue = "1") Integer pn,
                                @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) throws Exception {
        PageHelper.startPage(pn, pageSize);
        JSONObject params = new JSONObject();
        params.put("status",request.getParameter("status"));
        params.put("type",request.getParameter("type"));
        params.put("spiVal",request.getParameter("spiVal"));
        List<punRecord> result = studentService.punRecordSp(params);

        PageInfo<punRecord> pageInfo = new PageInfo<punRecord>(result, 10);

        return CommonUtil.successJson(pageInfo);
    }

    @RequestMapping(value = "/writeOff.do",method = RequestMethod.POST)
    public int writeOff(HttpServletRequest request) throws Exception{
        JSONObject params = new JSONObject();
        params.put("id",Integer.parseInt(request.getParameter("id")));
        params.put("reason",request.getParameter("reason"));
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        params.put("dId",user.getId());
        int a = studentService.writeOff(params);
        return a;
    }

    @RequestMapping(value = "/getRecord.do",method = RequestMethod.POST)
    public punRecord1 getRecord(HttpServletRequest request) throws Exception{
        int id = Integer.parseInt(request.getParameter("id"));
        punRecord1 a = studentService.getRecord(id);
        return a;
    }
}
