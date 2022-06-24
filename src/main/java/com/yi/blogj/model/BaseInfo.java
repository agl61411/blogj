package com.yi.blogj.model;

import java.io.Serializable;
import java.util.Date;

public class BaseInfo implements Serializable {

    private static final long serialVersionUID = 56230136917205108L;
    
    private Long id;
    private Date creationtime;
    private Date modifiedtime;
    private Boolean deprecated;
    
    public BaseInfo() {
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
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
  
}
