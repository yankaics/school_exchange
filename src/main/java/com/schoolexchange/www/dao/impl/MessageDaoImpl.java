package com.schoolexchange.www.dao.impl;

import com.schoolexchange.www.dao.MessageDao;
import com.schoolexchange.www.entity.Message;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Override
    public List<Object[]> queryUnreadMessage(Integer userId) {
        String hql = "select u.user_name,m.message_content from User u,Message m where u.id=m.send_user_id and m.accept_user_id=?0 and m.message_state=0 order by m.publishDate DESC";
        return sessionFactory.openSession().createQuery(hql)
                .setParameter("0",userId)
                .list();
    }
}
