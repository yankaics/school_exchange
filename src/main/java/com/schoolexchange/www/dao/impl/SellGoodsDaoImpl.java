package com.schoolexchange.www.dao.impl;

import com.schoolexchange.www.dao.SellGoodsDao;
import com.schoolexchange.www.entity.SellGoods;
import com.schoolexchange.www.entity.SellGoodsToUser;
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


}
