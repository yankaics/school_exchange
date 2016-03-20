<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/12/19
  Time: 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title></title>
</head>
<body>
    <h1>请求的页面找不到。。。。。。。。。。。</h1>
    <h2>
        <c:if test="${parseIntFail != null}">
            商品信息错误！！
        </c:if>
        <c:if test="${notFoundGoods != null}">
            商品不存在!!!
        </c:if>
    </h2>
</body>
</html>
