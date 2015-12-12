package com.schoolexchange.www.action;

import com.schoolexchange.www.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by shadow
 * on 2015/12/8
 */
@Controller
public class SellGoodsController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/to_release_goods")
    public String toReleaseGoods() {

        return "releaseGoods/releaseGoods";
    }

    @RequestMapping(value = "/to_release_goods/update_goods")
    public void updateReleaseGoodsInfo(String goods_info, HttpServletResponse response
            , @RequestParam MultipartFile[] release_goods_pic1)
            throws IOException {
        if (null != release_goods_pic1) {
            System.out.println(release_goods_pic1[0].getOriginalFilename());
        }
        goods_info = userService.solveGetMessyCode(goods_info);
        System.out.println("goods_info === " + goods_info);
        response.getWriter().write("succes");
    }
}
