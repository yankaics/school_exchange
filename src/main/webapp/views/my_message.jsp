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
<body onload="getSideNumber()">
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
            <div style="float: right;margin-top: 30px">
                <button class="btn btn-success btn-lg" style="background: #2BB8AA">全部标记为已读</button>
            </div>
            <div style="clear: both"></div>
            <c:if test="${status == 1}">
                还没有消息
            </c:if>
            <c:if test="${status == 0}">
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
                                    <fmt:formatDate value="${message.publishDate}" pattern="yyyy-MM-dd HH:mm" />
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
</body>
</html>
