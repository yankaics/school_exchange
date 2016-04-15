package com.schoolexchange.www.action;

import com.google.gson.Gson;
import com.qiniu.api.auth.AuthException;
import com.schoolexchange.www.entity.SellGoodsToUser;
import com.schoolexchange.www.service.QiniuService;
import com.schoolexchange.www.service.RequestUrlSecurity;
import com.schoolexchange.www.service.SellGoodsService;
import com.schoolexchange.www.service.UserService;
import org.apache.commons.codec.EncoderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shadow
 * on 2015/11/19
 */
@Controller
public class LoginController {

    private static final Logger loogger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RequestUrlSecurity requestUrlSecurity;

    @Autowired
    private SellGoodsService sellGoodsService;

    @Autowired
    private QiniuService qiniuService;

    /**
     * 跳转到登录界面
     */
    @RequestMapping(value = "to_login")
    public String login(@RequestParam(value = "requestUrl", required = false) String requestUrl,
                        HttpServletResponse response, String activate_status, Model model, String reset_pwd_status) {
        String decryptUrl = null;
        if (null != requestUrl) {
            try {
                //解密url
                decryptUrl = requestUrlSecurity.decrypt(requestUrl);
                Cookie cookie = new Cookie("requestUrl", decryptUrl);
                //存活2分钟
                cookie.setMaxAge(2 * 60);
                response.addCookie(cookie);
            } catch (Exception e) {
                System.out.println("url错误无法解密" + decryptUrl);
            }
        }

        if (null != activate_status) {
            model.addAttribute("activate_status", activate_status);
        }

        if (null != reset_pwd_status) {
            model.addAttribute("reset_pwd_status", reset_pwd_status);
        }
        return "login";
    }

    /**
     * 网站首页
     *
     * @param model 存放大学
     */
    @RequestMapping(value = "/")
    public String index(HttpSession session, Model model) throws AuthException, EncoderException {
        loogger.info("index visit log.............");
        String sx_university = userService.getUserUniversity(session);
        if (sx_university == null) {
            session.setAttribute("sx_university", "烟台大学文经学院");
            model.addAttribute("sx_university", "烟台大学文经学院");
        } else {
            model.addAttribute("sx_university", sx_university);
        }
        List<SellGoodsToUser> list = sellGoodsService.getPageContent(1, 30, (String) session.getAttribute("sx_university"));
        for (SellGoodsToUser sellGoodsToUser : list) {
            qiniuService.setAccessKey("Zm0x_pMEfrKAWYlzSAnMXvdEXuOP3kaCFhBebuf4");
            qiniuService.setSecretKey("Ypu9e__2WJxsL-MoUTGqUR4EyexVMdXd_DT-4Olx");
            qiniuService.setDomain("7xo7z2.com1.z0.glb.clouddn.com");
            qiniuService.setBucketName("schoolexchange");
            String goodsUrl = qiniuService.getDownloadFileUrl(sellGoodsToUser.getGoods_images());
            sellGoodsToUser.setGoods_images(goodsUrl);
        }

        model.addAttribute("indexGoods", list);
       /* System.out.println("结果=====" + sellGoodsService.getUniversityGoodsCount(0, "烟台大学文经学院"));*/
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
        //map存储返回的结果
        Map<String, String> map = new HashMap<String, String>();
        //把map转化为json格式
        Gson gson = new Gson();
        if (flag) {
            String nameAndUniversity = userService.getUniversityByUserNameOrEmail(user_name);
            session.setAttribute("sx_user_name", nameAndUniversity.substring(nameAndUniversity.indexOf('$') + 1));
            session.setAttribute("sx_university", nameAndUniversity.substring(0, nameAndUniversity.indexOf('$')));
            session.setMaxInactiveInterval(60 * 60 * 24 * 30);
            //通过session获取跳转的requestUrl
            Cookie[] cookies = request.getCookies();
            map.put("result", "yes");
            if (null != cookies) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("requestUrl")) {
                        String requestUrl = cookie.getValue();
                        //销毁requestUrl
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                        map.put("url", requestUrl);
                        response.getWriter().write(gson.toJson(map));
                        return;
                    }
                }
            }
            response.getWriter().write(gson.toJson(map));
        } else {
            map.put("result", "no");
            response.getWriter().write(gson.toJson(map));
        }
    }

    @RequestMapping(value = "/selectUniversity")
    public void selectUniversity(HttpServletRequest request, HttpServletResponse response,
                                String selectUniversity) throws IOException {
        String sessionUniversity = (String) request.getSession().getAttribute("sx_university");
        if (sessionUniversity.equals(selectUniversity)){
            //选择的学校和当前session中一样，不需要跳转
            response.getWriter().write("no");
        }else {
            request.getSession().setAttribute("sx_university", selectUniversity);
            response.getWriter().write("yes");
        }

    }

}
