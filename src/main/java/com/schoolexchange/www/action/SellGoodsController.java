package com.schoolexchange.www.action;

import com.schoolexchange.www.entity.SellGoods;
import com.schoolexchange.www.entity.User;
import com.schoolexchange.www.service.SellGoodsService;
import com.schoolexchange.www.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by shadow
 * on 2015/12/8
 */
@Controller
public class SellGoodsController {

    @Autowired
    private UserService userService;

    @Autowired
    private SellGoodsService sellGoodsService;

    /**
     * 跳转到发布或编辑商品界面
     *
     * @param status  跳转状态,1发布商品,2编辑商品
     * @param session 当前session
     */
    @RequestMapping(value = "/to_release_goods")
    public String toReleaseGoods(String status, Model model, HttpSession session) throws Exception {
        if (null == status){
            status = "1";
        }
        if (!("1".equals(status) || "2".equals(status) || "3".equals(status))) {
            return "error";
        }
        //获取当前用户
        User user = userService.getCurrentUser(session);
        model.addAttribute("goods_count", user.getUser_goods_counts());
        model.addAttribute("status", status);
        return "releaseGoods/releaseGoods";
    }

    @RequestMapping(value = "/to_release_goods/update_goods")
    public void updateReleaseGoodsInfo(String status, String goods_name, @RequestParam("goods_price") String str_goods_price,
                                       @RequestParam("goods_count") String str_goods_count, String goods_desc,
                                       String goods_tag, @RequestParam("goods_deadline") String str_goods_deadline,
                                       String goods_address, String goods_contact, @RequestParam("goods_info") String encode_goods_info,
                                       HttpServletResponse response, @RequestParam MultipartFile[] release_goods_pic1,
                                       HttpSession session, HttpServletRequest request)
            throws Exception {
        //获取当前用户
        User user = userService.getCurrentUser(session);

        //日期转化
        /*str_goods_deadline = str_goods_deadline.equals("2016/6/13") ? "2016-6-13" : str_goods_deadline;*/
        //为sellGoods对象赋值
        SellGoods sellGoods = new SellGoods();
        sellGoods.setUser_id(user.getId());
        sellGoods.setGoods_name(goods_name);
        sellGoods.setGoods_deadline(new SimpleDateFormat("yyyy-MM-dd").parse(str_goods_deadline));
        sellGoods.setGoods_desc(goods_desc);
        sellGoods.setGoods_address(goods_address);
        sellGoods.setGoods_price(Double.parseDouble(str_goods_price));
        sellGoods.setGoods_count(Integer.parseInt(str_goods_count));
        sellGoods.setGoods_type(goods_tag);
        sellGoods.setGoods_info(URLDecoder.decode(encode_goods_info, "utf8"));
        sellGoods.setGoods_state(0);
        sellGoods.setCreate_time(new Date());
        sellGoods.setContact(goods_contact);
        //1插入、else更新
        if ("1".equals(URLDecoder.decode(status, "utf8"))) {
            System.out.println("状态=== " + sellGoodsService.validateSaveSellGoods(user));
            if (sellGoodsService.validateSaveSellGoods(user)) {
                sellGoods.setGoods_images(sellGoodsService.uploadGoodsPic(release_goods_pic1, request));
                //保存商品信息到数据库
                sellGoodsService.releaseGoods(sellGoods);
                //用户发布商品数量统计加1
                sellGoodsService.alterGoodsCounts(user, 1);
                //发布商品信息成功
                response.getWriter().write("1");
            } else {
                //用户发布商品数量已达上限
                response.getWriter().write("0");
            }


        } else {

        }


    }
}
