<%--
  Created by IntelliJ IDEA.
  User: shadow
  Date: 2016/5/8
  Time: 20:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>我发布商品</title>
    <link rel="stylesheet" href="../styles/bootstrap.css">
    <link rel="stylesheet" href="../styles/main.css">
</head>
<body>
<%@include file="top.jsp" %>
<div class="container">
    <span id="sp" style="display: none">4</span>
    <%@include file="sideBar.jsp" %>
    <div class="row">
        <div class="col-md-2 release_goods list-group">
            <div class="list-group-item list-group-item-success">
                <h2> 我发布的商品</h2>
            </div>
        </div>
        <div class="col-md-2"></div>
        <div class="col-md-8">
            <c:if test="${list.size() == 0}">
                <div style="margin-top: 40px">
                     <span style="text-align: center;font-weight: bold;font-size: 20px">
                             您还没有发布任何商品
                     </span>
                </div>
            </c:if>
            <c:if test="${list.size() != 0}">
                <table class="table table-hover" style="margin-top: 20px">
                    <tr>
                        <td style="text-align: center"><h4 style="font-weight: bold">浏览商品名</h4></td>
                        <td style="text-align: center"><h4 style="font-weight: bold">时间</h4></td>
                        <td style="text-align: center"><h4 style="font-weight: bold">操作</h4></td>
                    </tr>
                    <c:forEach items="${list}" var="sg">
                        <tr>
                            <td style="text-align: center">
                                <a href="/sell_goods?detail=${sg.id}" target="_blank"
                                   style="text-decoration: none;color: #0f0f0f">
                                        ${sg.goods_name}
                                </a>
                            </td>

                            <td style="text-align: center">
                                <button class="btn btn-danger">
                                    <span>
                                        <fmt:formatDate value="${sg.create_time}" pattern="yyyy-MM-dd HH:mm"/>
                                    </span>
                                </button>
                            </td>

                            <td style="text-align: center">
                                <button class="btn btn-danger" onclick="deleteGoods(${sg.id})">
                                    <span>删除</span>
                                </button>
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
