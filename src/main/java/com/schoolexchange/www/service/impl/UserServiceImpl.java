package com.schoolexchange.www.service.impl;

import com.schoolexchange.www.dao.UserDao;
import com.schoolexchange.www.entity.User;
import com.schoolexchange.www.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public boolean checkUserName() {

        return false;
    }
}
