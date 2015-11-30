package com.schoolexchange.www.service.impl;

import com.schoolexchange.www.dao.UserDao;
import com.schoolexchange.www.entity.User;
import com.schoolexchange.www.service.UserService;
import com.schoolexchange.www.tools.BCrypt;
import com.schoolexchange.www.tools.MailSenderInfo;
import com.schoolexchange.www.tools.SimpleMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by shadow on 2015/11/18.
 * UserServiceImpl
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public boolean checkEmail(String email) {
        List<User> users = new ArrayList<User>();
        users = userDao.getAllUser();
        if (users.size() > 0){
            for (User user:users){
                if (user.getUser_email().equals(email))
                    return true;
            }
        }
        return false;
    }

    public boolean checkUserName(String user_name) {
        List<User> users = new ArrayList<User>();
        users = userDao.getAllUser();
        if (users.size() > 0){
            for (User user:users){
                if (user.getUser_name().equals(user_name))
                    return true;
            }
        }
        return false;
    }

    public void registerUser(String user_email, String user_name, String user_password, String belong_university) throws ParseException {
        User user = new User();
        user.setUser_goods_counts(0);
        user.setUser_state(0);
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

    public boolean checkLogin(String user_name, String user_password) {
        List<User> users = new ArrayList<User>();
        users = userDao.getAllUser();
        if (users.size() > 0){
            for (User user:users){
                if ((user.getUser_name().equals(user_name) || user.getUser_email().equals(user_name))
                        && judge_password(user.getUser_password() , user_password))
                    return true;
            }
        }
        return false;
    }


    public String getUniversityByUserNameOrEmail(String userNameOrEmail) {
        List<User> users = new ArrayList<User>();
        users = userDao.getAllUser();
        if (users.size() > 0){
            for (User user:users){
                if ((user.getUser_name().equals(userNameOrEmail) || user.getUser_email().equals(userNameOrEmail)))
                    return user.getUser_university() + "$" + user.getUser_name();
            }
        }
        return null;
    }

    public String resetPassword(String email) {
        String new_pwd = getRandomPassword();
        List<User> users = new ArrayList<User>();
        users = userDao.getAllUser();
        if (users.size() > 0){
            for (User user:users){
                if (user.getUser_email().equals(email)){
                    user.setUser_password(encrypt_password(new_pwd));
                    userDao.editUser(user);
                }
            }
        }

        return new_pwd;
    }

    public  void sendMail(String email, String reset_password) {
        // 设置邮件服务器信息
        MailSenderInfo mailInfo = new MailSenderInfo();
        mailInfo.setMailServerHost("smtp.163.com");
        mailInfo.setMailServerPort("25");
        mailInfo.setValidate(true);

        // 邮箱用户名
        mailInfo.setUserName("18353507779@163.com");
        // 邮箱密码
        mailInfo.setPassword("zhangjiadong0418");
        // 发件人邮箱
        mailInfo.setFromAddress("18353507779@163.com");
        // 收件人邮箱
        mailInfo.setToAddress(email);
        // 邮件标题
        mailInfo.setSubject("schoolexchange");

        // 邮件内容
        StringBuffer buffer = new StringBuffer();
        buffer.append("password==：" + reset_password);
        mailInfo.setContent(buffer.toString());

        // 发送邮件
       /* SimpleMailSender sms = new SimpleMailSender();*/
        // 发送文体格式
       /* sms.sendTextMail(mailInfo);*/

        // 发送html格式
        SimpleMailSender.sendHtmlMail(mailInfo);
    }

    public String getUserUniversity(HttpSession session) {
        String university = null != session.getAttribute("sx_university")? (String)session.getAttribute("sx_university"):null;
        return university;
    }

    public void changePwd(String userName , String newPwd) {
        List<User> users = new ArrayList<User>();
        users = userDao.getAllUser();
        if (users.size() > 0){
            for (User user:users){
                if (user.getUser_name().equals(userName)){
                    user.setUser_password(encrypt_password(newPwd));
                    userDao.editUser(user);
                }
            }
        }
    }

    public User getCurrentUser(HttpSession session) {
        String currenrUserName = (String)session.getAttribute("sx_user_name");
        List<User> users = userDao.getAllUser();
        if (0 !=users.size() && currenrUserName != null){
            for (User user:users){
                if (user.getUser_name().equals(currenrUserName)){
                    return user;
                }
            }
        }
        return null;
    }

    public String solveGetMessyCode(String chineseCharacter) {
        try {
            chineseCharacter = new String(chineseCharacter.getBytes("iso-8859-1") ,"utf8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            System.out.println("乱码处理失败");
        }
        return chineseCharacter ;
    }

    public Date getCurrentTime() throws ParseException {
        Date create_time = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        String create_time_str = sf.format(create_time);
        return sf.parse(create_time_str);
    }


    //随机生成6位数字的密码
    public String getRandomPassword(){
        StringBuffer random_pwd = new StringBuffer();
        for (int i = 0 ; i < 6 ; i ++){
            random_pwd.append((int)(Math.random()*9 + 1));
        }

        return random_pwd.toString();

    }

    //密码加密
    public String  encrypt_password(String password){
        String encrypt_password = BCrypt.hashpw(password, BCrypt.gensalt());

        return encrypt_password;
    }
    //解密
    public boolean judge_password(String db_pwd , String input_pwd){

        return  BCrypt.checkpw(input_pwd,db_pwd);
    }
}
