<%--
  User: shadow
  Date: 2015/11/27
  Time: 13:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<div class="container">
    <div class="row">
        <div class="col-md-5"></div>
        <div class="col-md-3">
            <div id="update_success"
                 onmouseout="hide_update_info_close()" onmouseover="show_update_info_close()">
                <div id="personal_close_alert">
                    <span class="glyphicon glyphicon-remove" onclick="close_update_success_alert();"></span>
                </div>
                <div id="alert_success">
                    <span class="glyphicon glyphicon-thumbs-up"></span>&nbsp;&nbsp;&nbsp;个人信息更新成功&nbsp;！
                </div>
            </div>
        </div>
        <div class="col-md-4"></div>
    </div>
</div>
<%@include file="../top.jsp" %>
<div class="container" style="margin-top: 20px">
    <span id="sp" style="display: none">8</span>
    <%@include file="../sideBar.jsp" %>
    <!--账户-个人中心-->
    <div class="row">
        <div class="col-md-2 personal_setting list-group">
            <div class="list-group-item list-group-item-success">
                <h2>账户</h2>
            </div>
            <div class="list-group-item list-group-item-success personal_setting_center">
                <a href="/account"><span class="glyphicon glyphicon-user"></span>个人中心</a>
            </div>
            <div class="list-group-item list-group-item-success">
                <a href="/account/password_set"><span class="glyphicon glyphicon-link"></span>密码设置</a>
            </div>
            <div class="list-group-item list-group-item-success">
                <a href=""><span class="glyphicon glyphicon-phone"></span>实名认证</a>
            </div>
        </div>
        <form method="post" enctype="multipart/form-data" onsubmit="return false">
            <input type="text" id="hideUserName" value="${user.user_name}" style="display: none;">

            <div class="col-md-3 user_faces">
                <div id="resetPwdFail">
                    <div class="alert alert-danger resetPwdFailDiv"><span id="span_user_face_error"></span></div>
                </div>
                <div id="preview">
                    <img id="imghead" src="../../images/csdn.jpg" class="img-circle">
                </div>
                <div>
                    <img src="${user.user_faces}" id="testPic" class="img-circle"/>
                </div>
                <a href="javascript:" class="file">更换头像
                    <input type="file" name="user_faces" id="user_faces" onchange="previewImage(this)">
                </a>
            </div>
            <div class="col-md-7">
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="user_email">邮箱</label>
                        <input type="text" class="form-control" id="user_email"
                               disabled="disabled"
                               name="user_email" value="${user.user_email}">
                    </div>
                    <div class="form-group user_sex">
                        <label>性别</label><br/>
                        <c:if test="${user.user_sex == 1}">
                            <h4><input type="radio" name="sex" checked="checked" value="1" class="btn btn-primary">
                                未知
                            </h4>
                            <h4><input type="radio" name="sex" value="2" class="btn btn-primary">
                                男
                            </h4>
                            <h4>
                                <input type="radio" name="sex" value="3" class="btn btn-primary">
                                女
                            </h4>
                        </c:if>
                        <c:if test="${user.user_sex == 2}">
                            <h4><input type="radio" name="sex" value="1" class="btn btn-primary">
                                未知
                            </h4>
                            <h4><input type="radio" name="sex" checked="checked" value="2" class="btn btn-primary">
                                男
                            </h4>
                            <h4>
                                <input type="radio" name="sex" value="3" class="btn btn-primary">
                                女
                            </h4>
                        </c:if>
                        <c:if test="${user.user_sex == 3}">
                            <h4><input type="radio" name="sex" value="1" class="btn btn-primary">
                                未知
                            </h4>
                            <h4><input type="radio" name="sex" value="2" class="btn btn-primary">
                                男
                            </h4>
                            <h4>
                                <input type="radio" name="sex" checked="checked" value="3" class="btn btn-primary">
                                女
                            </h4>
                        </c:if>

                    </div>
                    <div class="form-group university">
                        <label for="university">所属学校 </label>
                        <c:choose>
                            <c:when test="${user.user_university == '烟台大学文经学院'}">
                                <select id="university" class="form-control">
                                    <option value="烟台大学文经学院" selected="selected">烟台大学文经学院</option>
                                    <option value="烟台大学">烟台大学</option>
                                    <option value="山东工商学院">山东工商学院</option>
                                    <option value="滨州医学院烟台校区">滨州医学院烟台校区</option>
                                    <option value="鲁东大学">鲁东大学</option>
                                </select>
                            </c:when>
                            <c:when test="${user.user_university == '烟台大学'}">
                                <select id="university" class="form-control">
                                    <option value="烟台大学文经学院">烟台大学文经学院</option>
                                    <option value="烟台大学" selected="selected">烟台大学</option>
                                    <option value="山东工商学院">山东工商学院</option>
                                    <option value="滨州医学院烟台校区">滨州医学院烟台校区</option>
                                    <option value="鲁东大学">鲁东大学</option>
                                </select>
                            </c:when>
                            <c:when test="${user.user_university == '山东工商学院'}">
                                <select id="university" class="form-control">
                                    <option value="烟台大学文经学院">烟台大学文经学院</option>
                                    <option value="烟台大学" selected="selected">烟台大学</option>
                                    <option value="山东工商学院" selected="selected">山东工商学院</option>
                                    <option value="滨州医学院烟台校区">滨州医学院烟台校区</option>
                                    <option value="鲁东大学">鲁东大学</option>
                                </select>
                            </c:when>
                            <c:when test="${user.user_university == '滨州医学院烟台校区'}">
                                <select id="university" class="form-control">
                                    <option value="烟台大学文经学院">烟台大学文经学院</option>
                                    <option value="烟台大学" selected="selected">烟台大学</option>
                                    <option value="山东工商学院">山东工商学院</option>
                                    <option value="滨州医学院烟台校区" selected="selected">滨州医学院烟台校区</option>
                                    <option value="鲁东大学">鲁东大学</option>
                                </select>
                            </c:when>
                            <c:when test="${user.user_university == '鲁东大学'}">
                                <select id="university" class="form-control">
                                    <option value="烟台大学文经学院">烟台大学文经学院</option>
                                    <option value="烟台大学" selected="selected">烟台大学</option>
                                    <option value="山东工商学院">山东工商学院</option>
                                    <option value="滨州医学院烟台校区">滨州医学院烟台校区</option>
                                    <option value="鲁东大学" selected="selected">鲁东大学</option>
                                </select>
                            </c:when>
                        </c:choose>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="user_name"><span id="span_personal_user_name">用户名</span></label>
                        <input type="text" class="form-control" id="user_name"
                               style="width: 100%;min-height: 40px;font-size: 16px"
                               name="user_name" value="${user.user_name}" onblur="checkPersonalUser();">
                    </div>
                    <div class="form-group user_birth">
                        <label for="date_b">生日</label>
                        <c:choose>
                            <c:when test="${null == user.user_birth}">
                                <input id="date_b" class="form-control" type="text" name="user_birth" value="1950-1-1"
                                       readonly style="background: #fff">
                            </c:when>
                            <c:otherwise>
                                <input id="date_b" class="form-control" type="text" name="user_birth"
                                       value='<fmt:formatDate value="${user.user_birth}" pattern="yyyy-MM-dd" />'
                                       readonly style="background: #fff">
                            </c:otherwise>
                        </c:choose>

                    </div>
                    <div class="form-group user_professional">
                        <label for="user_professional"><span id="span_user_professional">专业</span></label>
                        <input type="text" class="form-control" name="user_professional" id="user_professional"
                               placeholder="请输入您的专业"
                               onblur="inputUserProfessional()" value="${user.user_professional}">
                    </div>
                </div>
                <div class="col-md-12">
                    <div class="form-group user_motto">
                        <label for="user_motto"> <span id="span_user_motto">座右铭(50字以内)</span></label>
                        <input type="text" name="user_motto" id="user_motto" placeholder="个性签名" class="form-control"
                               onblur="inputUserMotto()" value="${user.user_motto}">
                    </div>
                </div>
                <div class="col-md-12">
                    <button type="submit" class="btn btn-primary" onclick="updateUserInfo()">更新</button>
                </div>
            </div>
        </form>

    </div>
</div>
<script src="../../js/jquery-1.11.3.min.js"></script>
<script src="../../js/bootstrap.js"></script>
<script src="../../js/ajaxfileupload.js"></script>
<script src="../../js/function.js"></script>
<script src="../../js/jquery.cxcalendar.min.js"></script>

<script>

    // 在 value 中有默认值
    $('#date_b').cxCalendar();

</script>
</body>
</html>
