package com.schoolexchange.www.service.impl;

import com.schoolexchange.www.dao.MessageDao;
import com.schoolexchange.www.entity.Message;
import com.schoolexchange.www.entity.UnreadMessage;
import com.schoolexchange.www.service.MessageService;
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
}
