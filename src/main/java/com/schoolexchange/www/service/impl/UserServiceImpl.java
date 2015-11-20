package com.schoolexchange.www.service.impl;

import com.schoolexchange.www.dao.UserDao;
import com.schoolexchange.www.entity.User;
import com.schoolexchange.www.service.UserService;
import com.schoolexchange.www.tools.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void registerUser(String user_email, String user_name, String user_password, String belong_university) {
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

    public Date getCurrentTime() throws ParseException {
        Date create_time = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        String create_time_str = sf.format(create_time);
        return sf.parse(create_time_str);
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
