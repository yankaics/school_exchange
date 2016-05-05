package com.schoolexchange.www.service;

/**
 * Created by shadow on 2016/5/5.
 * 浏览记录业务
 */
public interface BrowseRecordService {

    /**
     * 保存商品id
     *
     * @param userId  用户id
     * @param goodsId 商品id
     * @see com.schoolexchange.www.action.BrowseRecordController
     */
    void saveBrowseRecord(Integer userId, Integer goodsId);

    /**
     * 判断浏览记录是否存在
     *
     * @param userId  用户id
     * @param goodsId 商品id
     * @return true存在，false不存在
     */
    boolean isExistRecord(Integer userId, Integer goodsId);

    /**
     * 已经浏览过了，更新我的浏览记录
     *
     * @param userId  用户id
     * @param goodsId 商品id
     */
    void updateMyRecord(Integer userId, Integer goodsId);
}
