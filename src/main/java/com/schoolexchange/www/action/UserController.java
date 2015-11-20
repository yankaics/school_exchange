package com.schoolexchange.www.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by shadow on 2015/11/20.
 * UserController类
 */
@Controller
public class UserController {

    @RequestMapping(value = "/views/to_get_password")
    public String to_forget_password(){

        return "forget_password";
    }
}
