<%--
  Created by IntelliJ IDEA.
  User: shadow
  Date: 2015/11/18
  Time: 14:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>校内交易登录</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <link rel="stylesheet" href="../styles/main.css">
    <link rel="stylesheet" href="../styles/bootstrap.css">
</head>
<body class="login_body">
<div class="container">
    <div class="row">
        <div class="col-md-4">
            <!--logo-->
            <div class="login_logo">
                <a href="/"><img src="../images/logo.png"></a>
            </div>
        </div>
        <div class="col-md-4">
            <div class="login_fail" id="login_fail">
                用户登录失败,请检查用户名,密码是否正确
            </div>
        </div>
        <div class="col-md-4"></div>
    </div>
    <div class="login_center">
        <div class="row">
            <div class="col-md-2"></div>
            <div class="col-md-4 login_center_left">
                <!--左侧图标-->
                <div>
                    <img src="../images/login_left.png">
                </div>
            </div>
            <div class="col-md-4 login_center_right">
                <!--右侧登录-->
                <div class="login_form">
                    <form method="post"  onsubmit="return false">
                        <div class="form-group">
                            <label for="user_name"><span id="login_name">用户名</span></label>
                            <input class="form-control" id="user_name" placeholder="用户名/email/电话"
                                   onblur="checkLoginName()">
                        </div>
                        <div class="form-group">
                            <label for="user_password"><span id="login_password">密码</span></label>
                            <input type="password" class="form-control" id="user_password" placeholder="密码"
                                   onblur="checkLoginPassword()">
                        </div>
                        <div class="row">
                            <div class="col-md-4">
                                <!--忘记密码-->
                                <a href="to_get_password" class="btn btn-default">忘记密码</a>
                            </div>
                            <div class="col-md-4"></div>
                            <div class="col-md-4">
                                <!--注册新用户-->
                                <a href="to_register" class="btn btn-danger">注册新用户</a>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-2"></div>
                            <div class="col-md-8 login_submit">
                                <button type="submit" class="btn btn-primary login_button" onclick="checkLogin();">登录
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
