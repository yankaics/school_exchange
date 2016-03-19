package com.schoolexchange.www.action;

import com.schoolexchange.www.service.RequestUrlSecurity;
import com.schoolexchange.www.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by shadow
 * on 2015/11/18.
 */
@Controller
public class CheckRegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private RequestUrlSecurity requestUrlSecurity;

    /**
     * 跳转到注册用户界面
     */
    @RequestMapping(value = "/to_register")
    public String register() {

        return "register";
    }

    /**
     * Ajax验证邮箱是否存在
     */
    @RequestMapping(value = "/check_email")
    public void checkEmail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        boolean flag = userService.checkEmail(email);
        if (flag) {
            response.getWriter().write("no");
        } else {
            response.getWriter().write("yes");
        }
    }

    /**
     * Ajax验证用户名是否存在
     */
    @RequestMapping(value = "/check_name")
    public void checkUserName(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String user_name = null != request.getParameter("user_name") ? request.getParameter("user_name") : "";
        boolean flag = userService.checkUserName(user_name);
        if (flag) {
            response.getWriter().write("no");
        } else {
            response.getWriter().write("yes");
        }

    }

    /**
     * 注册用户信息到数据库
     */
    @RequestMapping(value = "/register_action")
    public void registerAction(HttpServletRequest request, HttpSession session) throws Exception {
        String user_email = null != request.getParameter("user_email") ? request.getParameter("user_email") : "";
        String user_name = null != request.getParameter("user_name") ? request.getParameter("user_name") : "";
        String user_password = null != request.getParameter("user_password") ? request.getParameter("user_password") : "";
        String belong_university = null != request.getParameter("belong_university") ? request.getParameter("belong_university") : "";
        String activateLink = "http://localhost:8080/activate_user?status=" + requestUrlSecurity.encrypt(user_email);
        String emailContent = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <title>注册用户</title>\n" +
                "    <meta charset='UTF-8'>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<h3>点击激活:</h3>\n" +
                "<a href='" + activateLink + "'>" + activateLink + "</a>" +
                "</body>\n" +
                "</html>";
        userService.sendMail(user_email, emailContent);
        userService.registerUser(user_email, user_name, user_password, belong_university);
        /*session.setAttribute("sx_user_name", user_name);
        session.setAttribute("sx_university", belong_university);*/
        //设置session存活一个月
        session.setMaxInactiveInterval(60 * 60 * 24 * 30);
    }

    @RequestMapping(value = "/activate_user")
    public String activateUser(@RequestParam(value = "status", required = false) String activate_email) throws Exception {
        String activate_status = "no";
        if (null != activate_email) {
            String email = requestUrlSecurity.decrypt(activate_email);
            if (userService.checkEmail(email)) {
                userService.activateUser(email);
                activate_status = "yes";
            }
        }

        return "redirect:to_login?activate_status=" + activate_status;
    }
}
