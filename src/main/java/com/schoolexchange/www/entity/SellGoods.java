package com.schoolexchange.www.entity;

import java.util.Date;

/**
 * Created by shadow on 2015/11/16.
 * SellGoods:商品表
 */
public class SellGoods {
    //id自增
    private Integer id;

    //商品对应的用户id
    private Integer user_id;

    //商品名
    private String goods_name;

    //商品过期时间
    private Date goods_deadline;

    //商品名描述
    private String goods_desc;

    //商品图片(轮播)
    private String goods_images;

    //商品地址
    private String goods_address;

    //商品价格
    private double goods_price;

    //商品类型
    private String goods_type;

    //单个商品数量
    private int goods_count;

    //商品具体信息简介
    private String goods_info;

    //商品状态 0表示正常发布，1表示禁售商品
    private int goods_state;

    //商品创建时间
    private Date create_time;

    //联系方式
    private String contact;

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public Date getGoods_deadline() {
        return goods_deadline;
    }

    public void setGoods_deadline(Date goods_deadline) {
        this.goods_deadline = goods_deadline;
    }

    public String getGoods_desc() {
        return goods_desc;
    }

    public void setGoods_desc(String goods_desc) {
        this.goods_desc = goods_desc;
    }

    public String getGoods_images() {
        return goods_images;
    }

    public void setGoods_images(String goods_images) {
        this.goods_images = goods_images;
    }

    public String getGoods_address() {
        return goods_address;
    }

    public void setGoods_address(String goods_address) {
        this.goods_address = goods_address;
    }

    public double getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(double goods_price) {
        this.goods_price = goods_price;
    }

    public String getGoods_type() {
        return goods_type;
    }

    public void setGoods_type(String goods_type) {
        this.goods_type = goods_type;
    }

    public int getGoods_count() {
        return goods_count;
    }

    public void setGoods_count(int goods_count) {
        this.goods_count = goods_count;
    }

    public String getGoods_info() {
        return goods_info;
    }

    public void setGoods_info(String goods_info) {
        this.goods_info = goods_info;
    }

    public int getGoods_state() {
        return goods_state;
    }

    public void setGoods_state(int goods_state) {
        this.goods_state = goods_state;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}
