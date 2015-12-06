package com.schoolexchange.www.action;

import com.schoolexchange.www.service.RequestUrlSecurity;
import com.schoolexchange.www.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @Autowired
    private RequestUrlSecurity requestUrlSecurity;

    /**
     * 跳转到找回密码的页面
     */
    @RequestMapping(value = "/to_get_password")
    public String to_forget_password() {

        return "forget_password";
    }

    /**
     * 发送邮件通知用户重置密码
     */
    @RequestMapping(value = "/reset_password")
    public void reset_password(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String email = request.getParameter("email");
        boolean flag = userService.checkEmail(email);
        String resetUrl = "http://localhost:8080/to_execute_reset_pwd?status=" + requestUrlSecurity.encrypt(email);
        String reset_link = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <title>忘记密码</title>\n" +
                "    <meta charset='UTF-8'>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<h3>点击重设密码:</h3>\n" +
                "<a href='" + resetUrl + "'>" + resetUrl + "</a>" +
                "</body>\n" +
                "</html>";
        if (flag) {
            //重置密码
            /*String reset_password = userService.resetPassword(email);*/
            userService.sendMail(email, reset_link);
            response.getWriter().write("no");
        } else {
            response.getWriter().write("yes");
        }
    }

    @RequestMapping("/to_execute_reset_pwd")
    public String toExecuteResetPwd(@RequestParam(value = "status", required = false) String resetPwd
            , Model model) {
        if (null != resetPwd && 0 != resetPwd.length()) {
            String email = null;
            try {
                email = requestUrlSecurity.decrypt(resetPwd);
            } catch (Exception e) {
                return "login";
            }
            model.addAttribute("resetInfo", email);
            return "to_reset_pwd";
        } else {
            return "login";
        }
    }

    @RequestMapping("/execute_reset_pwd")
    public void executeResetPwd(String resetEmail, String new_pwd, HttpServletResponse response) throws IOException {
        userService.resetPassword(resetEmail, new_pwd);
        response.getWriter().write("yes");
        /*return "redirect:to_login?reset_pwd_status=1";*/
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
