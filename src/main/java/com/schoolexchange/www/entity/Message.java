package com.schoolexchange.www.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by shadow on 2015/11/16.
 * Message:留言表
 */
public class Message implements Serializable{

    private static final long serialVersionUID = 1L;

    public Message(){}

    //id自增
    private Integer id;

    //发送消息用户
    private Integer send_user_id;

    //接受消息用户
    private Integer accept_user_id;

    //发送消息内容
    private String message_content;

    //消息状态=>0表示未读，1表示已读
    private Integer message_state;

    //发布日期
    private Date publishDate;

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSend_user_id() {
        return send_user_id;
    }

    public void setSend_user_id(Integer send_user_id) {
        this.send_user_id = send_user_id;
    }

    public Integer getAccept_user_id() {
        return accept_user_id;
    }

    public void setAccept_user_id(Integer accept_user_id) {
        this.accept_user_id = accept_user_id;
    }

    public String getMessage_content() {
        return message_content;
    }

    public void setMessage_content(String message_content) {
        this.message_content = message_content;
    }

    public Integer getMessage_state() {
        return message_state;
    }

    public void setMessage_state(Integer message_state) {
        this.message_state = message_state;
    }
}
