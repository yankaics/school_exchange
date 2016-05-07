package com.schoolexchange.www.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by shadow on 2016/5/4.
 * 浏览记录实体类
 */
public class BrowseRecords implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;         //id自增
    private Integer userId;     //用户id
    private Integer goodsId;    //商品id
    private Date bDate;         //浏览日期

    private String goodsName;   //商品名

    public BrowseRecords() {
    }

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

    public Date getbDate() {
        return bDate;
    }

    public void setbDate(Date bDate) {
        this.bDate = bDate;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
}
