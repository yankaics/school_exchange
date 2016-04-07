package com.schoolexchange.www.service.impl;

import com.schoolexchange.www.dao.MessageDao;
import com.schoolexchange.www.entity.Message;
import com.schoolexchange.www.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by shadow on 2016/4/7.
 * MessageService实现
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDao messageDao;

    @Override
    public void addMessage(Integer userId, Integer receiverId, String content) {
        Message message = new Message();
        message.setSend_user_id(userId);
        message.setAccept_user_id(receiverId);
        message.setMessage_content(content);
        message.setMessage_state(0);
        messageDao.saveMessage(message);
    }
}
