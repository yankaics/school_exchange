package com.schoolexchange.www.service.impl;

import com.schoolexchange.www.dao.UserDao;
import com.schoolexchange.www.entity.User;
import com.schoolexchange.www.service.UserService;
import com.schoolexchange.www.tools.BCrypt;
import com.schoolexchange.www.tools.MailSenderInfo;
import com.schoolexchange.www.tools.SimpleMailSender;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by shadow
 * on 2015/11/18.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 检测email是否存在
     *
     * @param email 用户email
     * @return 存在返回true, 否则返回false
     * @see com.schoolexchange.www.action.CheckRegisterController
     */
    public boolean checkEmail(String email) {
        List<User> users = new ArrayList<User>();
        users = userDao.getAllUser();
        if (users.size() > 0) {
            for (User user : users) {
                if (user.getUser_email().equals(email))
                    return true;
            }
        }
        return false;
    }

    /**
     * 检测用户名是否存在
     *
     * @param user_name 用户名
     * @return 存在返回true, 否则返回false
     * @see com.schoolexchange.www.action.CheckRegisterController
     */
    public boolean checkUserName(String user_name) {
        List<User> users = new ArrayList<User>();
        users = userDao.getAllUser();
        if (users.size() > 0) {
            for (User user : users) {
                if (user.getUser_name().equals(user_name))
                    return true;
            }
        }
        return false;
    }

    /**
     * 注册用户
     *
     * @param user_email        用户email
     * @param user_name         用户名
     * @param user_password     用户密码
     * @param belong_university 用户所属大学
     * @see com.schoolexchange.www.action.CheckRegisterController
     */
    public void registerUser(String user_email, String user_name, String user_password, String belong_university) throws ParseException {
        User user = new User();
        user.setUser_goods_counts(0);
        user.setUser_state(1);
        user.setMessage_count(0);
        try {
            user.setCreate_time(getCurrentTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setUser_email(user_email);
        user.setUser_name(user_name);
        user.setUser_password(encrypt_password(user_password));
        user.setUser_university(belong_university);
        user.setUser_sex(1);
        user.setUser_authentication(0);
        user.setUser_faces("default.jpg");
        user.setUser_birth(new SimpleDateFormat("yyyy-MM-dd").parse("1950-01-01"));
        userDao.saveUser(user);
    }

    /**
     * 登录验证
     *
     * @param user_name     用户名或email或手机号
     * @param user_password 用户密码
     * @return 用户名和密码正确返回true，否则返回false
     * @see com.schoolexchange.www.action.LoginController
     */
    public boolean checkLogin(String user_name, String user_password) {
        List<User> users = new ArrayList<User>();
        users = userDao.getAllUser();
        if (users.size() > 0) {
            for (User user : users) {
                if (null != user.getUser_tel()) {
                    if ((user.getUser_name().equals(user_name) || user.getUser_email().equals(user_name)
                            || user.getUser_tel().equals(user_name))
                            && judge_password(user.getUser_password(), user_password) && 0 == user.getUser_state())
                        return true;
                } else {
                    if ((user.getUser_name().equals(user_name) || user.getUser_email().equals(user_name))
                            && judge_password(user.getUser_password(), user_password) && 0 == user.getUser_state())
                        return true;
                }
            }
        }
        return false;
    }

    /**
     * 根据用户名或密码得到所属大学
     *
     * @param userNameOrEmail 用户名或email或手机号
     * @return 返回用户所属的大学和用户名，找不到则返回null
     * @see com.schoolexchange.www.action.UserController
     */
    public String getUniversityByUserNameOrEmail(String userNameOrEmail) {
        List<User> users = new ArrayList<User>();
        users = userDao.getAllUser();
        if (users.size() > 0) {
            for (User user : users) {
                if (null != user.getUser_tel()) {
                    if ((user.getUser_name().equals(userNameOrEmail) || user.getUser_email().equals(userNameOrEmail)
                            || user.getUser_tel().equals(userNameOrEmail)))
                        return user.getUser_university() + "$" + user.getUser_name();
                } else {
                    if ((user.getUser_name().equals(userNameOrEmail) || user.getUser_email().equals(userNameOrEmail)))
                        return user.getUser_university() + "$" + user.getUser_name();
                }
            }
        }
        return null;
    }

    /**
     * 重置密码
     *
     * @param email 用户email
     * @return 返回重置后的密码(6位数字)
     * @see com.schoolexchange.www.action.UserController
     */
    public String resetPassword(String email, String new_pwd) {
       /* String new_pwd = getRandomPassword();*/
        List<User> users = new ArrayList<User>();
        users = userDao.getAllUser();
        if (users.size() > 0) {
            for (User user : users) {
                if (user.getUser_email().equals(email)) {
                    user.setUser_password(encrypt_password(new_pwd));
                    userDao.editUser(user);
                }
            }
        }

        return new_pwd;
    }

    /**
     * 发送邮件
     *
     * @param email       接受信息的email
     * @param mailContent 邮件内容
     */
    public void sendMail(String email, String mailContent) {
        // 设置邮件服务器信息
        MailSenderInfo mailInfo = new MailSenderInfo();
        mailInfo.setMailServerHost("smtp.163.com");
        mailInfo.setMailServerPort("25");
        mailInfo.setValidate(true);

        // 邮箱用户名
        mailInfo.setUserName("18353507779@163.com");
        // 邮箱密码
        mailInfo.setPassword("shadow0418");
        // 发件人邮箱
        mailInfo.setFromAddress("18353507779@163.com");
        // 收件人邮箱
        mailInfo.setToAddress(email);
        // 邮件标题
        mailInfo.setSubject("schoolexchange");

        // 邮件内容
        StringBuffer buffer = new StringBuffer();
        buffer.append(mailContent);
        mailInfo.setContent(buffer.toString());

        // 发送邮件
       /* SimpleMailSender sms = new SimpleMailSender();*/
        // 发送文体格式
       /* sms.sendTextMail(mailInfo);*/

        // 发送html格式
        SimpleMailSender.sendHtmlMail(mailInfo);
    }

    /**
     * 通过session获取所属大学
     *
     * @param session session
     * @return 返回用户所属的大学，找不到返回 null
     */
    public String getUserUniversity(HttpSession session) {
        String university = null != session.getAttribute("sx_university") ? (String) session.getAttribute("sx_university") : null;
        return university;
    }

    /**
     * 修改密码
     *
     * @param userName 用户名
     * @param newPwd   新密码
     * @see com.schoolexchange.www.action.AccountController
     */
    public void changePwd(String userName, String newPwd) {
        List<User> users = new ArrayList<User>();
        users = userDao.getAllUser();
        if (users.size() > 0) {
            for (User user : users) {
                if (user.getUser_name().equals(userName)) {
                    user.setUser_password(encrypt_password(newPwd));
                    userDao.editUser(user);
                }
            }
        }
    }

    /**
     * 获取当前用户
     *
     * @param session 当前session
     * @return 通过session获取当前用户
     */
    public User getCurrentUser(HttpSession session) {
        String currenrUserName = (String) session.getAttribute("sx_user_name");
        List<User> users = userDao.getAllUser();
        if (0 != users.size() && currenrUserName != null) {
            for (User user : users) {
                if (user.getUser_name().equals(currenrUserName)) {
                    return user;
                }
            }
        }
        return null;
    }

    /**
     * 解决get乱码
     *
     * @param chineseCharacter 传入的字符串
     * @return 返回utf8的字符串
     * @see com.schoolexchange.www.action.AccountController
     */
    public String solveGetMessyCode(String chineseCharacter) {
        try {
            chineseCharacter = new String(chineseCharacter.getBytes("iso-8859-1"), "utf8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            System.out.println("乱码处理失败");
        }
        return chineseCharacter;
    }

    /**
     * 更新用户基本信息
     *
     * @param user 用户
     * @see com.schoolexchange.www.action.AccountController
     */
    public void updateUserInfo(User user) {
        userDao.updateUser(user);
    }

    /**
     * 认证状态
     *
     * @param user 用户
     * @return 认证成功返回true，否则返回false
     */
    public boolean authenticationStatus(User user) {
        boolean flag = false;
        List<User> users = userDao.getAllUser();
        if (0 != users.size()) {
            for (User u : users) {
                if (u.getUser_name().equals(user.getUser_name()) && 1 == u.getUser_authentication())
                    flag = true;
            }
        }
        return flag;
    }

    /**
     * 检测手机号是否已被认证
     *
     * @param auth_tel 要认证的手机号
     * @return 已被认证返回true, 否则返回false
     * @see com.schoolexchange.www.action.AccountController
     */
    public boolean authTel(String auth_tel) {
        boolean flag = false;
        List<User> users = userDao.getAllUser();
        if (null != auth_tel) {
            if (0 != users.size()) {
                for (User u : users) {
                    if (null != u.getUser_tel()) {
                        if (u.getUser_tel().equals(auth_tel)) {
                            flag = true;
                            break;
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * 最新日期与原始日期差值是否超过1分钟
     *
     * @param oldDate 原始日期
     * @param newDate 最新日期
     * @return 返回true表示超过，否则不超过
     * @see com.schoolexchange.www.action.AccountController
     */
    public boolean dateDifference(Date oldDate, Date newDate) {
        boolean flag = false;
        long diff = newDate.getTime() - oldDate.getTime();
        if (diff > 60000) {
            flag = true;
        }
        return flag;
    }

    /**
     * 获取系统当前时间
     *
     * @return 返回系统当前时间
     */
    public Date getCurrentTime() throws ParseException {
        Date create_time = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        String create_time_str = sf.format(create_time);
        return sf.parse(create_time_str);
    }


    /**
     * 随机生成6位数字的密码
     *
     * @return 返回6位数字密码
     */
    public String getRandomPassword() {
        StringBuffer random_pwd = new StringBuffer();
        for (int i = 0; i < 6; i++) {
            random_pwd.append((int) (Math.random() * 9 + 1));
        }

        return random_pwd.toString();

    }

    /**
     * 发送手机验证码
     *
     * @param tel             接受验证码的手机号
     * @param verificatinCode 验证码
     * @return
     */
    public String sendSms(String tel, String verificatinCode) throws IOException {
        HttpClient client = new HttpClient();
        String content = "您本次操作的验证码为:" + verificatinCode + ",请勿透露给他人(验证码有效时间为20分钟)";
        PostMethod post = new PostMethod("http://utf8.sms.webchinese.cn");
        post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf8");//在头文件中设置转码
        NameValuePair[] data = {new NameValuePair("Uid", "zjd0418"), new NameValuePair("Key", "53ae2199b371b1390c5c"), new NameValuePair("smsMob", tel), new NameValuePair("smsText", content)};
        post.setRequestBody(data);

        client.executeMethod(post);
        Header[] headers = post.getResponseHeaders();
        int statusCode = post.getStatusCode();
        System.out.println("statusCode:" + statusCode);
        for (Header h : headers) {
            System.out.println(h.toString());
        }
        String result = new String(post.getResponseBodyAsString().getBytes("utf8"));
        System.out.println(result); //打印返回消息状态

        post.releaseConnection();

        return result;
    }

    /**
     * 对密码加密
     *
     * @param password 要加密的字符串
     * @return 返回hash加密后的字符串
     */
    public String encrypt_password(String password) {
        String encrypt_password = BCrypt.hashpw(password, BCrypt.gensalt());

        return encrypt_password;
    }

    /**
     * 解密
     *
     * @param db_pwd    数据库的密码
     * @param input_pwd 当前输入的密码
     * @return 返回密码的比对状态，true表示匹配成功,否则返回false失败
     */
    public boolean judge_password(String db_pwd, String input_pwd) {

        return BCrypt.checkpw(input_pwd, db_pwd);
    }

    /**
     * 认证用户
     *
     * @param user    用户信息
     * @param authTel 认证手机号
     * @see com.schoolexchange.www.action.AccountController
     */
    public void authUser(User user, String authTel) {
        user.setUser_tel(authTel);
        user.setUser_authentication(1);
        userDao.updateUserTel(user);
    }

    /**
     * 激活邮箱
     *
     * @param userEmail 邮箱
     */
    public void activateUser(String userEmail) {
        List<User> users = userDao.getAllUser();
        if (0 != users.size()) {
            for (User u : users) {
                if (u.getUser_email().equals(userEmail)) {
                    u.setUser_state(0);
                    userDao.updateUserStatus(u);
                }
            }
        }
    }

    /**
     * 通过用户id获取用户的所有信息
     *
     * @param user_id 用户id
     * @return 成功返回用户的的所有信息，否则返回NULL
     */
    public User getUserByUserId(Integer user_id) {
        List<User> users = userDao.getAllUser();
        if (null != users){
            for (User u : users) {
                if (u.getId().equals(user_id))
                    return u;
            }
        }
        return null;
    }
}
