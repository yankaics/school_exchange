<%--
  User: shadow
  Date: 2015/11/10
  Time: 13:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE>
<html>
<head>
    <title>校内交易</title>
</head>
<body>
<div>
   <h1>首页</h1>
    <%
        out.println(session.getAttribute("user_name"));
        out.println(session.getAttribute("university"));
    %>
</div>
<script>

</script>
</body>
</html>
