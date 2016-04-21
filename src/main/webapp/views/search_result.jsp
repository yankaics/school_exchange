<%--
  Created by IntelliJ IDEA.
  User: shadow
  Date: 2016/4/21
  Time: 8:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>搜索结果</title>
    <link rel="stylesheet" href="../styles/bootstrap.css">
    <link rel="stylesheet" href="../styles/main.css">
</head>
<body>
<%@include file="top.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="alert alert-success" role="alert" style="text-align: center;font-weight: bold;font-size: 16px;margin-top: 20px">检索结果</div>
        </div>
    </div>

    <div class="row">
            <div class="col-md-2">
                <a href="#" target="_blank" style="text-decoration: none;">
                    <img src="../images/test01.jpg" class="img-thumbnail image_style">
                    <em style="color: red">
                        <b>¥</b>100
                    </em><br/>
                    <h5 style="color: black">java核心技术</h5>
                </a>
            </div>
    </div>
</div>
<script src="../js/jquery-1.11.3.min.js"></script>
<script src="../js/bootstrap.js"></script>
<script src="../js/function.js"></script>
<script>
    $(document).ready(function () {
        //首先将#back-to-top隐藏
        $("#back-to-top").hide();
        //当滚动条的位置处于距顶部100像素以下时，跳转链接出现，否则消失
        $(function () {
            $(window).scroll(function () {
                if ($(window).scrollTop() > 100) {
                    $("#back-to-top").fadeIn(500);
                }
                else {
                    $("#back-to-top").fadeOut(500);
                }
            });
            //当点击跳转链接后，回到页面顶部位置
            $("#back-to-top").click(function () {
                $('body,html').animate({scrollTop: 0}, 100);
                return false;
            });
        });
    });
    //获取未读消息个数
    window.onload = function () {
        var messageCount = document.getElementById("message_count");
        if (messageCount != undefined) {
            $.post(
                    "/queryMessageCount",
                    function (data) {
                        if ("no" == data) {
                            messageCount.innerText = '0';
                        } else {
                            messageCount.innerText = data;
                        }

                    }
            );
        }
    }
</script>
</body>
</html>
