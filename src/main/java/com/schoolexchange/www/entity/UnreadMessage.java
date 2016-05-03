package com.schoolexchange.www.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2016/4/12.
 * 存储未读信息
 */
public class UnreadMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String content;
    private Date publishDate;

    public UnreadMessage(String name, String content, Date publishDate) {
        this.name = name;
        this.content = content;
        this.publishDate = publishDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }
}
