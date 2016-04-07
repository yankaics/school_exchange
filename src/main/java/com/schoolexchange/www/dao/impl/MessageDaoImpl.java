package com.schoolexchange.www.dao.impl;

import com.schoolexchange.www.dao.MessageDao;
import com.schoolexchange.www.entity.Message;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by shadow on 2016/4/7.
 * Message操作
 */
@Repository
public class MessageDaoImpl implements MessageDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveMessage(Message message) {
        sessionFactory.openSession().save(message);
    }
}
