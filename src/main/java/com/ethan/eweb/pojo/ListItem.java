package com.ethan.eweb.pojo;

import java.util.Date;

public class ListItem {

    private String userName;
    private String cover;
    private String id;
    private String title;
    private int viewCount;
    private int commentCount;
    private Date publishTime;

    public ListItem(String title, String id, String cover, int viewCount, int commentCount) {
        this.id = id;
        this.title = title;
        this.viewCount = viewCount;
        this.commentCount = commentCount;
        this.publishTime = new Date();
        this.userName = "Ethan";
        this.cover = cover;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getViewCount() {
        return this.viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public int getCommentCount() {
        return this.commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public Date getPublishTime() {
        return this.publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCover() {
        return this.cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}
