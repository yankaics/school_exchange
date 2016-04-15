package com.schoolexchange.www.service;

import com.schoolexchange.www.entity.GoodsCommentsVo;
import com.schoolexchange.www.entity.UnreadMessage;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by shadow on 2016/4/7.
 * 消息(主要是评论和留言)
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

    /**
     * 查询用户未读信息
     *
     * @param userId 用户id
     * @return 返回未读信息集合
     */
    List<UnreadMessage> getUnreadMessage(Integer userId) throws UnsupportedEncodingException;

    /**
     * 评论
     *
     * @param userId         用户id
     * @param goodId         商品id
     * @param commentContent 评论内容
     */
    void addComments(Integer userId, Integer goodId, String commentContent);

    /**
     * 查询该商品的所有评论
     *
     * @param goodId 商品id
     * @return 商品评论集合
     */
    List<GoodsCommentsVo> queryAllCommentsById(Integer goodId);

    /**
     * 查询用户未读信息统计
     *
     * @param userId 用户id
     * @return 消息统计
     */
    int queryUnreadMessageCount(Integer userId);
}
