package com.school.service;

import com.alibaba.fastjson.JSONObject;
import com.school.dao.UserDao;
import com.school.model.User;
import com.school.model.analysis;
import com.school.model.analysis2;
import com.school.model.rewRecord;
import com.school.util.CommonUtil;
import com.school.util.ErrorEnum;
import com.school.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    public static String salt = "fdsafagfdgv43532ju76jMdsvfvr122";

    public User selectUser(JSONObject params) throws Exception{
        return this.userDao.selectUser(params);
    }
    public analysis selectSex(JSONObject params) throws Exception{
        return this.userDao.selectSex(params);
    }
    public List<analysis2> selectAna(JSONObject params) throws Exception{
        return this.userDao.selectAna(params);
    }

    public int updatePsd(JSONObject params) throws Exception{
        return this.userDao.updatePsd(params);
    }

    public List<Map> getFDY(JSONObject params) throws Exception{
        return this.userDao.getFDY(params);
    }

    public List<Map> getAllFDY() throws Exception{
        return this.userDao.getAllFDY();
    }

    public JSONObject upLoadM(JSONObject params) throws Exception{
        int userId = Integer.parseInt(params.get("userId").toString());
        params.put("userId",userId);
        String u = params.get("idCard").toString();
        Map b = new HashMap();
        b.put("userId",userId);
        String calPass = MD5Util.formPassToDBPass(u.substring(u.length() - 6), salt);
        b.put("pwd",calPass);
        b.put("role",4);
        int sex = params.get("sex").toString().equals("女")?0:1;
        params.put("sex",sex);
        int admClass = Integer.parseInt(params.get("admClass").toString());
        params.put("admClass",admClass);
        int scSystem = Integer.parseInt(params.get("scSystem").toString());
        params.put("scSystem",scSystem);
        int grade =  Integer.parseInt(params.get("grade").toString());
        params.put("grade",grade);
        int postalCode =  Integer.parseInt(params.get("postalCode").toString());
        params.put("postalCode",postalCode);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date admission = format.parse(params.get("admission").toString());
        params.put("admission",admission);
        Date birthDay = format.parse(params.get("birthDay").toString());
        params.put("birthDay",birthDay);
        int a = this.userDao.putUser3(params);
        int c = this.userDao.addUserImage3(b);
        int d = this.userDao.addUser3(b);
        return CommonUtil.successJson();
    }

    public JSONObject upLoadM1(JSONObject params) throws Exception{
        int userId = Integer.parseInt(params.get("userId").toString());
        params.put("userId",userId);
        String u = params.get("idCard").toString();
        Map b = new HashMap();
        b.put("userId",userId);
        String calPass = MD5Util.formPassToDBPass(u.substring(u.length() - 6), salt);
        b.put("pwd",calPass);
        b.put("role",Integer.parseInt(params.get("role").toString()));
        if(params.get("english").toString().equals("-1")){
            params.put("english",null);
        }
        if(params.get("engScore").toString().equals("-1")){
            params.put("engScore",null);
        }else{
            params.put("engScore",Double.parseDouble(params.get("engScore").toString()));
        }
        if(params.get("college").toString().equals("-1")){
            params.put("college",null);
        }
        int sex = params.get("sex").toString().equals("女")?0:1;
        params.put("sex",sex);
        int system  = params.get("system").toString().equals("是")?1:0;
        params.put("system",system);
        int isT  = params.get("isT").toString().equals("是")?1:0;
        params.put("isT",isT);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date data = format.parse(params.get("admission").toString());
        params.put("data",data);
        Date birthDay = format.parse(params.get("birthDay").toString());
        params.put("birthDay",birthDay);
        int a = this.userDao.putUser4(params);
        int c = this.userDao.addUserImage3(b);
        int d = this.userDao.addUser3(b);
        return CommonUtil.successJson();
    }

    public JSONObject putUser(List<Map> p) throws Exception{
        List<Map> id = new ArrayList<>();
        List<Map> result = new ArrayList<>();
        for(int i=0;i<p.size();i++){
            Map params = p.get(i);
            Map a = new HashMap();
            a.put("userName",params.get("姓名").toString());
            a.put("famous",params.get("民族").toString());
            a.put("political",params.get("政治面貌").toString());
            a.put("source",params.get("来源地区").toString());
            a.put("province",params.get("来源省").toString());
            a.put("college",params.get("学院").toString());
            a.put("department",params.get("系").toString());
            a.put("profession",params.get("专业名称").toString());
            a.put("idCard",params.get("身份证号").toString());
            a.put("education",params.get("学历层次").toString());
            a.put("status",params.get("学籍状态").toString());
            a.put("phone",params.get("手机号码").toString());
            a.put("candidate",params.get("考生号").toString());
            a.put("homePhone",params.get("家庭电话").toString());
            a.put("fName",params.get("母亲（监护人2）姓名").toString());
            a.put("fOffice",params.get("母亲（监护人2）单位").toString());
            a.put("fIdCard",params.get("母亲（监护人2）身份证号").toString());
            a.put("mName",params.get("父亲（监护人1）姓名").toString());
            a.put("mOffice",params.get("父亲（监护人1）单位").toString());
            a.put("mIdCard",params.get("父亲（监护人1）身份证号").toString());
            a.put("fPhone",params.get("母亲（监护人2）电话或手机").toString());
            a.put("mPhone",params.get("父亲（监护人1）电话或手机").toString());
            a.put("address",params.get("家庭所在地（/省/县）").toString());
            a.put("location",params.get("家庭地址").toString());
            String u = params.get("身份证号").toString();
            int userId = Integer.parseInt(params.get("学号").toString());
            a.put("userId",userId);
            Map b = new HashMap();
            b.put("userId",userId);
            String calPass = MD5Util.formPassToDBPass(u.substring(u.length() - 6), salt);
            b.put("pwd",calPass);
            b.put("role",4);
            id.add(b);
            a.put("sex",params.get("性别").toString().equals("女")?0:1);
            int admClass = Integer.parseInt(params.get("行政班").toString());
            a.put("admClass",admClass);
            int scSystem = Integer.parseInt(params.get("学制").toString());
            a.put("scSystem",scSystem);
            int grade =  Integer.parseInt(params.get("当前所在级").toString());
            a.put("grade",grade);
            int postalCode =  Integer.parseInt(params.get("邮政编码").toString());
            a.put("postalCode",postalCode);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date admission = format.parse(params.get("入学日期").toString());
            a.put("admission",admission);
            Date birthDay = format.parse(params.get("出生日期").toString());
            a.put("birthDay",birthDay);
            result.add(a);
        }
        int a = this.userDao.putUser(result);
        int b = this.userDao.addUser(id);
        int c = this.userDao.addUserImage(id);
        return CommonUtil.successJson();
    }

    public JSONObject putUser1(List<Map> p,String roles) throws Exception{
        List<Map> id = new ArrayList<>();
        List<Map> result = new ArrayList<>();
        for(int i=0;i<p.size();i++){
            Map params = p.get(i);
            Map a = new HashMap();
            a.put("userName",params.get("名字").toString());
            a.put("famous",params.get("民族").toString());
            a.put("political",params.get("政治面貌").toString());
            a.put("source",params.get("来源地区").toString());
            a.put("province",params.get("来源省").toString());
            if(params.get("所属学院").toString().equals("-1")){
                a.put("college",null);
            }else{
                a.put("college",params.get("所属学院").toString());
            }
            a.put("jobTitle",params.get("职称").toString());
            a.put("major",params.get("研究专业").toString());
            a.put("IDCard",params.get("身份证号").toString());
            a.put("inService",params.get("是否在职").toString());
            a.put("institution",params.get("毕业院校").toString());
            a.put("phone",params.get("联系方式").toString());
            a.put("hEducation",params.get("最高学历").toString());
            if(params.get("家庭电话").toString().equals("-1")){
                a.put("homePhone",null);
            }else{
                a.put("homePhone",params.get("家庭电话").toString());
            }
            a.put("department",params.get("部门").toString());
            a.put("marry",params.get("婚姻状况").toString());
            a.put("contract",params.get("签订合同").toString());
            a.put("system",params.get("在编").toString().equals("是")?1:0);
            if(params.get("英语等级").toString().equals("-1")){
                a.put("english",null);
            }else{
                a.put("english",params.get("英语等级").toString());
            }
            if(params.get("英语成绩").toString().equals("-1")){
                a.put("engScore",null);
            }else{
                a.put("engScore",params.get("英语成绩").toString());
            }
            a.put("isT",params.get("是否师范生").toString().equals("是")?1:0);
            a.put("address",params.get("家庭所在地").toString());
            a.put("location",params.get("家庭地址").toString());
            String u = params.get("身份证号").toString();
            int userId = Integer.parseInt(params.get("工号").toString());
            a.put("userId",userId);
            Map b = new HashMap();
            b.put("userId",userId);
            String calPass = MD5Util.formPassToDBPass(u.substring(u.length() - 6), salt);
            b.put("pwd",calPass);
            b.put("role",Integer.parseInt(roles));
            id.add(b);
            a.put("sex",params.get("性别").toString().equals("女")?0:1);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date data = format.parse(params.get("入职日期").toString());
            a.put("data",data);
            Date birthDay = format.parse(params.get("出生日期").toString());
            a.put("birthDay",birthDay);
            result.add(a);
        }
        int a = this.userDao.putUser1(result);
        int b = this.userDao.addUser(id);
        int c = this.userDao.addUserImage(id);
        return CommonUtil.successJson();
    }
    public JSONObject upLoadCourse(List<Map> p) throws Exception{
        List<Map> result = new ArrayList<>();
        for(int i=0;i<p.size();i++){
            Map params = p.get(i);
            Map a = new HashMap();
            a.put("year",params.get("学年").toString());
            a.put("semester",Integer.parseInt(params.get("学期").toString()));
            a.put("code",params.get("课程代码").toString());
            a.put("name",params.get("课程名称").toString());
            a.put("nature",params.get("课程性质").toString());
            a.put("credit",Double.parseDouble(params.get("学分").toString()));
            a.put("college",params.get("开课学院").toString());
            a.put("type",Integer.parseInt(params.get("成绩类别").toString()));
            result.add(a);
        }
        int a = this.userDao.upLoadCourse(result);
        return CommonUtil.successJson();
    }

    public JSONObject upLoadCourseInfo(List<Map> p) throws Exception{
        List<Map> result = new ArrayList<>();
        for(int i=0;i<p.size();i++){
            Map params = p.get(i);
            Map a = new HashMap();
            a.put("id",Integer.parseInt(params.get("课程id").toString()));
            a.put("code",params.get("课程代码").toString());
            a.put("userId",Integer.parseInt(params.get("教授人id").toString()));
            a.put("data",params.get("授课时间").toString());
            a.put("time",Double.parseDouble(params.get("周学时").toString()));
            a.put("location",params.get("授课地点").toString());
            a.put("people",Integer.parseInt(params.get("选课人数").toString()));
            result.add(a);
        }
        int a = this.userDao.upLoadCourseInfo(result);
        return CommonUtil.successJson();
    }

    public JSONObject upLoadScore(List<Map> p,Integer userId) throws Exception{
        List<Map> result = new ArrayList<>();
        List<Integer> courseId = new ArrayList<>();
        for(int i=0;i<p.size();i++){
            Map params = p.get(i);
            Map a = new HashMap();
            a.put("userId",Integer.parseInt(params.get("学号").toString()));
            a.put("courseId",Integer.parseInt(params.get("课程id").toString()));
            courseId.add(Integer.parseInt(params.get("课程id").toString()));
            a.put("score",params.get("成绩").toString());
            if(params.get("补考成绩").toString().equals("-1")){
                a.put("retest",null);
            }else{
                a.put("retest",params.get("补考成绩").toString());
            }
            if(params.get("是否重修").toString().equals("-1")){
                a.put("rebuild",null);
            }else{
                a.put("rebuild",params.get("是否重修").toString());
            }
            if(params.get("备注").toString().equals("-1")){
                a.put("remarks",null);
            }else{
                a.put("remarks",params.get("备注").toString());
            }
            if(params.get("补考备注").toString().equals("-1")){
                a.put("rRemarks",null);
            }else{
                a.put("rRemarks",params.get("补考备注").toString());
            }
            result.add(a);
        }
        LinkedHashSet<Integer> set = new LinkedHashSet<Integer>(courseId.size());
        set.addAll(courseId);
        courseId.clear();
        courseId.addAll(set);
        int len = courseId.size();
        JSONObject params = new JSONObject();
        params.put("courseId",courseId);
        params.put("userId",userId);
        int b = this.userDao.yanzheng(params);
        if(b<len){
            return CommonUtil.errorJson(ErrorEnum.E_608);
        }
        int a = this.userDao.upLoadScore(result);
        return CommonUtil.successJson();
    }

    public JSONObject upLoadCourse1(JSONObject params) throws Exception {
        int semester = Integer.parseInt(params.get("semester").toString());
        params.put("semester",semester);
        double credit = Double.parseDouble(params.get("credit").toString());
        params.put("credit",credit);
        int type = Integer.parseInt(params.get("type").toString());
        params.put("type",type);
        int a = userDao.upLoadCourse1(params);
        return CommonUtil.successJson();
    }

    public JSONObject upLoadCourseInfo1(JSONObject params) throws Exception {
        int courseId = Integer.parseInt(params.get("courseId").toString());
        params.put("courseId",courseId);
        int userId = Integer.parseInt(params.get("userId").toString());
        params.put("userId",userId);
        double time = Double.parseDouble(params.get("time").toString());
        params.put("time",time);
        int people = Integer.parseInt(params.get("people").toString());
        params.put("people",people);
        int a = userDao.upLoadCourseInfo1(params);
        return CommonUtil.successJson();
    }

    public JSONObject upLoadScore1(JSONObject params,Integer id) throws Exception {
        int courseId = Integer.parseInt(params.get("courseId").toString());
        params.put("courseId",courseId);
        int userId = Integer.parseInt(params.get("userId").toString());
        params.put("userId",userId);
        params.put("id",id);
        Map b = userDao.yanzheng1(params);
        if(b==null){
            return CommonUtil.errorJson(ErrorEnum.E_609);
        }
        Map c = userDao.yanzheng2(params);
        if(c==null){
            return CommonUtil.errorJson(ErrorEnum.E_610);
        }
        int a = userDao.upLoadScore1(params);
        return CommonUtil.successJson();
    }
    public JSONObject upLoadClass(List<Map> p) throws Exception{
        List<Map> result = new ArrayList<>();
        for(int i=0;i<p.size();i++){
            Map params = p.get(i);
            Map a = new HashMap();
            a.put("classId",Integer.parseInt(params.get("班号").toString()));
            a.put("userId",Integer.parseInt(params.get("辅导员id").toString()));
            a.put("col",params.get("学院").toString());
            a.put("dep",params.get("系别").toString());
            a.put("pro",params.get("专业").toString());
            a.put("classNumber",params.get("班级号").toString());
            a.put("studentNumber",Integer.parseInt(params.get("学生人数").toString()));
            a.put("grade",Integer.parseInt(params.get("年级").toString()));
            result.add(a);
        }
        int a = this.userDao.upLoadClass(result);
        return CommonUtil.successJson();
    }

    public JSONObject upLoadClass1(JSONObject params) throws Exception {
        int classId = Integer.parseInt(params.get("classId").toString());
        params.put("classId",classId);
        int userId = Integer.parseInt(params.get("userId").toString());
        params.put("userId",userId);
        int studentNumber = Integer.parseInt(params.get("studentNumber").toString());
        params.put("studentNumber",studentNumber);
        int grade = Integer.parseInt(params.get("grade").toString());
        params.put("grade",grade);
        int a = userDao.upLoadClass1(params);
        return CommonUtil.successJson();
    }

    public JSONObject upLoadReward(List<Map> p,int reviewId) throws Exception{
        List<Map> result = new ArrayList<>();
        for(int i=0;i<p.size();i++){
            Map params = p.get(i);
            Map a = new HashMap();
            a.put("reviewId",reviewId);
            a.put("userId",Integer.parseInt(params.get("获奖人id").toString()));
            a.put("content",params.get("获奖内容").toString());
            a.put("time",params.get("获奖时间").toString());
            result.add(a);
        }
        int a = this.userDao.upLoadReward(result);
        return CommonUtil.successJson();
    }

    public JSONObject upLoadReward1(JSONObject params) throws Exception{
        int userId = Integer.parseInt(params.get("userId").toString());
        params.put("userId",userId);
        int a = userDao.upLoadReward1(params);
        return CommonUtil.successJson();
    }

    public List<rewRecord> getRewRecord(JSONObject params) throws Exception{
        List<rewRecord> ar = userDao.getRewRecord(params);
        return ar;
    }

}
