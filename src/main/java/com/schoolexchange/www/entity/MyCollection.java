package com.schoolexchange.www.entity;

import java.io.Serializable;

/**
 * Created by shadow on 2016/5/2.
 * 我的收藏
 */
public class MyCollection implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer goods_id;
    private String goods_name;

    public MyCollection(Integer goods_id, String goods_name) {
        this.goods_id = goods_id;
        this.goods_name = goods_name;
    }

    public Integer getGood_id() {
        return goods_id;
    }

    public void setGood_id(Integer good_id) {
        this.goods_id = good_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }
}
