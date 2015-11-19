package com.schoolexchange.www.service;

/**
 * Created by shadow on 2015/11/18.
 * UserService
 */
public interface UserService {

    public boolean checkEmail(String email);

    public boolean checkUserName(String user_name);

    public void registerUser(String user_email , String user_name
            , String user_password , String belong_university);
    //验证登录
    public boolean checkLogin(String user_name , String user_password);

    //根据用户名或密码得到所属大学
    public String getUniversityByUserNameOrEmail(String userNameOrEmail);
}
