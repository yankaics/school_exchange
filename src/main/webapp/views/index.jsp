<%--
  User: shadow
  Date: 2015/11/10
  Time: 13:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>校内交易</title>
</head>
<body>
<div>
    <h2>七牛云存储测试</h2>

    <div>
        <form method="post" action="http://upload.qiniu.com/"
              enctype="multipart/form-data">
            <input name="key" type="hidden" value="<resource_key>">
            <input name="x:<custom_name>" type="hidden" value="<custom_value>">
            <input name="token" type="hidden" value="<upload_token>">
            <input name="file" type="file"/>
            <input name="crc32" type="hidden"/>
            <input name="accept" type="hidden"/>
            <input type="submit" value="上传">
        </form>
    </div>
</div>
<script>

</script>
</body>
</html>
