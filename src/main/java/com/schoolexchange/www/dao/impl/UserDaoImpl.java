package com.schoolexchange.www.dao.impl;

import com.schoolexchange.www.dao.UserDao;
import com.schoolexchange.www.entity.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shadow on 2015/11/18.
 *
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

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

    public void editUser(User user) {

        String hqlUpdate = "update User u set u.user_password = :new_password where u.user_email = :user_email ";
        sessionFactory.openSession().createQuery(hqlUpdate)
                                    .setString("new_password" , user.getUser_password())
                                    .setString("user_email" , user.getUser_email())
                                    .executeUpdate();
    }

}
