<%--
  Created by IntelliJ IDEA.
  User: dong
  Date: 16-4-6
  Time: 下午10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>留言</title>
    <link rel="stylesheet" href="../styles/bootstrap.css">
    <link rel="stylesheet" href="../styles/main.css">
</head>
<body>
<%@include file="top.jsp" %>
<div class="container">
    <div class="row" style="margin-top: 50px">
        <div class="col-md-2"></div>
        <div class="col-md-8">
            <p class="bg-danger ms_result" id="ms_result">向shadow留言成功!!</p>
        </div>
        <div class="col-md-2"></div>
    </div>
    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-8">
            <div class="form-group">
                <label for="receiver" class="fontStyle">接受者:</label>
                <input type="text" class="form-control" id="receiver" value="${publish}" readonly>
            </div>
            <div class="form-group">
                <label for="content" class="fontStyle"><span id="span_content">内容</span></label>
                <textarea id="content" class="form-control" placeholder="输入的内容不能超过200字" rows="5"></textarea>
            </div>
            <div class="form-group">
                <button class="btn btn-success sendBtn" id="sendBtn" onclick="sendMessage()">
                    <span class="fontStyle" id="span_btn">发送</span>
                </button>
            </div>
        </div>
        <div class="col-md-2"></div>
    </div>
</div>
<script src="../js/jquery-1.11.3.min.js"></script>
<script src="../js/function.js"></script>
</body>
</html>
