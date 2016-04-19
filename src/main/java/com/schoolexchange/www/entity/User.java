package com.schoolexchange.www.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by shadow on 2015/11/16.
 * User:用户表
 */
public class User implements Serializable{

    private static final long serialVersionUID = 1L;

    public User(){}

    //用户id自增
    private Integer id;

    //用户名
    private String user_name;

    //用户邮箱
    private String user_email;

    //用户电话
    private String user_tel;

    //登录密码
    private String user_password;

    //所属大学
    private String user_university;

    //用户头像
    private String user_faces;

    //用户发布的商品数量(包含下架的和删除的商品)
    private Integer user_goods_counts;

    //用户状态===> 0表示用户正常，1表示用户账号被冻结
    private int user_state;

    //未浏览的消息总数(留言)
    private int message_count;

    private Date create_time;

    //性别
    private int user_sex;

    //生日
    private Date user_birth;

    //座右铭
    private String user_motto;

    //专业
    private String user_professional;

    //认证
    private int user_authentication;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_tel() {
        return user_tel;
    }

    public void setUser_tel(String user_tel) {
        this.user_tel = user_tel;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_university() {
        return user_university;
    }

    public void setUser_university(String user_university) {
        this.user_university = user_university;
    }

    public String getUser_faces() {
        return user_faces;
    }

    public void setUser_faces(String user_faces) {
        this.user_faces = user_faces;
    }

    public Integer getUser_goods_counts() {
        return user_goods_counts;
    }

    public void setUser_goods_counts(Integer user_goods_counts) {
        this.user_goods_counts = user_goods_counts;
    }

    public int getUser_state() {
        return user_state;
    }

    public void setUser_state(int user_state) {
        this.user_state = user_state;
    }

    public int getMessage_count() {
        return message_count;
    }

    public void setMessage_count(int message_count) {
        this.message_count = message_count;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public int getUser_sex() {
        return user_sex;
    }

    public void setUser_sex(int user_sex) {
        this.user_sex = user_sex;
    }

    public Date getUser_birth() {
        return user_birth;
    }

    public void setUser_birth(Date user_birth) {
        this.user_birth = user_birth;
    }

    public String getUser_motto() {
        return user_motto;
    }

    public void setUser_motto(String user_motto) {
        this.user_motto = user_motto;
    }

    public String getUser_professional() {
        return user_professional;
    }

    public void setUser_professional(String user_professional) {
        this.user_professional = user_professional;
    }

    public int getUser_authentication() {
        return user_authentication;
    }

    public void setUser_authentication(int user_authentication) {
        this.user_authentication = user_authentication;
    }
}
