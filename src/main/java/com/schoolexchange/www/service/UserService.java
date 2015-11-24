package com.schoolexchange.www.service;

import javax.servlet.http.HttpSession;

/**
 * Created by shadow on 2015/11/18.
 * UserService
 */
public interface UserService {

    //检测email
    boolean checkEmail(String email);

    //检测用户名
    boolean checkUserName(String user_name);

    //注册用户
    void registerUser(String user_email, String user_name
            , String user_password, String belong_university);

    //验证登录
    boolean checkLogin(String user_name, String user_password);

    //根据用户名或密码得到所属大学
    String getUniversityByUserNameOrEmail(String userNameOrEmail);

    //重置密码
    String resetPassword(String email);

    //发送邮件
    void sendMail(String email, String reset_password);

    //通过session获取所属大学
    String getUserUniversity(HttpSession session);



}
