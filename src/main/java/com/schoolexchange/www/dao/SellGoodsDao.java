package com.schoolexchange.www.dao;

import com.schoolexchange.www.entity.SellGoods;
import com.schoolexchange.www.entity.SellGoodsToUser;

import java.util.List;

/**
 * Created by Administrator
 * on 2015/12/13
 */
public interface SellGoodsDao {

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
     * @return 返回商品生成的主键id
     */
    Integer saveSellGoods(SellGoods sellGoods);

    /**
     * 商品统计
     *
     * @param hql        查询特定商品的语句
     * @param university 查询条件
     * @return 总的商品数量
     */
    Integer getGoodsCount(String hql, String university);

    /**
     * 查询商品 (sell_goods表)
     *
     * @param hql 查询语句
     * @return 返回查询SellGoods的集合
     */
    List<SellGoodsToUser> sellGoodsQuery(String hql);
}
