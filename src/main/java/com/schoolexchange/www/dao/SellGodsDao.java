package com.schoolexchange.www.dao;

import com.schoolexchange.www.entity.SellGoods;

import java.util.List;

/**
 * Created by Administrator
 * on 2015/12/13
 */
public interface SellGodsDao {

    /**
     * 查询所有的商品
     *
     * @return 返回所有商品的集合
     */
    List<SellGoods> getAllGoods();

    /**
     * 保存商品信息
     *
     * @param sellGoods 商品信息
     */
    void saveSellGoods(SellGoods sellGoods);
}
