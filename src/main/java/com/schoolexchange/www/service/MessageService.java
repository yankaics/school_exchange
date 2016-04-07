package com.schoolexchange.www.service;

/**
 * Created by shadow on 2016/4/7.
 * 留言业务
 */
public interface MessageService {

    /**
     * 增加留言
     *
     * @param userId     发送消息用户id
     * @param receiverId 接受消息用户id
     * @param content    发送内容
     * @see com.schoolexchange.www.action.SellGoodsController
     */
    void addMessage(Integer userId, Integer receiverId, String content);
}
