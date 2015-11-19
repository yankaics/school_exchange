<%--
  Created by IntelliJ IDEA.
  User: shadow
  Date: 2015/11/18
  Time: 14:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>校内交易注册</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <link rel="stylesheet" href="../styles/main.css">
    <link rel="stylesheet" href="../styles/bootstrap.css">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-4">
            <!--logo-->
            <div class="login_logo">
                <a href="index"><img src="../images/logo.png"></a>
            </div>
        </div>
        <div class="col-md-4">
            <div class="register_success" id="register_success">
                恭喜，注册成功!! <span id="jumpTo">5</span>秒后自动跳转到校内交易首页
            </div>
        </div>
        <div class="col-md-4"></div>
    </div>
    <div class="login_center">
        <div class="row">
            <div class="col-md-2"></div>
            <div class="col-md-4 register_center_left">
                <!--左侧图标-->
                <div>
                    <img src="../images/login_left.png">
                </div>
            </div>
            <div class="col-md-4 register_center_right">
                <!--右侧注册-->
                <div class="login_form">
                    <form method="post"  onsubmit="false">
                        <div class="form-group">
                            <label for="register_email"><span id="span_email">邮箱</span></label>
                            <input class="form-control" id="register_email" placeholder="请输入Email"
                                   onblur="checkEmail()">
                        </div>
                        <div class="form-group">
                            <label for="register_name"><span id="span_name">用户名</span></label>
                            <input type="text" class="form-control" id="register_name"
                                   placeholder="用户名为汉字、数字、字母、下划线或它们的组合"
                                   onblur="checkRegisterName()">
                        </div>
                        <div class="form-group">
                            <label for="register_password"><span id="span_password">密码</span></label>
                            <input type="password" class="form-control" id="register_password" placeholder="请输入密码"
                                   onblur="checkPassword()">
                        </div>
                        <div class="form-group">
                            <label for="re_register_password"><span id="re_span_password">确认密码</span></label>
                            <input type="password" class="form-control" id="re_register_password" placeholder="再次输入密码"
                                   onblur="checkPasswordsAgreement()">
                        </div>
                        <div>
                            <label for="university">所属学校 </label>
                            <select id="university" class="form-control">
                                <option value="烟台大学文经学院" selected="selected">烟台大学文经学院</option>
                                <option value="烟台大学">烟台大学</option>
                                <option value="山东工商学院">山东工商学院</option>
                                <option value="滨州医学院烟台校区">滨州医学院烟台校区</option>
                                <option value="鲁东大学">鲁东大学</option>
                            </select>
                        </div>
                        <div class="row">
                            <div class="col-md-2"></div>
                            <div class="col-md-8 login_submit">
                                <button type="submit" class="btn btn-primary login_button" onclick="return checkRegister()">注册
                                </button>
                            </div>
                            <div class="col-md-2"></div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="col-md-2"></div>
        </div>
    </div>
</div>
<script src="../js/function.js"></script>
<script src="../js/jquery-1.11.3.min.js"></script>
</body>
</html>
