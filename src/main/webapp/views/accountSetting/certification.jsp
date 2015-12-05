<%--
  Created by IntelliJ IDEA.
  User: shadow
  Date: 2015/12/3
  Time: 12:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
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
            <div class="list-group-item list-group-item-success">
                <a href="/account/password_set"><span class="glyphicon glyphicon-link"></span>密码设置</a>
            </div>
            <div class="list-group-item list-group-item-success personal_setting_center">
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

                    <div class="alert alert-danger"><span>原始密码不正确!</span></div>
                </div>
                <div class="col-md-2"></div>
            </div>

            <c:if test="${se_db_auth_status == 1}">
                <div class="col-md-3"></div>
                <div class="col-md-6">
                    <div>
                        <h3 style="color: green">已通过个人认证</h3>
                        <ul class="list-group">
                            <li class="list-group-item list-group-item-success">
                                <h4>用户名:&nbsp;&nbsp;${user.user_name}</h4>
                            </li>
                            <li class="list-group-item list-group-item-info">
                                <h4>邮箱:&nbsp;&nbsp;${user.user_email}</h4>
                            </li>
                            <li class="list-group-item list-group-item-danger">
                                <h4>手机号:&nbsp;&nbsp;${user.user_tel}</h4>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="col-md-3"></div>
            </c:if>
            <c:if test="${se_db_auth_status == 0}">
                <div class="col-md-4"></div>
                <div class="col-md-4">
                    <c:if test="${status == 'fail'}">
                        <div id="captcha_fail">
                           <span class="glyphicon glyphicon-remove" style="float: right;cursor: pointer"
                                 onclick="closeAlert();"></span>

                            <div class="alert alert-success" role="alert">滑块验证失败</div>
                        </div>
                    </c:if>
                    <div id="often_check" style="display: none">
                           <span class="glyphicon glyphicon-remove" style="float: right;cursor: pointer"
                                 onclick="closeAlert();"></span>

                        <div class="alert alert-success" role="alert">请等一分钟后再次发送验证码</div>
                    </div>
                    <c:if test="${authStatus == 'error'}">
                        <div id="cpatcha_error">
                         <span class="glyphicon glyphicon-remove" style="float: right;cursor: pointer"
                               onclick="closeAlert();"></span>

                            <div class="alert alert-success" role="alert">验证码错误!!!</div>
                        </div>
                    </c:if>
                    <c:if test="${beUsed == 'true'}">
                        <div id="tel_beused">
                         <span class="glyphicon glyphicon-remove" style="float: right;cursor: pointer"
                               onclick="closeAlert();"></span>

                            <div class="alert alert-success" role="alert">你是不是啥,已经提醒你手机号已被注册,你还注册!</div>
                        </div>
                    </c:if>
                    <form method="post" action="/auth_user" onsubmit="return auth_captcha()">
                        <div class="form-group" style="margin-top: 50px">
                            <label for="se_user_tel"><span id="sp_auth_tel">手机号</span></label>

                            <div class="input-group">
                                <div class="input-group-addon">+86</div>
                                <input type="text" class="form-control" id="se_user_tel" placeholder="请输入手机号"
                                       name="se_auth_tel" onblur="checkAuthTel('1')" value="${se_auth_tel}">
                            </div>
                        </div>
                        <div class="form-group" style="margin-top: 20px">
                            <button class="btn btn-primary" id="get_captcha"><span
                                    id="sp_get_captcha">免费获取验证码</span></button>
                            <a type="button" class="btn btn-default" data-toggle="tooltip" data-placement="top"
                               style="float: right"
                               data-content="1.通信商导致<br/>
                                              2.网络环境问题导致<br/>
                                              3.现在获取短信的人太多了，请稍等片刻"
                               id="no_captcha" data-original-title="出现延迟的原因可能有:">没有收到验证码</a>
                        </div>
                        <div class="form-group" style="margin-top: 20px">
                            <label for="tel_captcha"><span id="sp_msg_captcha">验证码</span></label>

                            <div class="input-group">
                                <input type="text" class="form-control" id="tel_captcha" placeholder="请输入验证码"
                                       name="se_msg_captcha" onblur="msgCaptcha()">

                                <div class="input-group-addon">验证码是6位数字</div>
                            </div>
                        </div>
                        <div>
                            <div id="div_geetest_lib"></div>
                            <div id="div_id_embed"></div>
                        </div>
                        <div class="form-group" style="margin-top: 20px">
                            <input type="submit" value="确认认证" class="form-control btn-primary">
                        </div>
                    </form>
                </div>
                <div class="col-md-4"></div>
            </c:if>


        </div>
    </div>
</div>

<script src="../../js/jquery-1.11.3.min.js"></script>
<script src="../../js/bootstrap.js"></script>
<script src="../../js/function.js"></script>
<script>

    /**
     * 弹出框文字支持html
     * */
    $("#no_captcha").popover({
        html: true
    });

    /**
     *  验证码
     **/
    var gtFailbackFrontInitial = function (result) {
        var s = document.createElement('script');
        s.id = 'gt_lib';
        s.src = 'http://static.geetest.com/static/js/geetest.0.0.0.js';
        s.charset = 'UTF-8';
        s.type = 'text/javascript';
        document.getElementsByTagName('head')[0].appendChild(s);
        var
                loaded = false;
        s.onload = s.onreadystatechange = function () {
            if (!loaded
                    && (!this.readyState
                    || this.readyState === 'loaded' || this.readyState === 'complete')) {
                loadGeetest(result);
                loaded = true;
            }
        };
    }

    /**
     * 载入验证码
     * */
    var loadGeetest = function (config) {

        //1. 使用geetst验证码
        window.gt_captcha_obj = new window.Geetest({
            gt: config.gt,
            challenge: config.challenge,
            product: 'embed',
            offline: !config.success
        });

        gt_captcha_obj.appendTo("#div_id_embed");

    }

    s = document.createElement('script');
    s.src = 'http://api.geetest.com/get.php?callback=gtcallback';
    $("#div_geetest_lib").append(s);

    var gtcallback = (function () {
        var status = 0, result, apiFail;
        return function (r) {
            status += 1;
            if (r) {
                result = r;
                setTimeout(function () {
                    if (!window.Geetest) {
                        apiFail = true;
                        gtFailbackFrontInitial(result)
                    }
                }, 1000)
            }
            else if (apiFail) {
                return
            }
            if (status == 2) {
                loadGeetest(result);
            }
        }
    })()

    /**
     *   ajax请求生成验证码，并写入session
     **/
    $.ajax({
        url: "/StartCaptchaServlet",
        type: "get",
        dataType: 'JSON',
        success: function (result) {
            gtcallback(result)
        }
    })

    /*
     *重新发送验证码(60秒)
     */
    var wait = 60;

    document.getElementById("get_captcha").onclick = function () {
        if (checkAuthTel('2')) {
            //ajax执行免费获取手机验证码
            $.ajax({
                url: "/get_free_captcha",
                type: "get",
                data: {auth_tel: document.getElementById("se_user_tel").value},
                dataType: "text",
                success: function (data) {
                    if (data == "no") {
                        $("#often_check").show();
                    }
                }
            });
        } else {
            return false;
        }
        time(this);
    }
</script>
</body>
</html>
