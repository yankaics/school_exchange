package com.schoolexchange.www.action;

import com.schoolexchange.www.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by shadow on 2015/11/18.
 * 注册事件
 */
@Controller
public class CheckRegisterController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/views/check_email")
    public void checkEmail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        boolean flag = userService.checkEmail(email);
        if (flag) {
            response.getWriter().write("no");
        } else {
            response.getWriter().write("yes");
        }
    }

    @RequestMapping(value = "/views/check_name")
    public void checkUserName(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String user_name = null != request.getParameter("user_name") ? request.getParameter("user_name") : "";
        boolean flag = userService.checkUserName(user_name);
        if (flag){
            response.getWriter().write("no");
        }else {
            response.getWriter().write("yes");
        }

    }
}
