/**
 * Created by shadow on 2015/11/16.
 */
/*
 * 判断登录
 */
function checkLogin() {
    if (checkLoginName() && checkLoginPassword()) {
        var user_name = document.getElementById("user_name").value;
        var user_password = document.getElementById("user_password").value;
        //ajax验证账号和密码
        $.post(
            "/login",
            {
                user_name: user_name,
                user_password: user_password
            },
            function (data) {
                var obj = eval(data);
                if ("yes" == obj.result) {
                    if (null == obj.url) {
                        location.href = "/";
                    } else {
                        location.href = obj.url;
                    }
                } else {
                    $('#login_fail').show().delay(2000).fadeOut();
                    return false;
                }
                /*if (data == "yes") {
                 location.href = "/";
                 } else {
                 $('#login_fail').show().delay(2000).fadeOut();
                 return false;
                 }*/
            },
            "json"
        );

    }
}
/*
 *用户名判空
 */
function checkLoginName() {
    var user_name = document.getElementById("user_name").value;
    if (0 == user_name.length || "" == user_name) {
        document.getElementById("login_name").innerHTML = "<span style='color: red'>用户名不能为空</span>";
        return false;
    } else {
        document.getElementById("login_name").innerText = "用户名";
        return true;
    }
}
/*
 *登录密码判空
 */
function checkLoginPassword() {
    var user_password = document.getElementById("user_password").value;
    if (0 == user_password.length || "" == user_password) {
        document.getElementById("login_password").innerHTML = "<span style='color: red'>密码不能为空</span>";
        return false;
    } else {
        document.getElementById("login_password").innerText = "密码";
        return true;
    }
}

/*function checkLoginSubmit() {

 return checkLogin();
 }*/

/*
 *判断能否submit
 */
function checkRegister() {
    var register_email = document.getElementById("register_email").value;
    if (checkEmail() && checkRegisterName() && checkPassword() && checkPasswordsAgreement()) {
        saveUser();
        var remind = judgeEmail(register_email);
        remind = remind.replace('密码已被重置，', '激活连接已');
        document.getElementById("jumpTo").innerHTML = remind;
        $('#register_success').show();
        /* autoJump(5, '/');*/
    }
    return false;
}

/*
 * 判断邮箱格式是否合法以及是否被注册
 */
