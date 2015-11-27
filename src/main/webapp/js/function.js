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
                if (data == "yes") {
                    location.href = "/";
                } else {
                    $('#login_fail').show().delay(2000).fadeOut();
                    return false;
                }
            }
        );

    }
}
/*
 *用户名判空
 */
function checkLoginName() {
    var user_name = document.getElementById("user_name").value;
    if (0 == user_name.length || "" == user_name) {
        document.getElementById("login_name").innerHTML = "<font color='red'>用户名不能为空</font>";
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
        document.getElementById("login_password").innerHTML = "<font color='red'>密码不能为空</font>";
        return false;
    } else {
        document.getElementById("login_password").innerText = "密码";
        return true;
    }
}

function checkLoginSubmit() {

    return checkLogin();
}

/*
 *判断能否submit
 */
function checkRegister() {
    if (checkEmail() && checkRegisterName() && checkPassword() && checkPasswordsAgreement()) {
        saveUser();
        $('#register_success').show().delay(60000).fadeOut("slow");
        autoJump(5, '/');
    }
    return false;
}

/*
 * 判断邮箱格式是否合法以及是否被注册
 */
function checkEmail() {

    var js_value = document.getElementById("register_email");
    if (0 == js_value.value.length && "" == js_value.value) {
        document.getElementById("span_email").innerHTML = "<font color='red'>邮箱不能为空</font>";
        return false;
    }
    var pattern = /^([\.a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
    if (!pattern.test(js_value.value)) {
        document.getElementById("span_email").innerHTML = "<font color='red'>邮箱格式不正确</font>";
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
                document.getElementById("span_email").innerHTML = "<font color='green'>该邮箱可以注册</font>";
                return true;
            } else {
                document.getElementById("span_email").innerHTML = "<font color='red'>该邮箱已被注册</font>";
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
        document.getElementById("span_name").innerHTML = "<font color='red'>用户名不能为空</font>";
        return false;
    } else {

        if (js_value.length < 1 || js_value.length > 8) {
            document.getElementById("span_name").innerHTML = "<font color='red'>用户名长度必须在1-8之间</font>";
            return false;
        }
    }
    var reg = /^(\w|[\u4E00-\u9FA5])*$/;
    if (!reg.test(js_value)) {
        document.getElementById("span_name").innerHTML = "<font color='red'>用户名格式不合法</font>";
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
                document.getElementById("span_name").innerHTML = "<font color='green'>该用户名可以注册</font>";
                return true;
            } else {
                document.getElementById("span_name").innerHTML = "<font color='red'>该用户名已被注册</font>";
                return false;
            }
        }
    );


    return true;
}

function checkPassword() {
    var js_value = document.getElementById("register_password").value;

    if (0 == js_value.length && "" == js_value) {
        document.getElementById("span_password").innerHTML = "<font color='red'>密码不能为空</font>";
        return false;
    } else {

        if (js_value.length < 6 || js_value.length > 15) {
            document.getElementById("span_password").innerHTML = "<font color='red'>密码长度必须在6-15之间</font>";
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
        document.getElementById("re_span_password").innerHTML = "<font color='red'>确认密码不能为空</font>";
        return false;
    }
    if (!(pwd1 == pwd2)) {
        document.getElementById("re_span_password").innerHTML = "<font color='red'>两次密码不一致</font>";
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
        document.getElementById("reminding").innerHTML = "<font color='red'>重置密码中......</font>";
        //重置密码
        $.post(
            "reset_password",
            {
                email: fp
            },
            function (data) {
                if (data == "yes") {
                    document.getElementById("fp").innerHTML = "<font color='red'>邮箱不存在</font>";
                    document.getElementById("reminding").innerHTML = "<font color='white'>找回密码</font>";
                    return false;
                } else {
                    document.getElementById("fp").innerText = "邮箱";
                    $('#get_password').show().delay(60000).fadeOut("slow");
                    document.getElementById("get_password").innerHTML = judgeEmail(fp);
                    document.getElementById("reminding").innerHTML = "<font color='white'>找回密码</font>";
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
        return "密码已被重置，发送至您的邮箱: <a href='https://mail.qq.com/' style='color: green' target='_blank'>" + fp + "</a>";
    }
    if (-1 != fp.lastIndexOf('@163.com')) {
        return "密码已被重置，发送至您的邮箱: <a href='http://email.163.com/' style='color: green' target='_blank'>" + fp + "</a>";
    }
    if (-1 != fp.lastIndexOf('@126.com')) {
        return "密码已被重置，发送至您的邮箱: <a href='http://mail.126.com/' style='color: green' target='_blank'>" + fp + "</a>";
    }
    if (-1 != fp.lastIndexOf('@gmail.com')) {
        return "密码已被重置，发送至您的邮箱: <a href='https://mail.google.com/' style='color: green'>" + fp + "</a>";
    }
    return "密码已被重置，发送至您的邮箱: " + fp;
}

function checkInputEmail() {
    var fp = document.getElementById("forget_password").value;
    if (0 == fp.length || "" == fp) {
        document.getElementById("fp").innerHTML = "<font color='red'>邮箱不能为空</font>";
        return false;
    } else {
        document.getElementById("fp").innerText = "邮箱";
    }
    /*邮件格式*/
    var pattern = /^([\.a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
    if (!pattern.test(fp)) {
        document.getElementById("fp").innerHTML = "<font color='red'>邮箱格式不正确</font>";
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

    }

}
/*获取焦点获取未读的消息*/
function get_unread_message() {
    $(".message_div").show();
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
        return;
    }
    //发布商品
    if (chooseStatus != 0 && 2 == chooseStatus && "#buy_goods" == sideBarName) {
        $(sideBarName).css({
            background: "gray"
        });
        return;
    }
    //我的收藏
    if (chooseStatus != 0 && 3 == chooseStatus && "#my_collection" == sideBarName) {
        $(sideBarName).css({
            background: "gray"
        });
        return;
    }
    //已发布商品
    if (chooseStatus != 0 && 4 == chooseStatus && "#already_release" == sideBarName) {
        $(sideBarName).css({
            background: "gray"
        });
        return;
    }
    //已求购商品
    if (chooseStatus != 0 && 5 == chooseStatus && "#already_buy" == sideBarName) {
        $(sideBarName).css({
            background: "gray"
        });
        return;
    }
    //我的消息
    if (chooseStatus != 0 && 6 == chooseStatus && "#my_message" == sideBarName) {
        $(sideBarName).css({
            background: "gray"
        });
        return;
    }
    //我的足迹
    if (chooseStatus != 0 && 7 == chooseStatus && "#foot_print" == sideBarName) {
        $(sideBarName).css({
            background: "gray"
        });
        return;
    }
    //账号设置
    if (chooseStatus != 0 && 8 == chooseStatus && "#account_set" == sideBarName) {
        $(sideBarName).css({
            background: "gray"
        });
        return;
    } else {
        $(sideBarName).css({
            background: "none"
        });
    }


}
//修改选中的状态
function setChooseStatus(status) {
    chooseStatus = status;
}
//显示侧边栏
function show_sideBar(status, sideBarName) {

    $("#sidebar").show();
    setChooseStatus(status);
    deepen_div(sideBarName);
    location.href="/account"
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