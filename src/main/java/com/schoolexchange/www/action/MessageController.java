package com.schoolexchange.www.action;

import com.schoolexchange.www.entity.UnreadMessage;
import com.schoolexchange.www.service.MessageService;
import com.schoolexchange.www.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shadow on 2016/4/15.
 * MessageController类
 */
@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    /**
     * 获取未读信息统计
     */
    @RequestMapping(value = "/queryMessageCount")
    void getUnreadMessageCount(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String user_name = (String) request.getSession().getAttribute("sx_user_name");
        if (null != user_name) {
            Integer userId = userService.getUserIdByUserName(user_name);
            int message_count = messageService.queryUnreadMessageCount(userId);
            response.getWriter().write(String.valueOf(message_count));
        } else {
            //session过期,提醒登录
            response.getWriter().write("no");
        }
    }

    @RequestMapping(value = "/to_redirect_my_message")
    public String toRedirectMyMessage(HttpServletRequest request, Model model) {
        String user_name = (String) request.getSession().getAttribute("sx_user_name");
        List<UnreadMessage> list = new ArrayList<>();
        if (null != user_name) {
            Integer userId = userService.getUserIdByUserName(user_name);
            list = messageService.getMyMessage(userId);
        }
        if (0 == list.size()) {
            //没有任何消息
            model.addAttribute("status", 1);
        } else {
            model.addAttribute("status", 0);
            model.addAttribute("list", list);
        }
        return "my_message";
    }
}
