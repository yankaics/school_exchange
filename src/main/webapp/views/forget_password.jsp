<%--
  Created by IntelliJ IDEA.
  User: shadow
  Date: 2015/11/20
  Time: 11:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE>
<html>
<head>
    <title>校内交易找回密码</title>
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
                <a href="index"><img src="../images/logo.png"></a>
            </div>
        </div>
        <div class="col-md-4">
            <span class="get_password" id="get_password"></span>
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
                            <label for="forget_password"><span id="fp">邮箱</span></label>
                            <input class="form-control" id="forget_password" placeholder="请输入邮箱" onblur="checkInputEmail()">
                        </div>
                        <div class="row">
                            <div class="col-md-2"></div>
                            <div class="col-md-8 login_submit">
                                <button type="submit" class="btn btn-primary login_button" onclick="return forgetPassword()">找回密码
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
