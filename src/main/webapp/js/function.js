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
        $('#login_fail').show().delay(2000).fadeOut();
    }
    return false;
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
 *注册页面判空处理
 */
function checkRegister() {
    if(checkEmail() && checkRegisterName() && checkPassword() && checkPasswordsAgreement()){
        return true;
    }else{
        return false;
    }
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
        return true;
    }
    /*Ajax异步验证邮箱是否被注册*/


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
    if(!(pwd1 == pwd2)){
        document.getElementById("re_span_password").innerHTML = "<font color='red'>两次密码不一致</font>";
        return false;
    }
    document.getElementById("re_span_password").innerText = "确认密码";

    return true;
}