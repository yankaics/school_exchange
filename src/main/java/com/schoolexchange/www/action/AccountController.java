package com.schoolexchange.www.action;

import com.schoolexchange.www.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by shadow on 2015/11/27.
 * AccountController类：账户管理
 */
@Controller
public class AccountController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/account")
    public String toPersonalCenter() {

        return "accountSetting/personal_center";
    }

    @RequestMapping(value = "/account/password_set")
    public String toResetPwd() {

        return "accountSetting/reset_password";
    }

    @RequestMapping(value = "/pwd_setting")
    public void resetPwd(String currentPwd, String newPwd, HttpServletResponse response, HttpSession session)
            throws IOException {
        String userName = (String)session.getAttribute("sx_user_name");
        boolean flag = userService.checkLogin(userName , currentPwd);
        if (flag){
            userService.changePwd(userName , newPwd);
            response.getWriter().write("success");
        }else {
            response.getWriter().write("fail");
        }

    }
}
