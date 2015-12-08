package com.schoolexchange.www.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by shadow
 * on 2015/12/8
 */
@Controller
public class SellGoodsController {

    @RequestMapping(value = "/to_release_goods")
    public String toReleaseGoods(){

        return "releaseGoods/releaseGoods";
    }
}
