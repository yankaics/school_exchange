<%--
  Created by IntelliJ IDEA.
  User: shadow
  Date: 2016/5/2
  Time: 18:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>我的收藏</title>
    <link rel="stylesheet" href="../styles/bootstrap.css">
    <link rel="stylesheet" href="../styles/main.css">
</head>
<body onload="getSideNumber()">
<%@include file="top.jsp" %>
<div class="container">
    <span id="sp" style="display: none">3</span>
    <%@include file="sideBar.jsp" %>
    <div class="row">
        <div class="col-md-2 release_goods list-group">
            <div class="list-group-item list-group-item-success">
                <h2> 我的收藏</h2>
            </div>
        </div>
        <div class="col-md-2"></div>
        <div class="col-md-8">
            <c:if test="${1 == isCollection}">
                <div style="margin-top: 50px;">
                    <h3 style="color: red">您没有收藏的商品</h3>
                </div>
            </c:if>
            <c:if test="${0 == isCollection}">
                <table class="table table-hover" style="margin-top: 30px">

                    <tr>
                        <td style="text-align: center"><h4 style="font-weight: bold">商品名</h4></td>
                        <td style="text-align: center"><h4 style="font-weight: bold">操作</h4></td>
                    </tr>
                    <c:forEach items="${myCollections}" var="collection">
                        <tr>
                            <td style="text-align: center">
                                <a href="/sell_goods?detail=${collection.good_id}" target="_blank"
                                   style="text-decoration: none;color: #0f0f0f">
                                        ${collection.goods_name}
                                </a>
                            </td>
                            <td style="text-align: center">
                                <button class="btn btn-danger" id="${collection.good_id}"
                                        onclick="cancelMyCollection(${collection.good_id})"><span
                                        id="${collection.good_id}">取消收藏</span></button>
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
