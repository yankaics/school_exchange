package com.schoolexchange.www.action;

import com.qiniu.api.auth.AuthException;
import com.schoolexchange.www.entity.User;
import com.schoolexchange.www.service.QiniuService;
import com.schoolexchange.www.service.UserService;
import com.schoolexchange.www.tools.GeetestConfig;
import com.schoolexchange.www.tools.GeetestLib;
import org.apache.commons.codec.EncoderException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by shadow
 * on 2015/11/27.
 */
@Controller
public class AccountController {

    @Autowired
    private UserService userService;

    @Autowired
    private QiniuService qiniuService;

    /**
     * 跳转到个人信息中心
     *
     * @param model   存储用户信息
     * @param session 通过session获取当前用户User
     * @return 返回个人信息的页面路径
     */
    @RequestMapping(value = "/account")
    public String toPersonalCenter(Model model, HttpSession session) {
        User user = userService.getCurrentUser(session);
        if (null != user) {
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
        model.addAttribute("user", user);
        return "accountSetting/personal_center";
    }

    /**
     * 跳转到修改密码的页面
     */
    @RequestMapping(value = "/account/password_set")
    public String toResetPwd() {

        return "accountSetting/reset_password";
    }

    /**
     * 修改密码
     *
     * @param currentPwd 当前密码
     * @param newPwd     新密码
     */
    @RequestMapping(value = "/pwd_setting")
    public void resetPwd(String currentPwd, String newPwd, HttpServletResponse response, HttpSession session)
            throws IOException {
        String userName = (String) session.getAttribute("sx_user_name");
        boolean flag = userService.checkLogin(userName, currentPwd);
        if (flag) {
            userService.changePwd(userName, newPwd);
            response.getWriter().write("success");
        } else {
            response.getWriter().write("fail");
        }

    }

    /**
     * 更新用户信息到数据库
     *
     * @param userName         用户名
     * @param userSex          性别
     * @param userBirth        生日
     * @param userUniversity   所属大学
     * @param userProfessional 专业
     * @param userMotto        座右铭
     * @param user_faces       用户头像名(注:user_faces为input的name名字)
     */
    @RequestMapping(value = "/update_user_info")
    public void UpdateUserInfo(@RequestParam("userName") String userName, @RequestParam("userSex") String userSex,
                               @RequestParam("userBirth") String userBirth, @RequestParam("userUniversity") String userUniversity,
                               @RequestParam("userProfessional") String userProfessional, @RequestParam("userMotto") String userMotto,
                               @RequestParam MultipartFile[] user_faces, HttpServletRequest request, HttpServletResponse response,
                               HttpSession session) throws IOException, AuthException, JSONException {

       /* userName = userService.solveGetMessyCode(userName);
        userUniversity = userService.solveGetMessyCode(userUniversity);
        userProfessional = userService.solveGetMessyCode(userProfessional);
        userMotto = userService.solveGetMessyCode(userMotto);*/
        User user = userService.getCurrentUser(session);
        session.setAttribute("sx_user_name", userName);
        session.setAttribute("sx_university", userUniversity);
        user.setUser_name(userName);
        user.setUser_sex(Integer.parseInt(userSex));
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date birth = null;
        try {
            birth = sf.parse(userBirth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setUser_birth(birth);
        user.setUser_university(userUniversity);
        user.setUser_professional(userProfessional);
        user.setUser_motto(userMotto);
        PrintWriter out = response.getWriter();
        for (MultipartFile my_face : user_faces) {
            if (my_face.isEmpty()) {
                userService.updateUserInfo(user);
                out.write("3");
                return;
            } else {
                if (!qiniuService.judgePicExtension(my_face)) {
                    out.write("1");
                    return;
                }
                if (qiniuService.judgePicSize(my_face)) {
                    out.write("2");
                    return;
                }
                String tmpPath = request.getServletContext().getRealPath("views") + "\\";
                //System.out.println("根路径" + request.getServletContext().getRealPath("tmp") );
                String resetName = Long.toString(System.currentTimeMillis());
                String newPicName = resetName + my_face.getOriginalFilename().substring(my_face.getOriginalFilename().lastIndexOf('.'));
                String path = tmpPath + newPicName;
                my_face.transferTo(new File(path));
                File file = new File(path);
                qiniuService.setAccessKey("Zm0x_pMEfrKAWYlzSAnMXvdEXuOP3kaCFhBebuf4");
                qiniuService.setSecretKey("Ypu9e__2WJxsL-MoUTGqUR4EyexVMdXd_DT-4Olx");
                qiniuService.setDomain("7xo7z2.com1.z0.glb.clouddn.com");
                qiniuService.setBucketName("schoolexchange");
                qiniuService.uploadFile(file);
                if (file.exists()) {
                    file.delete();
                }
                //更新数据库
                user.setUser_faces(newPicName);
                userService.updateUserInfo(user);
                out.write("3");
                return;
            }
        }
    }

    /**
     * 跳转到认证页面
     *
     * @param status     滑块状态 success或fail
     * @param authStatus 验证码状态及手机是否已被被注册
     * @return 认证页面
     */

    @RequestMapping(value = "/account/to_certification")
    public String toCertification(HttpSession session, Model model, String status,
                                  @RequestParam(value = "flag", required = false) String authStatus) {

        User user = userService.getCurrentUser(session);
        boolean flag = userService.authenticationStatus(user);
        if (flag) {
            model.addAttribute("se_db_auth_status", "1");
            model.addAttribute("user", user);
        } else {
            //还没认证成功
            model.addAttribute("se_db_auth_status", "0");
            //判断是不是直接跳转
            if (null == status || null == authStatus) {
                return "accountSetting/certification";
            } else {
                //判断滑块是否验证正确
                if (userService.judge_password(status, "fail"))
                    model.addAttribute("status", "fail");
                //手机验证码及手机号是否被注册状态
                if (userService.judge_password(authStatus, "false$true")) {
                    model.addAttribute("authStatus", "error");
                    //验证失败显示失败手机号
                    if (null != session.getAttribute("se_auth_tel")) {
                        model.addAttribute("se_auth_tel", session.getAttribute("se_auth_tel").toString());
                    }
                    model.addAttribute("beUsed", "true");
                }
                if (userService.judge_password(authStatus, "false$false")) {
                    model.addAttribute("authStatus", "error");
                    //验证失败显示失败手机号
                    if (null != session.getAttribute("se_auth_tel")) {
                        model.addAttribute("se_auth_tel", session.getAttribute("se_auth_tel").toString());
                    }
                }
                if (userService.judge_password(authStatus, "true$true")) {
                    //验证失败显示失败手机号
                    if (null != session.getAttribute("se_auth_tel")) {
                        model.addAttribute("se_auth_tel", session.getAttribute("se_auth_tel").toString());
                    }
                    model.addAttribute("beUsed", "true");
                }
                //判断手机号是否已被注册
               /* if (null != aa){
                    if (aa.equals("true")) {
                        model.addAttribute("beUsed", "true");
                    }
                }*/
            }

        }
        return "accountSetting/certification";
    }

    /**
     * ajax创建验证码
     */
    @RequestMapping("/StartCaptchaServlet")
    public void createCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {

        GeetestLib gtSdk = new GeetestLib(GeetestConfig.getCaptcha_id(), GeetestConfig.getPrivate_key());

        String resStr = "{}";

        //自定义userid
        String userid = "test";

        //进行验证预处理
        int gtServerStatus = gtSdk.preProcess(userid);

        //将服务器状态设置到session中
        request.getSession().setAttribute(gtSdk.gtServerStatusSessionKey, gtServerStatus);
        //将userid设置到session中
        request.getSession().setAttribute("userid", userid);

        resStr = gtSdk.getResponseStr();

        PrintWriter out = response.getWriter();
        out.println(resStr);

    }

    /**
     * 表单提交验证验证码、滑块、手机号是否匹配
     *
     * @param request      request
     * @param authTel      验证的手机
     * @param inputCaptcha 输入的验证码
     * @param session      session
     * @return
     */
    @RequestMapping(value = "/auth_user")
    public String AuthUser(HttpServletRequest request, @RequestParam("se_auth_tel") String authTel,
                           @RequestParam("se_msg_captcha") String inputCaptcha, HttpSession session) throws IOException {
        //判断手机验证码匹配是否成功
        boolean flag = false;
        if (inputCaptcha.equals(session.getAttribute("se_msg_captcha")) && authTel.equals(session.getAttribute("se_auth_tel"))) {
            flag = true;
        }
        //验证滑块
        String veResult = "fail";
        GeetestLib gtSdk = new GeetestLib(GeetestConfig.getCaptcha_id(), GeetestConfig.getPrivate_key());

        String challenge = request.getParameter(GeetestLib.fn_geetest_challenge);
        String validate = request.getParameter(GeetestLib.fn_geetest_validate);
        String seccode = request.getParameter(GeetestLib.fn_geetest_seccode);

        //从session中获取gt-server状态
        int gt_server_status_code = (Integer) request.getSession().getAttribute(gtSdk.gtServerStatusSessionKey);

        //从session中获取userid
        String userid = (String) request.getSession().getAttribute("userid");

        int gtResult = 0;

        if (gt_server_status_code == 1) {
            //gt-server正常，向gt-server进行二次验证

            gtResult = gtSdk.enhencedValidateRequest(challenge, validate, seccode, userid);
            System.out.println(gtResult);
        } else {
            // gt-server非正常情况下，进行failback模式验证

            System.out.println("failback:use your own server captcha validate");
            gtResult = gtSdk.failbackValidateRequest(challenge, validate, seccode);
            System.out.println(gtResult);
        }


        if (gtResult == 1) {
            // 验证成功
            veResult = "success";
        }

        boolean beUsed = userService.authTel(authTel);
        if (veResult.equals("success") && flag && !beUsed) {
            //保存手机到数据库
            User user = userService.getCurrentUser(session);
            userService.authUser(user, authTel);
        }
        String flag_url = userService.encrypt_password(Boolean.toString(flag) + "$" + Boolean.toString(beUsed));
        return "redirect:account/to_certification?status=" + userService.encrypt_password(veResult) + "&flag="
                + flag_url;
    }

    /**
     * 验证手机号是否已被认证
     *
     * @param auth_tel 验证手机号
     */
    @RequestMapping(value = "/ajax_auth_tel")
    public void ajaxAuthTel(String auth_tel, HttpServletResponse response) throws IOException {

        if (!userService.authTel(auth_tel)) {
            response.getWriter().write("yes");
        } else {
            response.getWriter().write("no");
        }
    }

    /**
     * 获取免费的验证码，存储在session中并发送到手机
     *
     * @param session  通过session设置验证码、时间和获取上一次发送验证码时间
     * @param response ajax响应
     * @param auth_tel 要发送給验证码的手机
     */
    @RequestMapping(value = "/get_free_captcha")
    public void getFreeCaptcha(HttpSession session, HttpServletResponse response, String auth_tel) {
        Date newDate = new Date();
        if (null != session.getAttribute("se_free_captcha_date")) {
            Date oldDate = (Date) session.getAttribute("se_free_captcha_date");
            if (!userService.dateDifference(oldDate, newDate)) {
                try {
                    response.getWriter().write("no");
                    return;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                session.setAttribute("se_free_captcha_date", newDate);
                String randomPwd = userService.getRandomPassword();
                session.setAttribute("se_msg_captcha", randomPwd);
                session.setAttribute("se_auth_tel", auth_tel);
                //5分钟过期
                session.setMaxInactiveInterval(60 * 5);
                //发送随机6为数到手机
                System.out.println("发送验证码: " + randomPwd + "到手机: " + auth_tel);
               /* try {
                    userService.sendSms(auth_tel , randomPwd);
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
            }
        } else {
            session.setAttribute("se_free_captcha_date", newDate);
            String randomPwd = userService.getRandomPassword();
            session.setAttribute("se_msg_captcha", randomPwd);
            session.setAttribute("se_auth_tel", auth_tel);
            //5分钟过期
            session.setMaxInactiveInterval(60 * 5);
            //发送随机6为数到手机
            System.out.println("发送验证码: " + randomPwd + "到手机: " + auth_tel);
           /* try {
                userService.sendSms(auth_tel , randomPwd);
            } catch (IOException e) {
                e.printStackTrace();
            }*/
        }
    }

    /**
     * ajax验证验证码
     *
     * @param inputCaptcha 输入的验证码
     * @param authTel      验证的手机号
     * @param session      session
     * @param response     response
     * @throws IOException
     */
   /* @RequestMapping(value = "/auth_input_captcha")
    public void authInputTel(String inputCaptcha, String authTel, HttpSession session, HttpServletResponse response) throws IOException {
        System.out.println("session手机号=== " + session.getAttribute("se_auth_tel"));
        System.out.println("session的验证码=== " + session.getAttribute("se_msg_captcha"));
        if (null == session.getAttribute("se_msg_captcha") || null == session.getAttribute("se_auth_tel")) {
            response.getWriter().write("error");
            return;
        }
        if (inputCaptcha.equals(session.getAttribute("se_msg_captcha")) && authTel.equals(session.getAttribute("se_auth_tel"))) {
            response.getWriter().write("success");
        } else {
            response.getWriter().write("error");
        }
    }*/


}
