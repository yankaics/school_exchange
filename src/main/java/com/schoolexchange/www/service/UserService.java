package com.schoolexchange.www.service;

import com.schoolexchange.www.entity.User;

import javax.servlet.http.HttpSession;
import java.text.ParseException;

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
            , String user_password, String belong_university) throws ParseException;

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

    //修改密码
    void changePwd(String userName , String newPwd);

    //获取当前用户
    User getCurrentUser(HttpSession session);

    //解决get乱码
    String solveGetMessyCode(String chineseCharacter);

    //更新用户基本信息
    void updateUserInfo(User user);

}
