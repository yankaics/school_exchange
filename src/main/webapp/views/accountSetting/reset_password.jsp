<%--
  Created by IntelliJ IDEA.
  User: shadow
  Date: 2015/11/27
  Time: 22:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>校内交易</title>
    <link rel="stylesheet" href="../../styles/bootstrap.css">
    <link rel="stylesheet" href="../../styles/main.css">
</head>
<body onload="getSideNumber()">
<%@include file="../top.jsp" %>
<div class="container" style="margin-top: 20px">
    <span id="sp" style="display: none">8</span>
    <%@include file="../sideBar.jsp" %>

    <%--账户==密码设置--%>
    <div class="row">
        <div class="col-md-2 personal_setting list-group">
            <div class="list-group-item list-group-item-success">
                <h2>账户</h2>
            </div>
            <div class="list-group-item list-group-item-success">
                <a href="/account"><span class="glyphicon glyphicon-user"></span>个人中心</a>
            </div>
            <div class="list-group-item list-group-item-success personal_setting_center">
                <a href="/account/password_set"><span class="glyphicon glyphicon-link"></span>密码设置</a>
            </div>
            <div class="list-group-item list-group-item-success">
                <a href="/account/to_certification"><span class="glyphicon glyphicon-phone"></span>实名认证</a>
            </div>
        </div>
        <div class="col-md-10 password_setting">
            <div class="row">
                <div class="col-md-2"></div>
                <div class="col-md-8 reset_pwd_success" id="resetPwdSuccessWell">
                    <span class="glyphicon glyphicon-remove" onclick="closeAlert();"></span>

                    <div class="alert alert-success" role="alert">恭喜,修改密码成功!!</div>
                </div>
                <div class="col-md-2"></div>
            </div>
            <div class="row">
                <div class="col-md-2"></div>
                <div class="col-md-8" id="resetPwdFail">
                    <span class="glyphicon glyphicon-remove" onclick="closeAlert();"></span>
                    <div class="alert alert-danger">原始密码不正确!</div>
                </div>
                <div class="col-md-2"></div>
            </div>
            <div class="col-md-4"></div>
            <div class="col-md-4">
                <form method="post" onsubmit="return false">
                    <div class="form-group" style="margin-top: 30px">
                        <label for="oldPwd"><span id="sp_current_pwd">当前密码</span></label>
                        <input type="password" class="form-control" name="oldPwd" id="oldPwd" placeholder="请输入原始密码"
                               onblur="checkCurrentPwd()">
                    </div>
                    <div class="form-group">
                        <label for="newPwd"><span id="sp_new_pwd">新密码</span></label>
                        <input type="password" class="form-control" name="newPwd" id="newPwd" placeholder="请输入新密码"
                               onblur="checkNewPwd()">
                    </div>
                    <div class="form-group">
                        <label for="confirmPwd"><span id="sp_confirm_pwd">确认密码</span></label>
                        <input type="password" class="form-control" name="confirmPwd" id="confirmPwd"
                               placeholder="请再次输入新密码" onblur="checkConfirmPwd()">
                    </div>
                    <div class="form-group reset_button">
                        <button type="submit" class="btn btn-primary" onclick="pwdSetting()">确认
                        </button>
                    </div>
                </form>
            </div>
            <div class="col-md-4"></div>
        </div>
    </div>
</div>
<script src="../../js/jquery-1.11.3.min.js"></script>
<script src="../../js/bootstrap.js"></script>
<script src="../../js/function.js"></script>
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
