package com.schoolexchange.www.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by shadow on 2015/11/27.
 *
 */
@Controller
public class AccountController {

    @RequestMapping(value = "/account")
    public String toPersonalCenter(){

        return "accountSetting/personal_center";
    }

    @RequestMapping(value = "/account/password_set")
    public String toResetPwd(){

        return "accountSetting/reset_password";
    }
}
