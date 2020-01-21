package com.school.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class punRecord1 {
    private int id;
    private int userId;
    private String userName;
    private String level;
    private String descr;
    private int reviewId;
    private String reviewName;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date time;
    private int status;
    private String reason;
    private int dId;
    private String dName;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date dTime;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getdId() {
        return dId;
    }

    public void setdId(int dId) {
        this.dId = dId;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    public Date getdTime() {
        return dTime;
    }

    public void setdTime(Date dTime) {
        this.dTime = dTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
