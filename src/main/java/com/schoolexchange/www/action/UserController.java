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
 * Created by shadow
 * on 2015/11/20.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 跳转到找回密码的页面
     */
    @RequestMapping(value = "/to_get_password")
    public String to_forget_password() {

        return "forget_password";
    }

    /**
     * 重置密码并发送邮件通知用户
     */
    @RequestMapping(value = "/reset_password")
    public void reset_password(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        boolean flag = userService.checkEmail(email);
        if (flag) {
            //重置密码
            String reset_password = userService.resetPassword(email);
            userService.sendMail(email, reset_password);
            response.getWriter().write("no");
        } else {
            response.getWriter().write("yes");
        }
    }

    /**
     * 退出操作，销毁session
     */
    @RequestMapping(value = "/logout")
    public String logou_login(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
