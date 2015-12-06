<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/12/6
  Time: 15:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <link rel="stylesheet" href="../styles/main.css">
    <title>校内交易</title>
    <link rel="stylesheet" href="../styles/bootstrap.css">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-4">
            <!--logo-->
            <div class="login_logo">
                <a href="/"><img src="../images/logo.png"></a>
            </div>
        </div>
        <div class="col-md-4">
            <span class="get_password" id="get_password"></span>
        </div>
        <div class="col-md-4"></div>
    </div>
    <div class="login_center">
        <div class="row">
            <div class="col-md-4"></div>
            <div class="col-md-4">
                <form method="post">
                    <span id="hide_email" style="display: none">${resetInfo}</span>
                    <div style="text-align: center;font-size: 20px;font-weight: bold">重置密码</div>
                    <div class="form-group">
                        <label for="to_new_pwd"><span id="to_span_new_pwd">新密码</span></label>
                        <input type="password" class="form-control" name="to_new_pwd" id="to_new_pwd" onblur="toResetPwd()">
                    </div>
                    <div class="form-group">
                        <label for="to_again_new_pwd"><span id="to_span_again_new_pwd">确认密码</span></label>
                        <input type="password" class="form-control" name="to_again_new_pwd" id="to_again_new_pwd" onblur="toAgainResetPwd()">
                    </div>
                    <div class="form-group" style="margin-top: 20px">
                        <button type="button" class="btn btn-primary form-control" onclick="executeResetPwd()">确认重置</button>
                        <!-- <input type="reset" class="btn bg-warning" value="">-->
                    </div>
                </form>
            </div>
            <div class="col-md-4"></div>
        </div>
    </div>
</div>
<script src="../js/jquery-1.11.3.min.js"></script>
<script src="../js/function.js"></script>
</body>
</html>
