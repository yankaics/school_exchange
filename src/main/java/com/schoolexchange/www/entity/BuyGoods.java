package com.schoolexchange.www.entity;

import java.util.Date;

/**
 * Created by shadow on 2015/11/16.
 * BuyGoods:求购表
 */
public class BuyGoods {

    public BuyGoods(){}

    //商品id自增
    private Integer id;

    //求购商品对应的求购用户id
    private Integer user_id;

    //求购商品名
    private String goods_name;

    //过期日期
    private Date goods_deadline;

    //求购商品数量
    private Integer goods_count;

    //联系方式
    private String  goods_contact;

    //商品描述
    private String goods_desc;

    //商品状态=>0表示正常，1冻结商品
    private Integer goods_state;

    //求购日期
    private Date create_time;

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

    public Integer getGoods_count() {
        return goods_count;
    }

    public void setGoods_count(Integer goods_count) {
        this.goods_count = goods_count;
    }

    public String getGoods_contact() {
        return goods_contact;
    }

    public void setGoods_contact(String goods_contact) {
        this.goods_contact = goods_contact;
    }

    public String getGoods_desc() {
        return goods_desc;
    }

    public void setGoods_desc(String goods_desc) {
        this.goods_desc = goods_desc;
    }

    public Integer getGoods_state() {
        return goods_state;
    }

    public void setGoods_state(Integer goods_state) {
        this.goods_state = goods_state;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}
