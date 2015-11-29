package com.schoolexchange.www.action;

import com.qiniu.api.auth.AuthException;
import com.schoolexchange.www.entity.User;
import com.schoolexchange.www.service.QiniuService;
import com.schoolexchange.www.service.UserService;
import org.apache.commons.codec.EncoderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

/**
 * Created by shadow on 2015/11/27.
 * AccountController类：账户管理
 */
@Controller
public class AccountController {

    @Autowired
    private UserService userService;

    @Autowired
    private QiniuService qiniuService;

    @RequestMapping(value = "/account")
    public String toPersonalCenter(Model model , HttpSession session) {
        User user = userService.getCurrentUser(session);
        if (null != user){
            try {
                qiniuService.setAccessKey("Zm0x_pMEfrKAWYlzSAnMXvdEXuOP3kaCFhBebuf4");
                qiniuService.setSecretKey("Ypu9e__2WJxsL-MoUTGqUR4EyexVMdXd_DT-4Olx");
                qiniuService.setDomain("7xo7z2.com1.z0.glb.clouddn.com");
                qiniuService.setBucketName("schoolexchange");
                String userFaceUrl = qiniuService.getDownloadFileUrl(user.getUser_faces());
                user.setUser_faces(userFaceUrl);
            } catch (EncoderException e) {
                e.printStackTrace();
            } catch (AuthException e) {
                e.printStackTrace();
            }

        }
        model.addAttribute("user" , user);
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
