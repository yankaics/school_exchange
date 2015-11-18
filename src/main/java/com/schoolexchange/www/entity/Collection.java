package com.schoolexchange.www.entity;

/**
 * Created by shadow on 2015/11/16.
 * Collection:收藏表
 */
public class Collection {

    public Collection(){}

    private Integer id;

    private Integer goods_id;

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
