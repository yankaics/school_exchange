package com.schoolexchange.www.dao;

import com.schoolexchange.www.entity.BrowseRecords;

import java.util.List;

/**
 * Created by Administrator on 2016/5/5.
 */
public interface BrowseRecordDao {

    /**
     * 保存浏览记录
     *
     * @param browseRecords 浏览记录实体类
     */
    void saveRecord(BrowseRecords browseRecords);

    /**
     * 更新浏览记录
     *
     * @param browseRecords 浏览记录实体类
     */
    void updateRecord(BrowseRecords browseRecords);

    /**
     * 查询记录的存在
     *
     * @param browseRecords 浏览记录实体类
     * @return 1已经记录了，0还没记录
     */
    Number queryCount(BrowseRecords browseRecords);

    /**
     * 查询我的浏览记录
     *
     * @param userId 用户id
     * @return 返回所有商品id，商品名，时间组成的集合
     */
    List<Object[]> queryAllMyBrowseRecord(Integer userId);
}
