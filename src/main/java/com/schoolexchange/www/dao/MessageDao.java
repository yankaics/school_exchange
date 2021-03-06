package com.schoolexchange.www.dao;

import com.schoolexchange.www.entity.GoodsComments;
import com.schoolexchange.www.entity.Message;

import java.util.List;

/**
 * Created by shadow on 2016/4/7.
 * Message表和goods_comment表操作
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

    /**
     * 添加评论到数据库
     *
     * @param comments 评论实体类
     */
    void addComment(GoodsComments comments);

    /**
     * 查询评论表
     *
     * @param goodId 商品id
     * @return 返回评论表集合
     */
    List<GoodsComments> queryGoodsComments(Integer goodId);

    /**
     * 查询消息统计
     *
     * @param userId 用户id
     * @return 消息统计
     */
    int unreadMessageCount(Integer userId);

    /**
     * 获取我的所有消息,按时间排序
     *
     * @param userId 用户id
     * @return 返回我的消息结果集合
     */
    List<Object[]> getMyMessage(Integer userId);

    /**
     * 修改用户信息状态
     *
     * @param userId 用户id
     */
    void updateMessageStatus(Integer userId);
}
