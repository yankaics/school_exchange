package com.schoolexchange.www.service.impl;

import com.schoolexchange.www.dao.MessageDao;
import com.schoolexchange.www.entity.GoodsComments;
import com.schoolexchange.www.entity.GoodsCommentsVo;
import com.schoolexchange.www.entity.Message;
import com.schoolexchange.www.entity.UnreadMessage;
import com.schoolexchange.www.service.MessageService;
import com.schoolexchange.www.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by shadow on 2016/4/7.
 * MessageService实现
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDao messageDao;

    @Autowired
    private UserService userService;

    @Override
    public void addMessage(Integer userId, Integer receiverId, String content) {
        Message message = new Message();
        message.setSend_user_id(userId);
        message.setAccept_user_id(receiverId);
        message.setMessage_content(content);
        message.setMessage_state(0);
        message.setPublishDate(new Date());
        messageDao.saveMessage(message);
    }

    @Override
    public List<UnreadMessage> getUnreadMessage(Integer userId) throws UnsupportedEncodingException {
        List<UnreadMessage> messages = new ArrayList<>();
        List<Object[]> list = messageDao.queryUnreadMessage(userId);
        int size = list.size() > 4 ? 4 : list.size();
        for (int i = 0; i < size; i++) {
            String name = URLEncoder.encode(list.get(i)[0].toString().replace(" ", ""), "utf8");
            String content = URLEncoder.encode(list.get(i)[1].toString().replace(" ", ""), "utf8");
            messages.add(new UnreadMessage(name, content));
        }
        return messages;
    }

    @Override
    public void addComments(Integer userId, Integer goodId, String commentContent) {
        GoodsComments comments = new GoodsComments();
        comments.setUser_id(userId);
        comments.setSell_goods_id(goodId);
        comments.setComment_content(commentContent);
        comments.setComment_time(new Date());
        messageDao.addComment(comments);
    }

    @Override
    public List<GoodsCommentsVo> queryAllCommentsById(Integer goodId) {
        List<GoodsCommentsVo> list = new ArrayList<>();
        List<GoodsComments> gc = messageDao.queryGoodsComments(goodId);
        if (0 != gc.size()) {
            for (GoodsComments c : gc) {
                list.add(new GoodsCommentsVo(userService.getUserNameByUserId(c.getUser_id()), c.getComment_content(), c.getComment_time()));
            }
        } else {
            return null;
        }
        return list;
    }
}
