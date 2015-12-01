package com.schoolexchange.www.dao;

import com.schoolexchange.www.entity.User;

import java.util.List;

/**
 * Created by shadow on 2015/11/18.
 * UserDao提供对user表的增删改查
 */
public interface UserDao {

    List<User> getAllUser();

    void saveUser(User user);

    //修改密码
    void editUser(User user);

    //更新用户信息
    void updateUser(User user);
}
