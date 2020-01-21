package com.school.util;

public enum ErrorEnum {
    /*
     * 错误信息
     * */
    E_400("400", "请求处理异常，请稍后再试"),
    E_450("450", "数据修改失败"),

    E_452("452", "用户未登录"),
    E_453("453", "旧密码输入有误，操作失败"),


    E_500("500", "请求方式有误,请检查 GET/POST"),
    E_501("501", "请求路径不存在"),
    E_502("502", "权限不足"),

    E_600("600", "下载上传的文件失败，文件为空或过大"),
    E_603("603","解析文件失败，请检查数据是否为空"),
    E_604("604","缺少字段或字段名错误"),
    E_605("605","字段有空值"),
    E_606("606","文件中有内容为空"),
    E_607("607","文件为空"),
    E_608("608","文件中有错误的课程id"),
    E_609("609","错误的课程id"),
    E_610("610","无此学生"),

    E_10010("10010", "账户无权"),

    E_20011("20011", "登陆已过期,请重新登陆"),

    E_90003("90003", "缺少必填参数");



    private String errorCode;

    private String errorMsg;

    ErrorEnum() {
    }

    ErrorEnum(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
