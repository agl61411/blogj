package com.yi.blogj.model;

import com.yi.blogj.enums.ArticleType;

public class Article extends BaseInfo {

  private static final long serialVersionUID = 1L;

  private String title;
  private String content;
  private String tags;
  private ArticleType type;
  
  public Article(String title, String content, String tags, ArticleType type, String userId) {
    this.title = title;
    this.content = content;
    this.tags = tags;
    this.type = type;
    this.userId = userId;
  }

  public Article(String title, String content, ArticleType type, String userId) {
    this.title = title;
    this.content = content;
    this.type = type;
    this.userId = userId;
  }

  public Article() {
  }
  
  public ArticleType getType() {
    return type;
  }
  public void setType(ArticleType type) {
    this.type = type;
  }
  private String userId;

  public String getUserId() {
    return userId;
  }
  public void setUserId(String userId) {
    this.userId = userId;
  }
  public String getTags() {
    return tags;
  }
  public void setTags(String tags) {
    this.tags = tags;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
}
