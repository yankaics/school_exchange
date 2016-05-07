package com.schoolexchange.www.service.impl;

import com.schoolexchange.www.dao.BrowseRecordDao;
import com.schoolexchange.www.entity.BrowseRecords;
import com.schoolexchange.www.service.BrowseRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by shadow on 2016/5/5.
 */
@Service
public class BrowseRecordServiceImpl implements BrowseRecordService {

    @Autowired
    private BrowseRecordDao browseRecordDao;

    @Override
    public void saveBrowseRecord(Integer userId, Integer goodsId) {
        BrowseRecords browseRecords = new BrowseRecords();
        browseRecords.setUserId(userId);
        browseRecords.setGoodsId(goodsId);
        browseRecords.setbDate(new Date());
        browseRecordDao.saveRecord(browseRecords);
    }

    @Override
    public boolean isExistRecord(Integer userId, Integer goodsId) {
        BrowseRecords browseRecords = new BrowseRecords();
        browseRecords.setUserId(userId);
        browseRecords.setGoodsId(goodsId);
        Number c = browseRecordDao.queryCount(browseRecords);
        if (0 == c.intValue()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void updateMyRecord(Integer userId, Integer goodsId) {
        BrowseRecords browseRecords = new BrowseRecords();
        browseRecords.setUserId(userId);
        browseRecords.setGoodsId(goodsId);
        browseRecords.setbDate(new Date());
        browseRecordDao.updateRecord(browseRecords);
    }

    @Override
    public List<BrowseRecords> queryAllMyBrowseRecord(Integer userId) {
        List<BrowseRecords> list = new ArrayList<>();
        List<Object[]> objectses = browseRecordDao.queryAllMyBrowseRecord(userId);
        for (int i = 0; i < objectses.size(); i++) {
            BrowseRecords browseRecords = new BrowseRecords();
            browseRecords.setGoodsId((Integer) objectses.get(i)[0]);
            browseRecords.setGoodsName(objectses.get(i)[2].toString());
            browseRecords.setbDate((Date) objectses.get(i)[1]);
            list.add(browseRecords);
        }
        return list;
    }
}
