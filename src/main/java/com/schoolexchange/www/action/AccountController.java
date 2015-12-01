package com.schoolexchange.www.action;

import com.qiniu.api.auth.AuthException;
import com.schoolexchange.www.entity.User;
import com.schoolexchange.www.service.QiniuService;
import com.schoolexchange.www.service.UserService;
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

    @RequestMapping(value = "/account/password_set")
    public String toResetPwd() {

        return "accountSetting/reset_password";
    }

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

    //user_faces为input的name名字
    @RequestMapping(value = "/update_user_info")
    public void UpdateUserInfo(@RequestParam("userName") String userName, @RequestParam("userSex") String userSex,
                               @RequestParam("userBirth") String userBirth, @RequestParam("userUniversity") String userUniversity,
                               @RequestParam("userProfessional") String userProfessional, @RequestParam("userMotto") String userMotto,
                               @RequestParam MultipartFile[] user_faces, HttpServletRequest request, HttpServletResponse response,
                               HttpSession session) throws IOException, AuthException, JSONException {

        userName = userService.solveGetMessyCode(userName);
        userUniversity = userService.solveGetMessyCode(userUniversity);
        userProfessional = userService.solveGetMessyCode(userProfessional);
        userMotto = userService.solveGetMessyCode(userMotto);
        User user = userService.getCurrentUser(session);
        session.setAttribute("sx_user_name" , userName);
        session.setAttribute("sx_university" , userUniversity);
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
                if (!qiniuService.judgePicExtension(my_face)){
                    out.write("1");
                    return;
                }
                if (qiniuService.judgePicSize(my_face)){
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
                if (file.exists()){
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
}
