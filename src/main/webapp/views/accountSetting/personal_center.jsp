<%--
  User: shadow
  Date: 2015/11/27
  Time: 13:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<!DOCTYPE>
<html>
<head>
    <title>校内交易</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../../styles/bootstrap.css">
    <link rel="stylesheet" href="../../styles/main.css">
</head>
<body>
<%@include file="../top.jsp" %>
<div class="container" style="margin-top: 20px">

    <%@include file="../sideBar.jsp" %>
    <!--账户-个人中心-->
    <div class="row">
        <div class="col-md-2 personal_setting list-group">
            <div class="list-group-item list-group-item-success">
                <h2>账户</h2>
            </div>
            <div class="list-group-item list-group-item-success personal_setting_center">
                <a href=""><span class="glyphicon glyphicon-user"></span>个人中心</a>
            </div>
            <div class="list-group-item list-group-item-success">
                <a href=""><span class="glyphicon glyphicon-link"></span>密码设置</a>
            </div>
            <div class="list-group-item list-group-item-success">
                <a href=""><span class="glyphicon glyphicon-phone"></span>实名认证</a>
            </div>
        </div>
        <form method="post" enctype="multipart/form-data" action="">
            <div class="col-md-3 user_faces">
                <div id="preview">
                    <img id="imghead" src="../../images/csdn.jpg" class="img-circle">
                </div>
                <div>
                    <img src="../../images/csdn.jpg" id="testPic" class="img-circle"/>
                </div>
                <a href="javascript:" class="file">更换头像
                    <input type="file" name="user_faces" id="" onchange="previewImage(this)">
                </a>
            </div>
            <div class="col-md-7">
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="user_email">邮箱</label>
                        <input type="text" class="form-control" id="user_email"
                               disabled="disabled"
                               name="user_email" value="zhangjiadong0418@qq.com">
                    </div>
                    <div class="form-group user_sex">
                        <label>性别</label><br/>
                        <h4><input type="radio" checked="checked" name="sex" value="1" class="btn btn-primary">
                            未知
                        </h4>
                        <h4><input type="radio" name="sex" value="2" class="btn btn-primary">
                            男
                        </h4>
                        <h4>
                            <input type="radio" name="sex" value="3" class="btn btn-primary">
                            女
                        </h4>
                    </div>
                    <div class="form-group university">
                        <label for="university">所属学校 </label>
                        <select id="university" class="form-control">
                            <option value="烟台大学文经学院" selected="selected">烟台大学文经学院</option>
                            <option value="烟台大学">烟台大学</option>
                            <option value="山东工商学院">山东工商学院</option>
                            <option value="滨州医学院烟台校区">滨州医学院烟台校区</option>
                            <option value="鲁东大学">鲁东大学</option>
                        </select>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="user_name">用户名</label>
                        <input type="text" class="form-control" id="user_name"
                               style="width: 100%;min-height: 40px;font-size: 16px"
                               name="user_name" value="shadow">
                    </div>
                    <div class="form-group user_birth">
                        <label for="date_b">生日</label>
                        <input id="date_b" class="form-control" type="text" name="user_birth" value="1993-04-18"
                               readonly style="background: #fff">
                    </div>
                    <div class="form-group user_professional">
                        <label for="user_professional">专业</label>
                        <input type="text" class="form-control" name="user_professional" id="user_professional"
                               value="计算机科学与技术">
                    </div>
                </div>
                <div class="col-md-12">
                    <div class="form-group user_motto">
                        <label for="user_motto">座右铭 <span>(50字以内)</span></label>
                        <input type="text" name="user_motto" id="user_motto" placeholder="个性签名" class="form-control">
                    </div>
                </div>
                <div class="col-md-12">
                    <button type="submit" class="btn btn-primary">更新</button>
                </div>
            </div>
        </form>

    </div>
</div>
<script src="../../js/jquery-1.11.3.min.js"></script>
<script src="../../js/bootstrap.js"></script>
<script src="../../js/function.js"></script>
<script src="../../js/jquery.cxcalendar.min.js"></script>
<script>

    // 在 value 中有默认值
    $('#date_b').cxCalendar();

</script>
</body>
</html>
