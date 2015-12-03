package com.schoolexchange.www.dao.impl;

import com.schoolexchange.www.dao.UserDao;
import com.schoolexchange.www.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shadow
 * on 2015/11/18.
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * 查询所有的用户
     *
     * @return 返回所有的用户集合
     */
    public List<User> getAllUser() {

        List<User> users = new ArrayList<User>();

        users = sessionFactory.openSession()
                .createQuery("from User")
                .list();
        return users;
    }

    public void saveUser(User user) {
        sessionFactory.openSession().save(user);
    }

    /**
     * 修改用户的密码信息
     *
     * @param user 用户信息
     */
    public void editUser(User user) {

        String hqlUpdate = "update User u set u.user_password = :new_password where u.user_email = :user_email ";
        sessionFactory.openSession().createQuery(hqlUpdate)
                .setString("new_password", user.getUser_password())
                .setString("user_email", user.getUser_email())
                .executeUpdate();
    }

    /**
     * 更新用户信息
     *
     * @param user 用户信息
     * @see com.schoolexchange.www.service.impl.UserServiceImpl
     */
    public void updateUser(User user) {
        String hql = "update User u set u.user_name = ? , u.user_sex = ? " +
                ", u.user_birth = ? , u.user_university = ? ," +
                " u.user_professional = ? , u.user_motto = ? , u.user_faces = ? " +
                " where u.user_email = ?";
        sessionFactory.openSession().createQuery(hql)
                .setString(0, user.getUser_name())
                .setInteger(1, user.getUser_sex())
                .setDate(2, user.getUser_birth())
                .setString(3, user.getUser_university())
                .setString(4, user.getUser_professional())
                .setString(5, user.getUser_motto())
                .setString(6, user.getUser_faces())
                .setString(7, user.getUser_email())
                .executeUpdate();
       /*  String hql = "update User u set u.user_name = ? , u.user_sex = ? and " +
                " where u.user_email = 'zhangjiadong0418@qq.com'";
        sessionFactory.openSession().createQuery(hql)
                                    .setString(0 , user.getUser_name())
                                    .setInteger(1, user.getUser_sex())
                                    .executeUpdate();*/

       /* System.out.println("性别序号===" + user.getUser_sex());
        Session session = sessionFactory.openSession();
        Transaction ts = session.beginTransaction();
        User u = (User) session.get(User.class, user.getId());
        System.out.println("database=======" +u.getUser_sex());
        u.setUser_sex(u.getUser_sex());
        *//*session.update(u);*//*
        session.flush();
        ts.commit();
        session.close();*/

    }

}
