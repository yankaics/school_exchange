package com.schoolexchange.www.dao.impl;

import com.schoolexchange.www.dao.MessageDao;
import com.schoolexchange.www.entity.GoodsComments;
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
                .setParameter("0", userId)
                .list();
    }

    @Override
    public void addComment(GoodsComments comments) {
        sessionFactory.openSession().save(comments);
    }

    @Override
    public List<GoodsComments> queryGoodsComments(Integer goodId) {
        String hql = "from GoodsComments gc where gc.sell_goods_id=?0 order by gc.comment_time desc";

        return sessionFactory.openSession().createQuery(hql)
                .setParameter("0", goodId)
                .list();

    }

    @Override
    public int unreadMessageCount(Integer userId) {
        String hql = "select count(*) from Message m where m.accept_user_id=?0 and m.message_state=0";
        Long count =  (Long) sessionFactory.openSession().createQuery(hql)
                .setParameter("0", userId)
                .list().get(0);
        return count.intValue();

    }
}
