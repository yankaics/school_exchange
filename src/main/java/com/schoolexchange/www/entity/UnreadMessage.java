package com.schoolexchange.www.entity;

/**
 * Created by Administrator on 2016/4/12.
 * 存储未读信息
 */
public class UnreadMessage {
    private String name;
    private String content;

    public UnreadMessage(String name, String content) {
        this.name = name;
        this.content = content;
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
}
