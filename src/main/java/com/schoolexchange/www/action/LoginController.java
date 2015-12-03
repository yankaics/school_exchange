package com.schoolexchange.www.action;

import com.schoolexchange.www.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by shadow
 * on 2015/11/19
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 跳转到登录界面
     */
    @RequestMapping(value = "to_login")
    public String login() {

        return "login";
    }

    /**
     * 网站首页
     *
     * @param model 存放大学
     * @return
     */
    @RequestMapping(value = "/")
    public String index(HttpSession session, Model model) {
        String sx_university = userService.getUserUniversity(session);
        if (sx_university == null) {
            session.setAttribute("sx_university", "烟台大学文经学院");
            model.addAttribute("sx_university", "烟台大学文经学院");
        } else {
            model.addAttribute("sx_university", sx_university);
        }
        return "index";
    }

    /**
     * Ajax验证登录
     */
    @RequestMapping(value = "/login")
    public void ajax_login(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {

        String user_name = null != request.getParameter("user_name") ? request.getParameter("user_name") : "";
        String user_password = null != request.getParameter("user_password") ? request.getParameter("user_password") : "";
        boolean flag = userService.checkLogin(user_name, user_password);
        if (flag) {
            String nameAndUniversity = userService.getUniversityByUserNameOrEmail(user_name);
            session.setAttribute("sx_user_name", nameAndUniversity.substring(nameAndUniversity.indexOf('$') + 1));
            session.setAttribute("sx_university", nameAndUniversity.substring(0, nameAndUniversity.indexOf('$')));
            session.setMaxInactiveInterval(60 * 60 * 24 * 30);
            response.getWriter().write("yes");
        } else {
            response.getWriter().write("no");
        }
    }

}
