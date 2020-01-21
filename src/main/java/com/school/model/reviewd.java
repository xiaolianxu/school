package com.school.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class reviewd {
    private int id;
    private int userId;
    private String userName;
    private String descr;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date appliTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date reviewTime;
    private int result;
    private int reviewId;
    private String reviewName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Date reviewTime) {
        this.reviewTime = reviewTime;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public String getReviewName() {
        return reviewName;
    }

    public void setReviewName(String reviewName) {
        this.reviewName = reviewName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