function checkEmail() {

    var js_value = document.getElementById("register_email");
    if (0 == js_value.value.length && "" == js_value.value) {
        document.getElementById("span_email").innerHTML = "<span style='color: red'>邮箱不能为空</span>";
        return false;
    }
    var pattern = /^([\.a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
    if (!pattern.test(js_value.value)) {
        document.getElementById("span_email").innerHTML = "<span style='color: red'>邮箱格式不正确</span>";
        return false;
    } else {
        document.getElementById("span_email").innerText = "邮箱";
    }
    /*Ajax异步验证邮箱是否被注册*/
    var email = js_value.value;

    $.post(
        "check_email",
        {
            email: email
        },
        function (data) {
            if (data == "yes") {
                document.getElementById("span_email").innerHTML = "<span style='color: green'>该邮箱可以注册</span>";
                return true;
            } else {
                document.getElementById("span_email").innerHTML = "<span style='color: red'>该邮箱已被注册</span>";
                return false;
            }
        }
    );

    return true;

}
/*
 *验证用户名
 */
function checkRegisterName() {
    var js_value = document.getElementById("register_name").value;

    if (0 == js_value.length && "" == js_value) {
        document.getElementById("span_name").innerHTML = "<span style='color: red'>用户名不能为空</span>";
        return false;
    } else {

        if (js_value.length < 1 || js_value.length > 8) {
            document.getElementById("span_name").innerHTML = "<span style='color: red'>用户名长度必须在1-8之间</span>";
            return false;
        }
    }
    var reg = /^(\w|[\u4E00-\u9FA5])*$/;
    if (!reg.test(js_value)) {
        document.getElementById("span_name").innerHTML = "<span style='color: red'>用户名格式不合法</span>";
        return false;
    }

    /*Ajax异步验证用户名是否存在*/
    $.post(
        "check_name",
        {
            user_name: js_value
        },
        function (data) {
            if (data == "yes") {
                document.getElementById("span_name").innerHTML = "<span style='color: green'>该用户名可以注册</span>";
                return true;
            } else {
                document.getElementById("span_name").innerHTML = "<span style='color: red'>该用户名已被注册</span>";
                return false;
            }
        }
    );


    return true;
}

function checkPassword() {
    var js_value = document.getElementById("register_password").value;

    if (0 == js_value.length && "" == js_value) {
        document.getElementById("span_password").innerHTML = "<span style='color: red'>密码不能为空</span>";
        return false;
    } else {

        if (js_value.length < 6 || js_value.length > 15) {
            document.getElementById("span_password").innerHTML = "<span style='color: red'>密码长度必须在6-15之间</span>";
            return false;
        }
    }
    document.getElementById("span_password").innerText = "密码";

    return true;
}


function checkPasswordsAgreement() {

    var pwd1 = document.getElementById("register_password").value;
    var pwd2 = document.getElementById("re_register_password").value;
    if (0 == pwd2.length && "" == pwd2) {
        document.getElementById("re_span_password").innerHTML = "<span style='color: red'>确认密码不能为空</span>";
        return false;
    }
    if (!(pwd1 == pwd2)) {
        document.getElementById("re_span_password").innerHTML = "<span style='color: red'>两次密码不一致</span>";
        return false;
    }
    document.getElementById("re_span_password").innerText = "确认密码";

    return true;
}

/*
 *自动跳转
 */
function autoJump(secs, surl) {
    var jumpTo = document.getElementById('jumpTo');
    jumpTo.innerHTML = secs;
    if (--secs > 0) {
        setTimeout("autoJump(" + secs + ",'" + surl + "')", 1000);
    }
    else {
        location.href = surl;
    }
}

/*注册到数据库Ajax*/
function saveUser() {
    var user_email = document.getElementById("register_email").value;
    var user_name = document.getElementById("register_name").value;
    var user_password = document.getElementById("register_password").value;
    var belong_university = document.getElementById("university").value;

    //ajax保存到数据库
    $.post(
        "register_action",
        {
            user_email: user_email,
            user_name: user_name,
            user_password: user_password,
            belong_university: belong_university
        }
    );
}


/*找回密码js*/
function forgetPassword() {

    if (checkInputEmail()) {

        var fp = document.getElementById("forget_password").value;
        document.getElementById("reminding").innerHTML = "<span style='color: red'>重置密码中......</span>";
        //重置密码
        $.post(
            "reset_password",
            {
                email: fp
            },
            function (data) {
                if (data == "yes") {
                    document.getElementById("fp").innerHTML = "<span style='color: red'>邮箱不存在</span>";
                    document.getElementById("reminding").innerHTML = "<span style='color: white'>找回密码</span>";
                    return false;
                } else {
                    document.getElementById("fp").innerText = "邮箱";
                    $('#get_password').show().delay(60000).fadeOut("slow");
                    document.getElementById("get_password").innerHTML = judgeEmail(fp).replace('密码已被重置，', '重置连接已');
                    document.getElementById("reminding").innerHTML = "<span style='color: white'>找回密码</span>";
                    return false;
                }
            }
        );
    }
}
/*
 *判断邮箱类型
 */
function judgeEmail(fp) {
    if (-1 != fp.lastIndexOf('@qq.com')) {
        return "密码已被重置，发送至您的邮箱: <a href='https://mail.qq.com/' style='color: white' target='_blank'>" + fp + "</a>";
    }
    if (-1 != fp.lastIndexOf('@163.com')) {
        return "密码已被重置，发送至您的邮箱: <a href='http://email.163.com/' style='color: white' target='_blank'>" + fp + "</a>";
    }
    if (-1 != fp.lastIndexOf('@126.com')) {
        return "密码已被重置，发送至您的邮箱: <a href='http://mail.126.com/' style='color: white' target='_blank'>" + fp + "</a>";
    }
    if (-1 != fp.lastIndexOf('@gmail.com')) {
        return "密码已被重置，发送至您的邮箱: <a href='https://mail.google.com/' style='color: white'>" + fp + "</a>";
    }
    return "密码已被重置，发送至您的邮箱: " + fp;
}

function checkInputEmail() {
    var fp = document.getElementById("forget_password").value;
    if (0 == fp.length || "" == fp) {
        document.getElementById("fp").innerHTML = "<span style='color: red'>邮箱不能为空</span>";
        return false;
    } else {
        document.getElementById("fp").innerText = "邮箱";
    }
    /*邮件格式*/
    var pattern = /^([\.a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
    if (!pattern.test(fp)) {
        document.getElementById("fp").innerHTML = "<span style='color: red'>邮箱格式不正确</span>";
        return false;
    } else {
        document.getElementById("fp").innerText = "邮箱";
    }

    return true;
}

function selectUniversity() {
    var university = document.getElementById("select_university").value;
    if (university != "切换学校") {
        document.getElementById("span_university").innerText = university;
        /*Ajax异步把学校设置到session中,并刷新首页*/
        $.post(
            "/selectUniversity",
            {
                selectUniversity: university
            },
            function (data) {
                if ("yes" == data) {
                    location.href = "/";
                }
            }
        );

    }

}
/*获取焦点获取未读的消息*/
function get_unread_message() {
    $(".message_div").show().empty();
    $(".message_div").append('<h5>正在加载。。。。请稍等</h5>');
    $.post(
        "/getUnreadMessage",
        function (data) {
            if (data == "no") {
                alert("还没登录");
            } else {
                var obj = eval(data);
                $(".message_div").show().empty();
                if (0 == obj.length) {
                    $(".message_div").append('<div class="look_all_message"><a href="#" class="list-group-item" style="background: #2BB8AA" onclick="show_sideBar(6,\'#my_message\');" onmouseover="get_unread_message()">暂无消息</a></div>');
                } else {
                    $(".message_div").append('<div class="look_all_message"><a href="#" class="list-group-item" style="background: #2BB8AA" onclick="show_sideBar(6,\'#my_message\');" onmouseover="get_unread_message()">查看所有未读消息</a></div>');
                    if (1 == obj.length) {
                        $(".message_div").append('<a href="#"  class="list-group-item list-group-item-success" onmouseover="get_unread_message()" onclick="show_sideBar(6,\'#my_message\');"><p id="mess_one"></p><div id="name_one"></div></a>');
                    }
                    if (2 == obj.length) {
                        $(".message_div").append('<a href="#"  class="list-group-item list-group-item-success" onmouseover="get_unread_message()" onclick="show_sideBar(6,\'#my_message\');"><p id="mess_one"></p><div id="name_one"></div></a>');
                        $(".message_div").append('<a href="#" class="list-group-item list-group-item-info" onmouseover="get_unread_message()" onclick="show_sideBar(6,\'#my_message\');"><p id="mess_two"></p><div id="name_two"></div> </a>');
                    }
                    if (3 == obj.length) {
                        $(".message_div").append('<a href="#"  class="list-group-item list-group-item-success" onmouseover="get_unread_message()" onclick="show_sideBar(6,\'#my_message\');"><p id="mess_one"></p><div id="name_one"></div></a>');
                        $(".message_div").append('<a href="#" class="list-group-item list-group-item-info" onmouseover="get_unread_message()" onclick="show_sideBar(6,\'#my_message\');"><p id="mess_two"></p><div id="name_two"></div> </a>');
                        $(".message_div").append('<a href="#" class="list-group-item list-group-item-warning" onmouseover="get_unread_message()" onclick="show_sideBar(6,\'#my_message\');"><p id="mess_three"></p><div id="name_three"></div> </a>');
                    }
                    if (4 == obj.length) {
                        $(".message_div").append('<a href="#"  class="list-group-item list-group-item-success" onmouseover="get_unread_message()" onclick="show_sideBar(6,\'#my_message\');"><p id="mess_one"></p><div id="name_one"></div></a>');
                        $(".message_div").append('<a href="#" class="list-group-item list-group-item-info" onmouseover="get_unread_message()" onclick="show_sideBar(6,\'#my_message\');"><p id="mess_two"></p><div id="name_two"></div> </a>');
                        $(".message_div").append('<a href="#" class="list-group-item list-group-item-warning" onmouseover="get_unread_message()" onclick="show_sideBar(6,\'#my_message\');"><p id="mess_three"></p><div id="name_three"></div> </a>');
                        $(".message_div").append('<a href="#" class="list-group-item list-group-item-danger" onmouseover="get_unread_message()" onclick="show_sideBar(6,\'#my_message\');"><p id="mess_four"></p><div id="name_four"></div> </a>');
                    }
                    for (var i = 0; i < obj.length; i++) {
                        /*alert(decodeURI(obj[i].name) + "==========" + decodeURI(obj[i].content));*/
                        if (0 == i) {
                            document.getElementById("mess_one").innerText = decodeURIComponent(obj[i].content);
                            document.getElementById("name_one").innerText = "from " + decodeURIComponent(obj[i].name) + "的评论";
                        }
                        if (1 == i) {
                            document.getElementById("mess_two").innerText = decodeURIComponent(obj[i].content);
                            document.getElementById("name_two").innerText = "from " + decodeURIComponent(obj[i].name) + "的评论";
                        }
                        if (2 == i) {
                            document.getElementById("mess_three").innerText = decodeURIComponent(obj[i].content);
                            document.getElementById("name_three").innerText = "from " + decodeURIComponent(obj[i].name) + "的评论";
                        }
                        if (3 == i) {
                            document.getElementById("mess_four").innerText = decodeURIComponent(obj[i].content);
                            document.getElementById("name_four").innerText = "from " + decodeURIComponent(obj[i].name) + "的评论";
                        }
                    }
                }
            }
        }
    );
}

/*失去焦点关闭未读消息*/
function close_unread_message() {
    $(".message_div").hide();
}

/*展开个人中心*/
function show_personal_center() {
    $(".personal_center").show();
}

function close_personal_center() {
    $(".personal_center").hide();
}

/*展开发布和求购商品*/
function show_release_buy_goods() {
    $("#release_and_buy").show();
}
function close_release_buy_goods() {
    $("#release_and_buy").hide();
}


/*侧边栏js*/
//设置选中的侧边栏选项
var chooseStatus = 0;
function getSideNumber() {
    chooseStatus = document.getElementById("sp").textContent;
    if (1 == chooseStatus) {
        $("#release_goods").css({
            background: "gray"
        });
    }
    //我的收藏颜色加深
    if (3 == chooseStatus) {
        $("#my_collection").css({
            background: "gray"
        });
    }
    //我发布的商品
    if (4 == chooseStatus) {
        $("#already_release").css({
            background: "gray"
        });
    }
    if (6 == chooseStatus) {

        $("#my_message").css({
            background: "gray"
        });
    }
    if (7 == chooseStatus) {

        $("#foot_print").css({
            background: "gray"
        });
    }
    if (8 == chooseStatus) {
        $("#account_set").css({
            background: "gray"
        });
    }
}


/*alert("状态:== " + chooseStatus);*/
function deepen_div(sideBarName) {
    $(sideBarName).css({
        background: "gray"
    });
}

function noDeepen_div(sideBarName) {
    //发布商品
    if (chooseStatus != 0 && 1 == chooseStatus && "#release_goods" == sideBarName) {
        $(sideBarName).css({
            background: "gray"
        });
        return true;
    }
    //发布商品
    if (chooseStatus != 0 && 2 == chooseStatus && "#buy_goods" == sideBarName) {
        $(sideBarName).css({
            background: "gray"
        });
        return true;
    }
    //我的收藏
    if (chooseStatus != 0 && 3 == chooseStatus && "#my_collection" == sideBarName) {
        $(sideBarName).css({
            background: "gray"
        });
        return true;
    }
    //已发布商品
    if (chooseStatus != 0 && 4 == chooseStatus && "#already_release" == sideBarName) {
        $(sideBarName).css({
            background: "gray"
        });
        return true;
    }
    //已求购商品
    if (chooseStatus != 0 && 5 == chooseStatus && "#already_buy" == sideBarName) {
        $(sideBarName).css({
            background: "gray"
        });
        return true;
    }
    //我的消息
    if (chooseStatus != 0 && 6 == chooseStatus && "#my_message" == sideBarName) {
        $(sideBarName).css({
            background: "gray"
        });
        return true;
    }
    //我的足迹
    if (chooseStatus != 0 && 7 == chooseStatus && "#foot_print" == sideBarName) {
        $(sideBarName).css({
            background: "gray"
        });
        return true;
    }
    //账号设置
    if (chooseStatus != 0 && 8 == chooseStatus && "#account_set" == sideBarName) {
        $(sideBarName).css({
            background: "gray"
        });
        return true;
    } else {
        $(sideBarName).css({
            background: "none"
        });
    }


}
//修改选中的状态
/*function setChooseStatus(status) {
 chooseStatus = status;
 }*/
//显示侧边栏
function show_sideBar(status, sideBarName) {
    /* $("#sidebar").show();
     setChooseStatus(status);
     deepen_div(sideBarName);*/
    location.href = "/account"
}
//侧边栏点击事件
function redirectAccount() {
    location.href = "/account";
}
function closeBar() {
    $("#sidebar").hide("slow");
}

//图片上传预览    IE是用了滤镜。
function previewImage(file) {
    var MAXWIDTH = 260;
    var MAXHEIGHT = 180;
    var div = document.getElementById('preview');
    if (file.files && file.files[0]) {
        div.innerHTML = '<img id=imghead>';
        var img = document.getElementById('imghead');

        img.onload = function () {
            var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
            img.width = rect.width;
            img.height = rect.height;
//                 img.style.marginLeft = rect.left+'px';
            img.style.marginTop = rect.top + 'px';
            /*$("#imghead").toggleclass("img-circle");*/
        }
        var reader = new FileReader();
        reader.onload = function (evt) {
            img.src = evt.target.result;
            document.getElementById("testPic").src = img.src;
        }
        reader.readAsDataURL(file.files[0]);
    }
    else //兼容IE
    {
        var sFilter = 'filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
        file.select();
        var src = document.selection.createRange().text;
        div.innerHTML = '<img id=imghead>';
        var img = document.getElementById('imghead');
        img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
        var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
        status = ('rect:' + rect.top + ',' + rect.left + ',' + rect.width + ',' + rect.height);
        div.innerHTML = "<div id=divhead style='width:" + rect.width + "px;height:" + rect.height + "px;margin-top:" + rect.top + "px;" + sFilter + src + "\"'></div>";
    }
    /* getSrc();*/
}
function clacImgZoomParam(maxWidth, maxHeight, width, height) {
    var param = {top: 0, left: 0, width: width, height: height};
    if (width > maxWidth || height > maxHeight) {
        rateWidth = width / maxWidth;
        rateHeight = height / maxHeight;

        if (rateWidth > rateHeight) {
            param.width = maxWidth;
            param.height = Math.round(height / rateWidth);
        } else {
            param.width = Math.round(width / rateHeight);
            param.height = maxHeight;
        }
    }

    param.left = Math.round((maxWidth - param.width) / 2);
    param.top = Math.round((maxHeight - param.height) / 2);
    return param;
}

/*密码设置*/

//判断当前密码是否为空
function checkCurrentPwd() {
    var currentPwd = document.getElementById("oldPwd").value;
    if (0 == currentPwd.length || "" == currentPwd) {
        document.getElementById("sp_current_pwd").innerHTML = "<span style='color: red'>当前密码不能空</span>";
        return false;
    } else {
        document.getElementById("sp_current_pwd").innerText = "当前密码";
        return true;
    }
}

//判断新密码是否为空
function checkNewPwd() {
    var newPwd = document.getElementById("newPwd").value;
    if (0 == newPwd.length || "" == newPwd) {
        document.getElementById("sp_new_pwd").innerHTML = "<span style='color: red'>新密码不能空</span>";
        return false;
    } else {
        document.getElementById("sp_new_pwd").innerText = "新密码";
    }
    if (newPwd.length < 6 || newPwd.length > 15) {
        document.getElementById("sp_new_pwd").innerHTML = "<span style='color: red'>密码长度必须在6-15之间</span>";
        return false;
    } else {
        document.getElementById("sp_new_pwd").innerText = "新密码";
        return true;
    }
}
//判断确认密码是否为空
function checkConfirmPwd() {
    var confirmPwd = document.getElementById("confirmPwd").value;
    if (0 == confirmPwd.length || "" == confirmPwd) {
        document.getElementById("sp_confirm_pwd").innerHTML = "<span style='color: red'>新密码不能空</span>";
        return false;
    } else {
        document.getElementById("sp_confirm_pwd").innerText = "确认密码";
    }

    return matchPassword();
}

//判断二次密码是否一致
function matchPassword() {
    var newPwd = document.getElementById("newPwd").value;
    var confirmPwd1 = document.getElementById("confirmPwd").value;
    if (newPwd != confirmPwd1) {
        document.getElementById("sp_confirm_pwd").innerHTML = "<span style='color: red'>两次密码不一致</span>";
        return false;
    } else {
        document.getElementById("sp_confirm_pwd").innerText = "确认密码";
        return true;
    }
}

//重置密码
function pwdSetting() {
    $("#resetPwdSuccessWell").hide();
    $("#resetPwdFail").hide();
    var currentPwd = document.getElementById("oldPwd").value;
    var newPwd = document.getElementById("newPwd").value;
    if (checkCurrentPwd() && checkNewPwd() && checkConfirmPwd() && matchPassword()) {
        //ajax修改密码
        $.post(
            "/pwd_setting",
            {
                currentPwd: currentPwd,
                newPwd: newPwd
            },
            function (data) {
                if (data == "success") {
                    $("#resetPwdSuccessWell").show();
                } else {
                    $("#resetPwdFail").show();
                }
            }
        );
    }
}
//关闭修改密码成功的提醒
function closeAlert() {
    $("#resetPwdSuccessWell").hide();
    $("#resetPwdFail").hide();
    //滑块失败
    $("#captcha_fail").hide();
    //频繁发送手机验证码
    $("#often_check").hide();
    //验证码失败
    $("#cpatcha_error").hide();
    //手机号已被注册提醒
    $("#tel_beused").hide();
}

/*
 *个人中心修改用户名
 */
function checkPersonalUser() {
    var spanUserName = document.getElementById("span_personal_user_name");
    var userName = document.getElementById("user_name").value;
    if (null == userName || 0 == userName.length) {
        spanUserName.innerHTML = "<span style='color: red'>用户名不能空</span>";
        return false;
    } else {
        if (userName.length < 1 || userName.length > 8) {
            document.getElementById("span_personal_user_name").innerHTML = "<span style='color: red'>用户名长度必须在1-8之间</span>";
            return false;
        }
    }

    var reg = /^(\w|[\u4E00-\u9FA5])*$/;
    if (!reg.test(userName)) {
        document.getElementById("span_personal_user_name").innerHTML = "<span style='color: red'>用户名格式不合法</span>";
        return false;
    }

    var hideUserName = document.getElementById("hideUserName").value;
    //判断是否发送验证
    if (hideUserName != userName) {
        /*Ajax异步验证用户名是否存在*/
        $.post(
            "check_name",
            {
                user_name: userName
            },
            function (data) {
                if (data == "yes") {
                    document.getElementById("span_personal_user_name").innerHTML = "<span style='color: green'>该用户名可以使用</span>";
                    return true;
                } else {
                    document.getElementById("span_personal_user_name").innerHTML = "<span style='color: red'>该用户名已被占用</span>";
                    return false;
                }
            }
        );
    }
    document.getElementById("span_personal_user_name").innerText = "用户名";
    return true;

}
/*专业验证*/
function inputUserProfessional() {
    var user_professional = document.getElementById("user_professional").value;
    if (user_professional.length > 20) {
        document.getElementById("span_user_professional").innerHTML = "<span style='color: red'>专业名称不能超过20个字</span>";
        return false;
    } else {
        document.getElementById("span_user_professional").innerText = "专业";
        return true;
    }

}

/*座右铭验证*/
function inputUserMotto() {
    var user_motto = document.getElementById("user_motto").value;
    if (user_motto.length > 50) {
        document.getElementById("span_user_motto").innerHTML = "<span style='color: red'>座右铭不能超过50个字</span>>";
        return false;
    } else {
        document.getElementById("span_user_motto").innerText = "座右铭(50字以内)";
        return true;
    }
}

/*更新用户信息*/
function updateUserInfo() {
    var flag1 = checkPersonalUser();
    var flag2 = inputUserProfessional();
    var flag3 = inputUserMotto();
    //获取用户名
    var userName = document.getElementById("user_name").value;
    //获取性别
    var userSex = getSexNumber();
    //获取生日
    var userBirth = document.getElementById("date_b").value;
    //获取所在大学
    var userUniversity = document.getElementById("university").value;
    //获取专业
    var userProfessional = document.getElementById("user_professional").value;
    //获取座右铭
    var userMotto = document.getElementById("user_motto").value;
    if (flag1 && flag2 && flag3) {
        /*$("#update_success").show();*/
        $("#resetPwdFail").hide();
        $.ajaxFileUpload({
            //处理文件上传操作的服务器端地址(可以传参数,已亲测可用)
            url: '/update_user_info?userName=' + userName + '&userSex=' + userSex + '&userBirth=' + userBirth + '&userUniversity=' + userUniversity + '&userProfessional=' + userProfessional + '&userMotto=' + userMotto,
            secureuri: false,                       //是否启用安全提交,默认为false
            fileElementId: 'user_faces',           //文件选择框的id属性 ,
            dataType: 'text',                       //服务器返回的格式,可以是json或xml等
            success: function (data) {        //服务器响应成功时的处理函数
                //1、图片格式=== <pre style="word-wrap: break-word; white-space: pre-wrap;">1</pre>
                // 2、图片大小
                // 3、上传成功
                if (data == '<pre style="word-wrap: break-word; white-space: pre-wrap;">1</pre>') {
                    document.getElementById("span_user_face_error").innerHTML = "<span style='color: red'>图片格式只能是.jpg或.png</span>";
                    $("#resetPwdFail").show();
                }
                if (data == '<pre style="word-wrap: break-word; white-space: pre-wrap;">2</pre>') {
                    document.getElementById("span_user_face_error").innerHTML = "<span style='color: red'>图片大小不能超过1M</span>";
                    $("#resetPwdFail").show();
                }
                if (data == '<pre style="word-wrap: break-word; white-space: pre-wrap;">3</pre>') {
                    $("#update_success").show();
                }
            }
        });
    }
}

//获取性别number
function getSexNumber() {
    var userSexs = document.getElementsByName("sex");
    var userSex = "";
    for (var i = 0; i < userSexs.length; i++) {
        if (userSexs[i].checked == true) {
            userSex = userSexs[i].value;
        }
    }
    return userSex;
}

/*鼠标移上展示窗口关闭框*/
function show_update_info_close() {
    $("#personal_close_alert").show();
}
//关闭成功消息框
function close_update_success_alert() {
    $("#update_success").hide();
}

function hide_update_info_close() {
    $("#personal_close_alert").hide();
}


function time(o) {
    var sp_get_captcha = document.getElementById("sp_get_captcha");
    if (wait == 0) {
        o.removeAttribute("disabled");
        sp_get_captcha.innerText = "免费获取验证码";
        wait = 60;
    } else {
        o.setAttribute("disabled", true);
        sp_get_captcha.innerText = "重新发送(" + wait + ")";
        wait--;
        setTimeout(function () {
                time(o)
            },
            1000)
    }
}

/**
 * 验证手机号
 * status为1执行ajax验证、status为2、不执行
 */
function checkAuthTel(status) {
    var auth_tel = document.getElementById("se_user_tel").value;
    var sp_auth_tel = document.getElementById("sp_auth_tel");
    if (0 == auth_tel.length || null == auth_tel) {
        sp_auth_tel.innerHTML = "<span style='color: red'>手机号不能为空</span>";
        return false;
    } else {
        var reg = /^0?1[3|4|5|8][0-9]\d{8}$/;
        if (!reg.test(auth_tel)) {
            sp_auth_tel.innerHTML = "<span style='color: red'>手机号格式不对</span>";
            return false;
        } else {
            sp_auth_tel.innerText = "手机号";
        }
    }

    /**
     *   ajax验证手机号是否能
     *   return  没有被注册返回true,否则返回false
     */
    if (status == 1) {
        $.ajax({
            url: "/ajax_auth_tel",
            type: "get",
            data: {auth_tel: auth_tel},
            dataType: "text",
            success: function (data) {
                if (data == "yes") {
                    sp_auth_tel.innerText = "手机号";
                    return true;
                } else {
                    sp_auth_tel.innerHTML = "<span style='color: red'>手机号已被认证</span>";
                    return false;
                }
            }
        });
    }
    return true;
}

/**
 * 验证判空
 */
function msgCaptcha() {
    var msg_captcha = document.getElementById("tel_captcha").value;
    var sp_msg_captcha = document.getElementById("sp_msg_captcha");
    if (msg_captcha == "" || msg_captcha.length == 0) {
        sp_msg_captcha.innerHTML = "<span style='color: red'>验证码不能为空</span>";
        return false;
    } else {
        sp_msg_captcha.innerText = "验证码";
        return true;
    }
}

/**
 * onsubmit提交验证
 *
 */
function auth_captcha() {
    $("#cpatcha_error").hide();
    var mc = msgCaptcha();
    var ca = checkAuthTel('2');
    var flag = false;
    if (mc && ca) {
        flag = true;
    }
    return flag;
}

/**
 * 检测新密码
 */
function toResetPwd() {
    var new_pwd = document.getElementById("to_new_pwd").value;
    if (null == new_pwd || "" == new_pwd) {
        document.getElementById("to_span_new_pwd").innerHTML = "<span style='color: red'>新密码不能为空</span>";
        return false;
    } else {
        if (new_pwd.length < 6 || new_pwd.length > 15) {
            document.getElementById("to_span_new_pwd").innerHTML = "<span style='color: red'>密码长度必须在6-15之间</span>";
            return false;
        } else {
            document.getElementById("to_span_new_pwd").innerText = "新密码";
        }
    }
    return true;
}

/**
 * 检测重复密码
 */
function toAgainResetPwd() {
    var to_again_new_pwd = document.getElementById("to_again_new_pwd").value;
    var new_pwd = document.getElementById("to_new_pwd").value;
    if (null == to_again_new_pwd || "" == to_again_new_pwd) {
        document.getElementById("to_span_again_new_pwd").innerHTML = "<span style='color: red'>重复密码不能为空</span>";
        return false;
    } else {
        if (new_pwd != to_again_new_pwd) {
            document.getElementById("to_span_again_new_pwd").innerHTML = "<span style='color: red'>两次密码不一致</span>";
            return false;
        }
        document.getElementById("to_span_again_new_pwd").innerText = "新密码";
    }

    return true;
}

/**
 * 重置密码
 */
function executeResetPwd() {
    var flag1 = toResetPwd();
    var flag2 = toAgainResetPwd();
    var resetEmail = document.getElementById("hide_email").innerHTML;
    var new_pwd = document.getElementById("to_new_pwd").value;
    if (flag1 && flag2) {
        //Ajax重置密码
        $.ajax({
            url: "/execute_reset_pwd",
            type: "post",
            data: {
                resetEmail: resetEmail,
                new_pwd: new_pwd
            },
            dataType: "text",
            success: function (data) {
                if (data == "yes") {
                    location.href = "/to_login?reset_pwd_status=1";
                }
            }
        });
    }
}

/*侧边栏点击事件*/
function redirectReleaseGoods() {
    location.href = "/to_release_goods";
}

/*
 * 商品收藏按钮切换
 */
function CollectionGoods(id) {
    var btnName = document.getElementById("btn_name").innerText;
    if (btnName == "收藏商品") {
        $.post(
            "/show_goods_details/collection",
            {
                goods_id: id,
                type: "1"
            },
            function (data) {
                if (data != "yes") {
                    document.getElementById("btn_name").innerHTML = "收藏商品";
                }
            }
        );
        document.getElementById("btn_name").innerHTML = "取消收藏";
    } else {
        $.post(
            "/show_goods_details/collection",
            {
                goods_id: id,
                type: "2"
            },
            function (data) {
                if (data != "yes") {
                    document.getElementById("btn_name").innerHTML = "取消收藏";
                }
            }
        );
        document.getElementById("btn_name").innerHTML = "收藏商品";
    }
}

/*
 *留言
 */
function toMessage(userName, goodsId) {
    console.log('用户名== ' + userName);
    location.href = "/show_goods_details/message?publish=" + userName + "&goodsId=" + goodsId;
}
/**
 * ajax异步发送留言
 */
function sendMessage() {
    var receiver = document.getElementById("receiver").value;
    var content = document.getElementById("content").value;
    content = $.trim(content);
    if (0 == content.length) {
        document.getElementById("span_content").innerHTML = "<span style='color: red'>内容不能为空</span>";
    } else {
        if (content.length > 200) {
            document.getElementById("span_content").innerHTML = "<span style='color: red'>内容不能超过200字</span>";
        } else {
            document.getElementById("span_content").innerHTML = '内容';
            $('#sendBtn').attr({
                "disabled": "disabled"
            });
            document.getElementById("span_btn").innerText = '发送中...';
            $.post(
                "/show_goods_details/sendMessage",
                {
                    receiver: receiver,
                    content: content
                },
                function (data) {
                    if (data == "ok") {
                        $('#sendBtn').removeAttr("disabled");//将按钮可用
                        document.getElementById("span_btn").innerText = '发送';
                        $('#ms_result').show();
                    } else {
                        alert("不能给自己留言");
                        document.getElementById("span_btn").innerText = '发送';
                    }

                }
            );

        }
    }

}

/*
 *检测评论内容
 */
function checkComments() {
    var comment = document.getElementById("Smohan_text").value;
    if (0 == comment.length || "" == comment) {
        document.getElementById("comments_error").innerText = "评论不能为空";
        $(".comments_error").show();
        return false;
    }
    if (comment.length > 500) {
        document.getElementById("comments_error").innerText = "评论文字太长了";
        $(".comments_error").show();
        return false;
    }
    $(".comments_error").hide();
    return true;
}
/**
 * 跳转到我的收藏
 */
function toRedirectMyCollection() {
    window.location.href = "/to_redirect_myCollection";
}

function cancelMyCollection(id) {
    var span_id = "#" + id;
    $(span_id).attr({"disabled": "disabled"});
    $.post(
        "/show_goods_details/collection",
        {
            goods_id: id,
            type: "2"
        },
        function (data) {
            if (data != "yes") {
                alert("取消收藏出错了");
            }
        }
    );
    document.getElementById(id).innerText = "已取消收藏";
}
/**
 * 跳转到我的信息页面
 */
function toRedirectMyMessage() {
    window.location.href = "/to_redirect_my_message";
}
/**
 * 全部标记已读
 */
function marksAllRead() {

    $.post(
        "/marksAllRead",
        function (data) {
            if ("no" == data) {
                alert("登录过期了，请重新登录");
            } else {
                $("#readMessage").attr({"disabled": "disabled"});
                document.getElementById("btn_span").innerText = "无未读消息";
            }

        }
    );
}
/**
 * 跳转到我的浏览足迹
 */
function toRedirectMyBrowseRecord() {
    window.location.href = "/to_redirect_myBrowseRecord";
}
/**
 * 跳转到我发布的商品
 */
function toRedirectMyGoods() {
    window.location.href = "/to_redirect_my_goods";
}

/**
 *删除商品
 *
 * @param id 商品id
 */
function deleteGoods(id) {
    var msg = "您真的确定要删除该商品?";
    if (confirm(msg) == true) {
        //执行删除
        $.post(
            "/deleteMyGoods",
            {
                goodsId:id
            },
            function(data){
                if ("no" == data){
                    alert("还没登录，删除失败");
                }else {
                    window.location.href = "/to_redirect_my_goods";
                }
            }
        );
        return true;
    } else {
        return false;
    }
}