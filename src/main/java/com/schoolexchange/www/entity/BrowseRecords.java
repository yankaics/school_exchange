package com.schoolexchange.www.entity;

import java.io.Serializable;

/**
 * Created by shadow on 2016/5/4.
 * 浏览记录实体类
 */
public class BrowseRecords implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;         //id自增
    private Integer userId;     //用户id
    private Integer goodsId;    //商品id
    public BrowseRecords(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }
}
