<%--
  Created by IntelliJ IDEA.
  User: shadow
  Date: 2015/12/3
  Time: 12:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>校内交易</title>
    <link rel="stylesheet" href="../../styles/bootstrap.css">
    <link rel="stylesheet" href="../../styles/main.css">
</head>
<body onload="getSideNumber()">
<%@include file="../top.jsp" %>
<div class="container" style="margin-top: 20px">
    <span id="sp" style="display: none">8</span>
    <%@include file="../sideBar.jsp" %>

    <%--账户==密码设置--%>
    <div class="row">
        <div class="col-md-2 personal_setting list-group">
            <div class="list-group-item list-group-item-success">
                <h2>账户</h2>
            </div>
            <div class="list-group-item list-group-item-success">
                <a href="/account"><span class="glyphicon glyphicon-user"></span>个人中心</a>
            </div>
            <div class="list-group-item list-group-item-success">
                <a href="/account/password_set"><span class="glyphicon glyphicon-link"></span>密码设置</a>
            </div>
            <div class="list-group-item list-group-item-success personal_setting_center">
                <a href="/account/to_certification"><span class="glyphicon glyphicon-phone"></span>实名认证</a>
            </div>
        </div>
        <div class="col-md-10 password_setting">
            <div class="row">
                <div class="col-md-2"></div>
                <div class="col-md-8 reset_pwd_success" id="resetPwdSuccessWell">
                    <span class="glyphicon glyphicon-remove" onclick="closeAlert();"></span>

                    <div class="alert alert-success" role="alert">恭喜,修改密码成功!!</div>
                </div>
                <div class="col-md-2"></div>
            </div>
            <div class="row">
                <div class="col-md-2"></div>
                <div class="col-md-8" id="resetPwdFail">
                    <span class="glyphicon glyphicon-remove" onclick="closeAlert();"></span>

                    <div class="alert alert-danger"><span>原始密码不正确!</span></div>
                </div>
                <div class="col-md-2"></div>
            </div>
            <div class="col-md-4"></div>
            <div class="col-md-4">
                认证
            </div>
            <div class="col-md-4"></div>
        </div>
    </div>
</div>
<script src="../../js/jquery-1.11.3.min.js"></script>
<script src="../../js/bootstrap.js"></script>
<script src="../../js/function.js"></script>
</body>
</html>
