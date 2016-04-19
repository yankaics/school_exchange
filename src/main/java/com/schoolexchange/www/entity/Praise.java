package com.schoolexchange.www.entity;

import java.io.Serializable;

/**
 * Created by shadow on 2015/11/16.
 *  Praise:点赞表
 */
public class Praise implements Serializable{

    private static final long serialVersionUID = 1L;

    public Praise(){}

    //id自增
    private Integer id;

    //点赞商品id
    private Integer goods_id;

    //点赞用户id
    private Integer user_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(Integer goods_id) {
        this.goods_id = goods_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
}
