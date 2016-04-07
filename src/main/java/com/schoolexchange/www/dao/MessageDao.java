package com.schoolexchange.www.dao;

import com.schoolexchange.www.entity.Message;

/**
 * Created by shadow on 2016/4/7.
 * Message表操作
 */
public interface MessageDao {

    void saveMessage(Message message);
}
