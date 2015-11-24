package com.schoolexchange.www.action;

import com.schoolexchange.www.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by shadow on 2015/11/18.
 * 注册事件
 * checkEmail方法:检测Email是否被注册
 * checkName方法:检测用户名是否被注册
 * registerAction:保存用户到数据库
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

    @RequestMapping(value = "/views/register_action")
    public void registerAction(HttpServletRequest request , HttpSession session){
        String user_email = null != request.getParameter("user_email")?request.getParameter("user_email"):"";
        String user_name = null != request.getParameter("user_name")?request.getParameter("user_name"):"";
        String user_password = null != request.getParameter("user_password")?request.getParameter("user_password"):"";
        String belong_university = null != request.getParameter("belong_university")?request.getParameter("belong_university"):"";

        userService.registerUser(user_email , user_name , user_password , belong_university);
        session.setAttribute("sx_user_name" ,user_name);
        session.setAttribute("sx_university" ,belong_university);
        //设置session存活一个月
        session.setMaxInactiveInterval(60*60*24*30);
    }
}
