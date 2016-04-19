package com.schoolexchange.www.entity;

import java.io.Serializable;

/**
 * Created by shadow on 16-2-28.
 */
public class SellGoodsToUser implements Serializable{

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String goods_name;

    private double goods_price;

    private String goods_images;

    public SellGoodsToUser(Integer id, String goods_name , double goods_price , String goods_images){
        this.id = id;
        this.goods_name = goods_name;
        this.goods_price = goods_price;
        this.goods_images = goods_images;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public double getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(double goods_price) {
        this.goods_price = goods_price;
    }

    public String getGoods_images() {
        return goods_images;
    }

    public void setGoods_images(String goods_images) {
        this.goods_images = goods_images;
    }
}
