package com.schoolexchange.www.dao.impl;

import com.schoolexchange.www.dao.SellGoodsDao;
import com.schoolexchange.www.entity.SellGoods;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

        return (Integer)sessionFactory.openSession().save(sellGoods);
    }
}
