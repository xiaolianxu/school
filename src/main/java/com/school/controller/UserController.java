package com.school.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.school.model.User;
import com.school.model.analysis;
import com.school.model.analysis2;
import com.school.model.rewRecord;
import com.school.service.UserService;
import com.school.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    public static String salt = "fdsafagfdgv43532ju76jMdsvfvr122";

    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    public JsonResult userLogin(@RequestBody JSONObject params, HttpServletRequest request) throws Exception {
        JsonResult ar = new JsonResult();
        if (params.get("id") == null || params.get("id").equals("")) {
            ar.setSize(0);
            ar.setData("用户名不能为空");
            ar.setSuccess(false);
        } else if ("".equals(params.get("password")) || params.get("password") == null) {
            ar.setSize(0);
            ar.setData("密码不能为空");
            ar.setSuccess(false);
        } else {
            String calPass = MD5Util.formPassToDBPass(params.get("password").toString(), salt);
            params.put("password",calPass);
            User user = this.userService.selectUser(params);
            if (null == user) {
                ar.setSize(0);
                ar.setData("用户名或密码错误");
                ar.setSuccess(false);
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                ar.setData(user);
                ar.setSuccess(true);
            }
        }
        return ar;
    }

    @RequestMapping(value = "/updatePsd.do", method = RequestMethod.POST)
    public JsonResult updatePsd(@RequestBody JSONObject params, HttpServletRequest request) throws Exception {
        JsonResult ar = new JsonResult();
        if (params.get("opsd") == null || params.get("opsd").equals("")) {
            ar.setSize(0);
            ar.setData("旧密码不能为空！");
            ar.setSuccess(false);
        } else if (params.get("npsd") == null || params.get("npsd").equals("") || params.get("npsd2") == null || params.get("npsd2").equals("")) {
            ar.setSize(0);
            ar.setData("新密码不能为空！");
            ar.setSuccess(false);
        } else {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            int id = user.getId();
            JSONObject pa = new JSONObject();
            pa.put("id", id);
            String calPass = MD5Util.formPassToDBPass(params.get("opsd").toString(), salt);
            pa.put("password",calPass);
            User a = this.userService.selectUser(pa);
            if (null == a) {
                ar.setSize(0);
                ar.setData("旧密码错误！");
                ar.setSuccess(false);
            } else {
                if (params.get("npsd").equals(params.get("npsd2"))) {
                    String psd = MD5Util.formPassToDBPass(params.get("npsd").toString(), salt);
                    pa.put("password", psd);
                    int i = this.userService.updatePsd(pa);
                    ar.setSize(0);
                    ar.setData("密码修改成功！");
                    ar.setSuccess(true);
                } else {
                    ar.setSize(0);
                    ar.setData("两次输入密码不一致！");
                    ar.setSuccess(false);
                }
            }
        }
        return ar;
    }

    @RequestMapping(value = "/getFemale.do", method = RequestMethod.POST)
    public JsonResult selectSex(@RequestBody JSONObject params, HttpServletRequest request) throws Exception {
        JsonResult ar = new JsonResult();
        analysis ana = this.userService.selectSex(params);
        ar.setData(ana);
        ar.setSuccess(true);
        return ar;
    }

    @RequestMapping(value = "/getMap.do", method = RequestMethod.POST)
    public JsonResult selectAna(@RequestBody JSONObject params, HttpServletRequest request) throws Exception {
        JsonResult ar = new JsonResult();
        List<analysis2> ana = this.userService.selectAna(params);
        ar.setData(ana);
        ar.setSuccess(true);
        return ar;
    }

    @RequestMapping(value = "/quit.do", method = RequestMethod.POST)
    public JSONObject logoutSystem(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        return CommonUtil.successJson();
    }

    @RequestMapping(value = "/getRole.do", method = RequestMethod.POST)
    public int getRole(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int role = user.getRole();
        return role;
    }

    @RequestMapping(value = "/getFDY.do", method = RequestMethod.POST)
    public List<Map> getFDY(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int role = user.getRole();
        List<Map> ar = new ArrayList<>();
        if (role == 1) {
            JSONObject params = new JSONObject();
            params.put("id", Integer.parseInt(request.getParameter("user_id")));
            params.put("name", request.getParameter("login_name"));
            ar = userService.getFDY(params);
        }
        return ar;
    }

    @RequestMapping(value = "/getAllFDY.do", method = RequestMethod.POST)
    public List<Map> getAllFDY(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int role = user.getRole();
        List<Map> ar = new ArrayList<>();
        if (role == 1) {
            ar = userService.getAllFDY();
        }
        return ar;
    }

    @RequestMapping(value = "/upLoadClass.do")
    @ResponseBody
    public JSONObject upLoadClass(HttpServletRequest req) throws Exception {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        int role = user.getRole();
        if (role == 1) {
            List<String> reque = new ArrayList<String>(){{
                add("班号");add("辅导员id");add("学院");add("系别");add("专业");add("班级号");add("学生人数");add("年级");
            }};
            JSONObject result = ReadExcelUtil.readExcelInfo(req,reque);
            if(result.get("returnCode").toString().equals("100")){
                List<Map> list = (List<Map>) result.get("returnData");
                JSONObject i = userService.upLoadClass(list);
                return i;
            }else{
                return result;
            }
        } else {
            return CommonUtil.errorJson(ErrorEnum.E_502);
        }
    }

    @RequestMapping(value = "/upLoadClass1.do", method = RequestMethod.POST)
    public JSONObject upLoadClass1(@RequestBody JSONObject params, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int role = user.getRole();
        if (role == 1) {
            JSONObject a = userService.upLoadClass1(params);
            return a;
        }
        return CommonUtil.errorJson(ErrorEnum.E_502);
    }

    @RequestMapping(value = "/upLoadScore.do")
    @ResponseBody
    public JSONObject upLoadScore(HttpServletRequest req) throws Exception {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        int role = user.getRole();
        int userId = user.getId();
        if (role == 2) {
            List<String> reque = new ArrayList<String>(){{
                add("学号");add("课程id");add("成绩");add("补考成绩");add("是否重修");add("备注");add("补考备注");
            }};
            JSONObject result = ReadExcelUtil.readExcelInfo(req,reque);
            if(result.get("returnCode").toString().equals("100")){
                List<Map> list = (List<Map>) result.get("returnData");
                JSONObject i = userService.upLoadScore(list,userId);
                return i;
            }else{
                return result;
            }
        } else {
            return CommonUtil.errorJson(ErrorEnum.E_502);
        }
    }

    @RequestMapping(value = "/upLoadScore1.do", method = RequestMethod.POST)
    public JSONObject upLoadScore1(@RequestBody JSONObject params, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int role = user.getRole();
        int id = user.getId();
        if (role == 2) {
            JSONObject a = userService.upLoadScore1(params,id);
            return a;
        }
        return CommonUtil.errorJson(ErrorEnum.E_502);
    }

    @RequestMapping(value = "/upLoadCourse1.do", method = RequestMethod.POST)
    public JSONObject upLoadCourse1(@RequestBody JSONObject params, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int role = user.getRole();
        if (role == 1) {
            JSONObject a = userService.upLoadCourse1(params);
            return a;
        }
        return CommonUtil.errorJson(ErrorEnum.E_502);
    }

    @RequestMapping(value = "/upLoadCourseInfo1.do", method = RequestMethod.POST)
    public JSONObject upLoadCourseInfo1(@RequestBody JSONObject params, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int role = user.getRole();
        if (role == 1) {
            JSONObject a = userService.upLoadCourseInfo1(params);
            return a;
        }
        return CommonUtil.errorJson(ErrorEnum.E_502);
    }

    @RequestMapping(value = "/upLoadM.do", method = RequestMethod.POST)
    public JSONObject upLoadM(@RequestBody JSONObject params, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int role = user.getRole();
        if (role == 1) {
             JSONObject a = userService.upLoadM(params);
             return a;
        }
        return CommonUtil.errorJson(ErrorEnum.E_502);
    }
    @RequestMapping(value = "/upLoadM1.do", method = RequestMethod.POST)
    public JSONObject upLoadM1(@RequestBody JSONObject params, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int role = user.getRole();
        if (role == 1) {
            JSONObject a = userService.upLoadM1(params);
            return a;
        }
        return CommonUtil.errorJson(ErrorEnum.E_502);
    }
    @RequestMapping(value = "/upLoadCourse.do")
    @ResponseBody
    public JSONObject upLoadCourse(HttpServletRequest req) throws Exception {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        int role = user.getRole();
        if (role == 1) {
            List<String> reque = new ArrayList<String>(){{
                add("学年");add("学期");add("课程代码");	add("课程名称");add("课程性质");	add("学分");add("开课学院");
                add("成绩类别");
            }};
            JSONObject result = ReadExcelUtil.readExcelInfo(req,reque);
            if(result.get("returnCode").toString().equals("100")){
                List<Map> list = (List<Map>) result.get("returnData");
                JSONObject i = userService.upLoadCourse(list);
                return i;
            }else{
                return result;
            }
        } else {
            return CommonUtil.errorJson(ErrorEnum.E_502);
        }
    }

    @RequestMapping(value = "/upLoadCourseInfo.do")
    @ResponseBody
    public JSONObject upLoadCourseInfo(HttpServletRequest req) throws Exception {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        int role = user.getRole();
        if (role == 1) {
            List<String> reque = new ArrayList<String>(){{
                add("课程id");add("课程代码");add("教授人id");add("授课时间");add("授课地点");add("周学时");add("选课人数");
            }};
            JSONObject result = ReadExcelUtil.readExcelInfo(req,reque);
            if(result.get("returnCode").toString().equals("100")){
                List<Map> list = (List<Map>) result.get("returnData");
                JSONObject i = userService.upLoadCourseInfo(list);
                return i;
            }else{
                return result;
            }
        } else {
            return CommonUtil.errorJson(ErrorEnum.E_502);
        }
    }

    @RequestMapping(value = "/upLoad.do")
    @ResponseBody
    public JSONObject upload(HttpServletRequest req) throws Exception {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        int role = user.getRole();
        if (role == 1) {
            List<String> reque = new ArrayList<String>(){{
                add("姓名");add("民族");add("政治面貌");add("来源地区");add("来源省");add("学院");add("系");add("专业名称");
                add("身份证号");add("学历层次");add("学籍状态");add("手机号码");add("考生号");add("家庭电话");add("入学日期");add("出生日期");
                add("母亲（监护人2）姓名");add("母亲（监护人2）单位");add("母亲（监护人2）身份证号");add("父亲（监护人1）姓名");
                add("父亲（监护人1）单位");add("父亲（监护人1）身份证号");add("母亲（监护人2）电话或手机");add("父亲（监护人1）电话或手机");
                add("家庭所在地（/省/县）");add("家庭地址");add("行政班");add("学制");add("当前所在级");add("邮政编码");add("学号");add("性别");
            }};
            JSONObject result = ReadExcelUtil.readExcelInfo(req,reque);
            if(result.get("returnCode").toString().equals("100")){
                List<Map> list = (List<Map>) result.get("returnData");
                JSONObject i = userService.putUser(list);
                return i;
            }else{
                return result;
            }
        } else {
            return CommonUtil.errorJson(ErrorEnum.E_502);
        }
    }

    @RequestMapping(value = "/upLoad1.do")
    @ResponseBody
    public JSONObject upload1(HttpServletRequest req) throws Exception {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        String roles = req.getParameter("role");
        int role = user.getRole();
        if (role == 1) {
            List<String> reque = new ArrayList<String>(){{
                add("工号");add("名字");add("性别");	add("出生日期");add("民族");	add("职称");add("入职日期");
                add("是否在职");add("在编");	add("最高学历");add("毕业院校");	add("联系方式");add("政治面貌");
                add("身份证号");add("部门");	add("家庭地址");add("家庭所在地");add("来源地区");add("来源省");
                add("所属学院");add("研究专业");	add("婚姻状况");add("英语等级");	add("英语成绩");add("是否师范生");
                add("家庭电话");add("签订合同");
            }};
            JSONObject result = ReadExcelUtil.readExcelInfo(req,reque);
            if(result.get("returnCode").toString().equals("100")){
                List<Map> list = (List<Map>) result.get("returnData");
                JSONObject i = userService.putUser1(list,roles);
                return i;
            }else{
                return result;
            }
        } else {
            return CommonUtil.errorJson(ErrorEnum.E_502);
        }
    }

    @RequestMapping(value = "/upLoadReward.do")
    @ResponseBody
    public JSONObject upLoadReward(HttpServletRequest req) throws Exception {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        int role = user.getRole();
        if (role == 1) {
            List<String> reque = new ArrayList<String>(){{
                add("获奖人id");add("获奖内容");add("获奖时间");
            }};
            JSONObject result = ReadExcelUtil.readExcelInfo(req,reque);
            if(result.get("returnCode").toString().equals("100")){
                List<Map> list = (List<Map>) result.get("returnData");
                int id = user.getId();
                JSONObject i = userService.upLoadReward(list,id);
                return i;
            }else{
                return result;
            }
        } else {
            return CommonUtil.errorJson(ErrorEnum.E_502);
        }
    }

    @RequestMapping(value = "/upLoadReward1.do", method = RequestMethod.POST)
    public JSONObject upLoadReward1(@RequestBody JSONObject params, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int role = user.getRole();
        if (role == 1) {
            params.put("reviewId",user.getId());
            JSONObject a = userService.upLoadReward1(params);
            return a;
        }
        return CommonUtil.errorJson(ErrorEnum.E_502);
    }

    @RequestMapping(value = "/getRewRecord.do",method = RequestMethod.POST)
    public JSONObject getRewRecord(HttpServletRequest request, @RequestParam(value = "pn", defaultValue = "1") Integer pn,
                                   @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) throws Exception {
        PageHelper.startPage(pn, pageSize);
        JSONObject params = new JSONObject();
        params.put("type", request.getParameter("type"));
        params.put("spiVal", request.getParameter("spiVal"));
        List<rewRecord> result = userService.getRewRecord(params);

        PageInfo<rewRecord> pageInfo = new PageInfo<rewRecord>(result, 10);

        return CommonUtil.successJson(pageInfo);
    }
}
