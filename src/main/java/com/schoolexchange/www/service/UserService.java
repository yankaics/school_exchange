package com.schoolexchange.www.service;

import com.schoolexchange.www.entity.User;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by shadow
 * on 2015/11/18
 */
public interface UserService {

    /**
     * 检测email是否存在
     *
     * @param email 用户email
     * @return 存在返回true, 否则返回false
     * @see com.schoolexchange.www.action.CheckRegisterController
     */
    boolean checkEmail(String email);

    /**
     * 检测用户名是否存在
     *
     * @param user_name 用户名
     * @return 存在返回true, 否则返回false
     * @see com.schoolexchange.www.action.CheckRegisterController
     */
    boolean checkUserName(String user_name);

    /**
     * 注册用户
     *
     * @param user_email        用户email
     * @param user_name         用户名
     * @param user_password     用户密码
     * @param belong_university 用户所属大学
     * @see com.schoolexchange.www.action.CheckRegisterController
     */
    void registerUser(String user_email, String user_name
            , String user_password, String belong_university) throws ParseException;

    /**
     * 登录验证
     *
     * @param user_name     用户名或email或手机号
     * @param user_password 用户密码
     * @return 用户名和密码正确返回true，否则返回false
     * @see com.schoolexchange.www.action.LoginController
     */
    boolean checkLogin(String user_name, String user_password);

    /**
     * 根据用户名或密码得到所属大学
     *
     * @param userNameOrEmail 用户名或email或手机号
     * @return 返回用户所属的大学，找不到则返回null
     * @see com.schoolexchange.www.action.UserController
     */
    String getUniversityByUserNameOrEmail(String userNameOrEmail);

    /**
     * 重置密码
     *
     * @param email   用户email
     * @param new_pwd 新密码
     * @return 返回重置后的密码
     * @see com.schoolexchange.www.action.UserController
     */
    String resetPassword(String email, String new_pwd);

    /**
     * 发送邮件
     *
     * @param email       接受信息的email
     * @param mailContent 邮件内容
     */
    void sendMail(String email, String mailContent);

    /**
     * 通过session获取所属大学
     *
     * @param session session
     * @return 返回用户所属的大学，找不到返回 null
     */
    String getUserUniversity(HttpSession session);

    /**
     * 修改密码
     *
     * @param userName 用户名
     * @param newPwd   新密码
     * @see com.schoolexchange.www.action.AccountController
     */
    void changePwd(String userName, String newPwd);

    /**
     * 获取当前用户
     *
     * @param session 当前session
     * @return 通过session获取当前用户
     */
    User getCurrentUser(HttpSession session);

    /**
     * 解决get乱码
     *
     * @param chineseCharacter 传入的字符串
     * @return 返回utf8的字符串
     * @see com.schoolexchange.www.action.AccountController
     */
    String solveGetMessyCode(String chineseCharacter);

    /**
     * 更新用户基本信息
     *
     * @param user 用户
     * @see com.schoolexchange.www.action.AccountController
     */
    void updateUserInfo(User user);

    /**
     * 认证状态
     *
     * @param user 用户
     * @return 认证成功返回true，否则返回false
     */
    boolean authenticationStatus(User user);

    /**
     * 检测手机号是否已被认证
     *
     * @param auth_tel 要认证的手机号
     * @return 已被认证返回true, 否则返回false
     * @see com.schoolexchange.www.action.AccountController
     */
    boolean authTel(String auth_tel);

    /**
     * 最新日期与原始日期差值是否超过1分钟
     *
     * @param oldDate 原始日期
     * @param newDate 最新日期
     * @return 返回true表示超过，否则不超过
     * @see com.schoolexchange.www.action.AccountController
     */
    boolean dateDifference(Date oldDate, Date newDate);

    /**
     * 随机生成6位数字的密码
     *
     * @return 返回6位数字密码
     */
    String getRandomPassword();

    /**
     * 发送手机验证码
     *
     * @param tel             接受验证码的手机号
     * @param verificatinCode 验证码
     * @return
     */
    String sendSms(String tel, String verificatinCode) throws IOException;

    /**
     * 对字符串加密
     *
     * @param password 要加密的字符串
     * @return 返回hash加密后的字符串
     */
    String encrypt_password(String password);


    /**
     * 解密
     *
     * @param db_pwd    数据库的密码
     * @param input_pwd 当前输入的密码
     * @return 返回密码的比对状态，true表示匹配成功,否则返回false失败
     */
    boolean judge_password(String db_pwd, String input_pwd);

    /**
     * 认证用户
     *
     * @param user    用户信息
     * @param authTel 认证手机号
     * @see com.schoolexchange.www.action.AccountController
     */
    void authUser(User user, String authTel);

    /**
     * 激活邮箱
     *
     * @param userEmail 邮箱
     */
    void activateUser(String userEmail);

    /**
     * 通过用户id获取用户的所有信息
     *
     * @param user_id 用户id
     * @return 成功返回用户的的所有信息，否则返回NULL
     */
    User getUserByUserId(Integer user_id);

    /**
     * 通过用户名获取用户id
     *
     * @param userName 用户名
     * @return 返回用户id
     */
    Integer getUserIdByUserName(String userName);

    /**
     * 通过用户id获取用户名
     *
     * @param userId 用户id
     * @return 用户名或null
     */
    String getUserNameByUserId(Integer userId);
}
