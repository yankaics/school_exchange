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
 * Created by shadow on 2015/11/19.
 * LoginController:登录控制
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/views/index")
    public String index(){

        return "index";
    }

    @RequestMapping(value = "/views/to_register_user")
    public String toRegister(){

        return "register";
    }

    @RequestMapping(value = "/login")
    public void login(HttpServletRequest request , HttpServletResponse response , HttpSession session) throws IOException {

        String user_name = null != request.getParameter("user_name") ?request.getParameter("user_name"):"";
        String user_password = null != request.getParameter("user_password")?request.getParameter("user_password"):"";
        boolean flag = userService.checkLogin(user_name , user_password);
        if (flag){
            session.setAttribute("user_name", user_name);
            session.setAttribute("university" , userService.getUniversityByUserNameOrEmail(user_name));
            session.setMaxInactiveInterval(60*60*24*30);
            response.getWriter().write("yes");
        }else {
            response.getWriter().write("no");
        }
    }

}
