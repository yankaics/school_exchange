package com.schoolexchange.www.dao;

import com.schoolexchange.www.entity.Message;

import java.util.List;

/**
 * Created by shadow on 2016/4/7.
 * Message表操作
 */
public interface MessageDao {
    /**
     * 保存消息
     *
     * @param message 信息
     */
    void saveMessage(Message message);

    /**
     * 查询未读信息
     *
     * @param userId 用户id
     * @return 返回查询结果Object集合
     * @see com.schoolexchange.www.service.impl.MessageServiceImpl
     */
    List<Object[]> queryUnreadMessage(Integer userId);
}
