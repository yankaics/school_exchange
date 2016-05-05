package com.schoolexchange.www.dao;

import com.schoolexchange.www.entity.BrowseRecords;

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
}
