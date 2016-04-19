package com.schoolexchange.www.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by shadow on 2015/11/16.
 * ReplayComments:回复表
 */
public class ReplyComments implements Serializable{

    private static final long serialVersionUID = 1L;

    public ReplyComments(){}

    //id自增
    private Integer id;

    //商品id
    private Integer goods_comments_id;

    //用户id
    private Integer user_id;

    //回复时间
    private Date reply_time;

    //回复内容
    private String reply_comments_content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoods_comments_id() {
        return goods_comments_id;
    }

    public void setGoods_comments_id(Integer goods_comments_id) {
        this.goods_comments_id = goods_comments_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Date getReply_time() {
        return reply_time;
    }

    public void setReply_time(Date reply_time) {
        this.reply_time = reply_time;
    }

    public String getReply_comments_content() {
        return reply_comments_content;
    }

    public void setReply_comments_content(String reply_comments_content) {
        this.reply_comments_content = reply_comments_content;
    }
}
