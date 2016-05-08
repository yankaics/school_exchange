package com.schoolexchange.www.dao;

import com.schoolexchange.www.entity.Collection;
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

    /**
     * 通过用户名和商品id查询该商品是否被收藏
     *
     * @param userName 用户名
     * @param goodId   商品id
     */
    List<Object[]> queryCollection(String userName, Integer goodId);

    /**
     * 增加收藏记录
     *
     * @param collection 收藏实体类
     */
    void saveCollection(Collection collection);

    /**
     * 取消收藏,直接删除collection表
     *
     * @param goodsId 商品id
     * @param userId  用户id
     */
    void deleteCollection(Integer goodsId, Integer userId);

    /**
     * 查询我的商品收藏
     *
     * @param user_id 当前用户id
     */
    List<Object[]> queryMyCollection(Integer user_id);

    void deleteGoodsById(Integer goodsId);
}
