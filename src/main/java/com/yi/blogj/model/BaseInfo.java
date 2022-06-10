package com.yi.blogj.model;

import java.io.Serializable;

public class BaseInfo implements Serializable {

    private static final long serialVersionUID = 56230136917205108L;
    
    private String id;
    private String creationtime;
    private String modifiedTime;
    private Boolean deprecated;
    
    public BaseInfo() {
    }
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getCreationtime() {
        return creationtime;
    }
    public void setCreationtime(String creationtime) {
        this.creationtime = creationtime;
    }
    public String getModifiedTime() {
        return modifiedTime;
    }
    public void setModifiedTime(String modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
    public Boolean getDeprecated() {
        return deprecated;
    }
    public void setDeprecated(Boolean deprecated) {
        this.deprecated = deprecated;
    }
  
}
