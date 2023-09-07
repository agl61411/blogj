package com.yi.blogj.model;

import java.io.Serializable;
import java.util.Date;

public class Record implements Serializable {
    private static final long serialVersionUID = -6779461342733324629L;
    private int id;
    private Date creationtime;
    private Date modifiedtime;
    private Boolean deprecated;
    private String title;
    private String content;
    private String tags;
    private Date recordTime;
    private int userId;

    public Record() {
    }

    public Record(String title, String content, String tags, Date recordTime, int userId) {
        this.title = title;
        this.content = content;
        this.tags = tags;
        this.recordTime = recordTime;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreationtime() {
        return creationtime;
    }

    public void setCreationtime(Date creationtime) {
        this.creationtime = creationtime;
    }

    public Date getModifiedtime() {
        return modifiedtime;
    }

    public void setModifiedtime(Date modifiedtime) {
        this.modifiedtime = modifiedtime;
    }

    public Boolean getDeprecated() {
        return deprecated;
    }

    public void setDeprecated(Boolean deprecated) {
        this.deprecated = deprecated;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
