<%--
  Created by IntelliJ IDEA.
  User: shadow
  Date: 2016/5/3
  Time: 18:51
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>我的消息</title>
    <link rel="stylesheet" href="../styles/bootstrap.css">
    <link rel="stylesheet" href="../styles/main.css">
</head>
<body>
<%@include file="top.jsp" %>
<div class="container">
    <span id="sp" style="display: none">6</span>
    <%@include file="sideBar.jsp" %>
    <div class="row">
        <div class="col-md-2 release_goods list-group">
            <div class="list-group-item list-group-item-success">
                <h2> 我的消息</h2>
            </div>
        </div>
        <div class="col-md-2"></div>
        <div class="col-md-8">

            <c:if test="${status == 1}">
                <div style="margin-top: 40px">
                    <span style="text-align: center;font-weight: bold;font-size: 20px">
                    还没有消息
                    </span>
                </div>
            </c:if>
            <c:if test="${status == 0}">
                <div style="float: right;margin-top: 30px">
                    <button class="btn btn-success btn-lg" style="background: #2BB8AA; display: none" id="readMessage" onclick="marksAllRead()">
                        <span id="btn_span">全部标记为已读</span>
                    </button>
                    <button class="btn btn-success btn-lg" style="background: #2BB8AA;display: none" id="no_readMessage"
                            disabled>无未读消息
                    </button>
                </div>
                <div style="clear: both"></div>
                <table class="table table-hover" style="margin-top: 20px">
                    <tr>
                        <td style="text-align: center"><h4 style="font-weight: bold">内容</h4></td>
                        <td style="text-align: center"><h4 style="font-weight: bold">发送人</h4></td>
                        <td style="text-align: center"><h4 style="font-weight: bold">时间</h4></td>
                    </tr>
                    <c:forEach items="${list}" var="message">
                        <tr>
                            <td style="text-align: center">
                                <span>${message.content}</span>
                            </td>
                            <td style="text-align: center">
                                <button class="btn btn-danger"><span>${message.name}</span></button>
                            </td>
                            <td style="text-align: center">
                                <button class="btn btn-danger"><span>
                                    <fmt:formatDate value="${message.publishDate}" pattern="yyyy-MM-dd HH:mm"/>
                                </span></button>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>

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
        //获取当前用户头像
        $.post(
                "/getMyHeadPic",
                function (data) {
                    if ("no" != data) {
                        document.getElementById("myHeadPic").src = "http://7xo7z2.com1.z0.glb.clouddn.com/" + data;
                    }
                }
        );
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
