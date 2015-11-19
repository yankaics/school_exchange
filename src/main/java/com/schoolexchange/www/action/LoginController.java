package com.schoolexchange.www.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by shadow on 2015/11/19.
 * LoginController:登录控制
 */
@Controller
public class LoginController {

    @RequestMapping(value = "/views/to_register_user")
    public String toRegister(){

        return "register";
    }
}
