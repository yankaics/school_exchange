<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container">
    <div class="row">
        <div class="col-md-4">
            <div class="top_logo">
                <a href="/"><img src="../images/logo.png"></a>
            </div>
            <div class="top_university">
                <label for="select_university" id="belong_university"> <span
                        id="span_university"><%=null != session.getAttribute("sx_university") ? session.getAttribute("sx_university") : "烟台大学文经学院" %></span></label>
                <select id="select_university" class="form-control" onchange="selectUniversity();">
                    <option value="切换学校" selected="selected">切换学校</option>
                    <option value="烟台大学文经学院">烟台大学文经学院</option>
                    <option value="烟台大学">烟台大学</option>
                    <option value="山东工商学院">山东工商学院</option>
                    <option value="滨州医学院烟台校区">滨州医学院烟台校区</option>
                    <option value="鲁东大学">鲁东大学</option>
                </select>
            </div>

        </div>
        <!--搜索-->
        <div class="col-md-4">
            <div class="top_search">
                <form class="form-search" action="/searchGoods">
                    <input type="text" id="search_content" placeholder="请输入商品名" size="30" name="searchContent">
                    <button type="submit">搜索</button>
                </form>
            </div>
        </div>
        <div class="col-md-1">
            <div class="release_buy_goods" onmouseover="show_release_buy_goods()"
                 onmouseout="close_release_buy_goods()">
                <span class="glyphicon glyphicon-plus" style="margin-right: 20px"></span>
            </div>
            <div class="list-group" style="" id="release_and_buy" onmouseout="close_release_buy_goods()">
                <a href="/to_release_goods" class="list-group-item list-group-item-success"
                   onmouseover="show_release_buy_goods()" onclick="show_sideBar(1,'#release_goods');">发布</a>
                <a href="/to_redirect_myCollection" class="list-group-item list-group-item-success"
                   onmouseover="show_release_buy_goods()" onclick="show_sideBar(2,'#buy_goods');">收藏</a>
            </div>

        </div>
        <%
            String user_name = (String) session.getAttribute("sx_user_name");
            if (user_name != null) {
        %>
        <div class="col-md-3">

            <div class="personal_avatar">
                <div id="personal_center" onmouseover="show_personal_center()" onmouseout="close_personal_center()"
                     style="cursor: pointer">
                    <img src="../images/csdn.jpg" class="img-circle" style="width: 50px;height: 50px" id="myHeadPic">
                    <span class="glyphicon glyphicon-chevron-down"></span>
                </div>
                <div>
                    <ul class="personal_center" onmouseout="close_personal_center()">
                        <li onmouseover="show_personal_center()"><a href="#" onclick="show_sideBar(8,'#account_set');"><span
                                class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;账号设置</a></li>
                        <li onmouseover="show_personal_center()"><a href="#" onclick="toRedirectMyCollection()"> <span
                                class="glyphicon glyphicon-heart"></span>&nbsp;&nbsp;收藏商品</a></li>
                        <li onmouseover="show_personal_center()"><a href="#" onclick="show_sideBar(4,'#already_release');"><span
                                class="glyphicon glyphicon-triangle-left"></span>&nbsp;&nbsp;已发布商品</a></li>
                        <li onmouseover="show_personal_center()"><a href="#" onclick="show_sideBar(5,'#already_buy');"><span
                                class="glyphicon glyphicon-triangle-right"></span>&nbsp;&nbsp;已求购商品</a></li>
                        <li onmouseover="show_personal_center()"><a href="/logout"><span
                                class="glyphicon glyphicon-log-out"></span>&nbsp;&nbsp;退出</a></li>
                    </ul>
                </div>
            </div>

            <div class="message">
                <button class="btn btn-primary" style="background: #2BB8AA" type="button" onclick="show_sideBar(6,'#my_message');"
                        onmouseover="get_unread_message()" onmouseout="close_unread_message()">
                    未读消息 <span class="badge" id="message_count"></span>
                </button>
            </div>
            <div class="list-group message_div" onmouseout="close_unread_message();">
                <div class="look_all_message"><a href="#" class="list-group-item" style="background: #2BB8AA" onclick="show_sideBar(6,'#my_message');"
                                                 onmouseover="get_unread_message()">查看所有未读消息</a></div>
                <a href="#" id="mess_one" class="list-group-item list-group-item-success" onmouseover="get_unread_message()" onclick="show_sideBar(6,'#my_message');">
                    <div id="name_one"></div>
                </a>
                <a href="#" id="mess_two" class="list-group-item list-group-item-info" onmouseover="get_unread_message()" onclick="show_sideBar(6,'#my_message');">
                    <div id="name_two"></div>
                </a>
                <a href="#" id="mess_three" class="list-group-item list-group-item-warning" onmouseover="get_unread_message()" onclick="show_sideBar(6,'#my_message');">

                    <div id="name_three"></div>
                </a>
                <a href="#" id="mess_four" class="list-group-item list-group-item-danger" onmouseover="get_unread_message()" onclick="show_sideBar(6,'#my_message');">

                    <div id="name_four"></div>
                </a>
            </div>
        </div>
        <%
        } else {
        %>
        <div class="col-md-3">
            <div class="col-md-6" style="margin-top: 50px">
                <a class="btn btn-primary" style="background: #2BB8AA" href="/to_login">
                    登录
                </a>
            </div>
            <div class="col-md-6" style="margin-top: 50px">
                <a class="btn btn-primary" style="background: #2BB8AA" href="/to_register">
                    注册
                </a>
            </div>
        </div>
        <%
            }
        %>

    </div>
</div>