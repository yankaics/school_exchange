package com.schoolexchange.www.dao;

import com.schoolexchange.www.entity.User;

import java.util.List;

/**
 * Created by shadow on 2015/11/18.
 * UserDao提供对user表的增删改查
 */
public interface UserDao {

    public List<User> getAllUser();

    public void saveUser(User user);

    public void editUser(User user);

    public void delUser(User user);
}
