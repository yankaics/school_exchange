package com.schoolexchange.www.entity;

import java.util.Date;

/**
 * Created by shadow on 2015/11/16.
 * GoodsComments:商品评论
 */
public class GoodsComments {

    public GoodsComments() {
    }

    //id自增
    private Integer id;

    //评论的商品id
    private Integer sell_goods_id;

    //评论用户id
    private Integer user_id;

    //评论时间
    private Date comment_time;

    //评论内容
    private String comment_content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSell_goods_id() {
        return sell_goods_id;
    }

    public void setSell_goods_id(Integer sell_goods_id) {
        this.sell_goods_id = sell_goods_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Date getComment_time() {
        return comment_time;
    }

    public void setComment_time(Date comment_time) {
        this.comment_time = comment_time;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

}
