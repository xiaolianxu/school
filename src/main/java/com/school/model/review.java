package com.school.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class review {
    private int id;
    private int userId;
    private String userName;
    private String descr;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date appliTime;
    private int result;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public Date getAppliTime() {
        return appliTime;
    }

    public void setAppliTime(Date appliTime) {
        this.appliTime = appliTime;
    }
}
