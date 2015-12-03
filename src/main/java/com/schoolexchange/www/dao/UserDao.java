package com.schoolexchange.www.dao;

import com.schoolexchange.www.entity.User;

import java.util.List;

/**
 * Created by shadow
 * on 2015/11/18.
 */
public interface UserDao {

    /**
     * 查询所有的用户
     *
     * @return 返回所有的用户集合
     */
    List<User> getAllUser();

    /**
     * 保存用户信息到数据库
     *
     * @param user 用户信息
     */
    void saveUser(User user);

    /**
     * 修改用户的密码信息
     *
     * @param user 用户信息
     */
    void editUser(User user);

    /**
     * 更新用户信息
     *
     * @param user 用户信息
     * @see com.schoolexchange.www.service.impl.UserServiceImpl
     */
    void updateUser(User user);
}
