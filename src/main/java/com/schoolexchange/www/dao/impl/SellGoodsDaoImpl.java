package com.schoolexchange.www.dao.impl;

import com.schoolexchange.www.dao.SellGoodsDao;
import com.schoolexchange.www.entity.Collection;
import com.schoolexchange.www.entity.SellGoods;
import com.schoolexchange.www.entity.SellGoodsToUser;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator
 * on 2015/12/13
 */
@Repository
public class SellGoodsDaoImpl implements SellGoodsDao {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * 查询所有的商品
     *
     * @return 返回所有商品的集合
     */
    public List<SellGoods> getAllGoods() {
        List<SellGoods> list = null;
        String hql = "from SellGoods";
        list = sessionFactory.openSession()
                .createQuery(hql)
                .list();
        return list;
    }

    /**
     * 保存商品信息
     *
     * @param sellGoods 商品信息
     * @return 返回商品生成的主键id
     */
    public Integer saveSellGoods(SellGoods sellGoods) {

        return (Integer) sessionFactory.openSession().save(sellGoods);
    }

    /**
     * 商品统计
     *
     * @param hql        查询特定商品的语句
     * @param university 查询条件
     * @return 总的商品数量
     */
    public Integer getGoodsCount(String hql, String university) {

        return (Integer) sessionFactory.openSession().createQuery(hql)
                .setString(0, university)
                .setDate(1, new Date())
                .uniqueResult();
    }

    /**
     * 查询商品 (sell_goods表)
     *
     * @param hql 查询语句
     * @return 返回查询SellGoods的集合
     */
    public List<SellGoodsToUser> sellGoodsQuery(String hql) {

        return (List<SellGoodsToUser>) sessionFactory.openSession().createQuery(hql).list();
    }

    @Override
    public List<Object[]> queryCollection(String userName, Integer goodId) {
        String hql = "select c.id,c.goods_id from Collection c , User u where u.id=c.user_id and u.user_name=?0 and c.goods_id=?1";

        return sessionFactory.openSession().createQuery(hql)
                .setParameter("0", userName)
                .setParameter("1", goodId)
                .list();
    }

    @Override
    public void saveCollection(Collection collection) {
        sessionFactory.openSession().save(collection);
    }

    @Override
    public void deleteCollection(Integer goodsId, Integer userId) {
        Session session = sessionFactory.openSession();
        String hql = "delete Collection  where goods_id=?0 and  user_id=?1";
        Query q = session.createQuery(hql);
        q.setInteger("0", goodsId);
        q.setInteger("1", userId);
        q.executeUpdate();
    }

    @Override
    public List<Object[]> queryMyCollection(Integer user_id) {
        String hql = "select c.goods_id,sg.goods_name from Collection c , SellGoods sg where  c.goods_id=sg.id and c.user_id=?0 ";
        return sessionFactory.openSession().createQuery(hql)
                .setParameter("0", user_id)
                .list();
    }

    @Override
    public void deleteGoodsById(Integer goodsId) {
        String hql = "delete  SellGoods sg where sg.id=?0";
        sessionFactory.openSession().createQuery(hql)
                .setParameter("0", goodsId)
                .executeUpdate();
    }


}
