package com.schoolexchange.www.dao.impl;

import com.schoolexchange.www.dao.BrowseRecordDao;
import com.schoolexchange.www.entity.BrowseRecords;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by shadow on 2016/5/5.
 * 浏览记录dao层所有数据库操作
 */
@Repository
public class BrowseRecordDaoImpl implements BrowseRecordDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveRecord(BrowseRecords browseRecords) {
        sessionFactory.openSession().save(browseRecords);
    }

    @Override
    public void updateRecord(BrowseRecords browseRecords) {
        //修改失败了，不知道问什么
        //先删除在插入
        String hql = "delete BrowseRecords br where br.userId="+browseRecords.getUserId()+" and br.goodsId=" + browseRecords.getGoodsId();
        sessionFactory.openSession().createQuery(hql).executeUpdate();
        saveRecord(browseRecords);
    }

    @Override
    public Number queryCount(BrowseRecords browseRecords) {
        String hql = "select count(*) from BrowseRecords br where br.userId=?0 and br.goodsId=?1";
        return (Number) sessionFactory.openSession().createQuery(hql)
                .setParameter("0", browseRecords.getUserId())
                .setParameter("1", browseRecords.getGoodsId())
                .uniqueResult();

    }
}
