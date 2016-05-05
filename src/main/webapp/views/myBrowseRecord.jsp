<%--
  Created by IntelliJ IDEA.
  User: shadow
  Date: 2016/5/5
  Time: 22:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>我的浏览记录</title>
    <link rel="stylesheet" href="../styles/bootstrap.css">
    <link rel="stylesheet" href="../styles/main.css">
</head>
<body>
<%@include file="top.jsp" %>
<div class="container">
    <span id="sp" style="display: none">7</span>
    <%@include file="sideBar.jsp" %>
    <div class="row">
        <div class="col-md-2 release_goods list-group">
            <div class="list-group-item list-group-item-success">
                <h2> 最近浏览记录</h2>
            </div>
        </div>
        <div class="col-md-2"></div>
        <div class="col-md-8">
            <table class="table table-hover" style="margin-top: 20px">
                <tr>
                    <td style="text-align: center"><h4 style="font-weight: bold">浏览商品名</h4></td>
                    <td style="text-align: center"><h4 style="font-weight: bold">时间</h4></td>
                </tr>
                <tr>
                    <td style="text-align: center">
                        <span>java编程思想</span>
                    </td>

                    <td style="text-align: center">
                        <button class="btn btn-danger">
                            <span>2016-05-06</span>
                        </button>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>
<script src="../js/jquery-1.11.3.min.js"></script>
<script src="../js/bootstrap.js"></script>
<script src="../js/function.js"></script>
<script>
    //获取未读消息个数
    window.onload = function () {
        getSideNumber();
        var messageCount = document.getElementById("message_count");
        if (messageCount != undefined) {
            $.post(
                    "/queryMessageCount",
                    function (data) {
                        if ("no" == data) {
                            messageCount.innerText = '0';

                        } else {
                            messageCount.innerText = data;
                            if (0 == data) {
                                $("#no_readMessage").show();
                            } else {
                                $("#readMessage").show();
                            }

                        }

                    }
            );
        }
    }
</script>
</body>
</html>
