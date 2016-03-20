<%--
  Created by IntelliJ IDEA.
  User: dong
  Date: 16-3-20
  Time: 下午12:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>商品详情</title>
    <link rel="stylesheet" href="../styles/bootstrap.css">
    <link rel="stylesheet" href="../styles/main.css">
</head>
<body>
        <ul>
            <li>${user.user_name}</li>
            <li>${sell_goods.goods_name}</li>
            <li>${sell_goods.contact}</li>
            <li><img src="${sell_goods.goods_images}" class="img-thumbnail image_style"></li>
        </ul>
</body>
</html>
