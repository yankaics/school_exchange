<%--
  Created by IntelliJ IDEA.
  User: dong
  Date: 16-3-20
  Time: 下午12:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>商品详情</title>
    <link rel="stylesheet" href="../styles/bootstrap.css">
    <link rel="stylesheet" href="../styles/main.css">
</head>
<body>
<%@include file="top.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-md-6" style="margin-top: 50px">
            <div class="row">
                <div class="col-md-9">
                    <div>
                        <img src="${sell_goods.goods_images}" class="img-thumbnail" style="height: 500px">
                    </div>
                </div>
                <div class="col-md-3" style="margin-left: 0">

                    <c:if test="${1 == isLogin }">
                        <button class="btn btn-danger">
                            <a href="/encryptCollectionUrl?requestUrl=sell_goods?detail=${sell_goods.id}"
                               class="collectionLoginStyle">收藏商品</a>
                        </button>
                    </c:if>

                    <c:if test="${0 == isLogin}">
                        <button class="btn btn-danger" onclick="CollectionGoods(${sell_goods.id})">
                                    <span id="btn_name">
                                        <c:if test="${0 == collection_status}">
                                            取消收藏
                                        </c:if>
                                        <c:if test="${1 == collection_status}">
                                            收藏商品
                                        </c:if>
                                    </span>
                        </button>
                    </c:if>
                    <div>
                        <script>window._bd_share_config = {
                            "common": {
                                "bdSnsKey": {},
                                "bdText": "",
                                "bdMini": "2",
                                "bdMiniList": false,
                                "bdPic": "",
                                "bdStyle": "0",
                                "bdSize": "16"
                            }, "slide": {"type": "slide", "bdImg": "4", "bdPos": "left", "bdTop": "100"}
                        };
                        with (document)0[(getElementsByTagName('head')[0] || body).appendChild(createElement('script')).src = 'http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion=' + ~(-new Date() / 36e5)];
                        </script>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <!--商品名-->
            <div class="detailed_name">${sell_goods.goods_name}</div>
            <!--商品简单介绍-->
            <div class="detailed_simple_desc">
                ${sell_goods.goods_desc}
            </div>
            <div style="margin-top: 20px">
                <ul class="list-group" style="">
                    <li class="list-group-item">
                        <!--价格-->
                        <div>
                            <h3><span class="label label-info">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;价格</span>
                                &nbsp;&nbsp;${sell_goods.goods_price}元</h3>
                        </div>
                    </li>
                    <li class="list-group-item">
                        <h3><span class="label label-info">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;数量</span>&nbsp;&nbsp;${sell_goods.goods_count}个
                        </h3>
                    </li>
                    <li class="list-group-item">
                        <h3><span
                                class="label label-info">&nbsp;&nbsp;&nbsp;&nbsp;发布者</span>&nbsp;&nbsp;${user.user_name}
                        </h3>
                    </li>
                    <li class="list-group-item">
                        <h3><span class="label label-info">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;标签</span>&nbsp;&nbsp;
                            <c:if test="${0 == sell_goods.goods_type.length()}">
                                无
                            </c:if>
                            <c:if test="${0 != sell_goods.goods_type.length()}">
                                ${sell_goods.goods_type}
                            </c:if>
                        </h3>
                    </li>
                    <li class="list-group-item">
                        <h3><span class="label label-info">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;地址</span>&nbsp;&nbsp;${sell_goods.goods_address}
                        </h3>
                    </li>
                    <li class="list-group-item">
                        <h3><span class="label label-info">联系方式</span>&nbsp;&nbsp;
                            <c:if test="${0 == sell_goods.contact.length()}">
                                无
                            </c:if>
                            <c:if test="${0 != sell_goods.contact.length()}">
                                ${sell_goods.contact}
                            </c:if>
                        </h3>
                    </li>
                    <li class="list-group-item">
                        <h3>
                            <button class="btn btn-danger" style="width: 100%"
                                    onclick="toMessage('${user.user_name}','${sell_goods.id}')">给他留言
                            </button>
                        </h3>
                    </li>
                </ul>
            </div>

        </div>
    </div>

    <div class="row">
        <div class="col-md-12">
            <div class="jumbotron" style="background-color: #bbd6af">
                <h4 style="text-align: center">商品详情</h4>
                <p>${sell_goods.goods_info}</p>
            </div>
        </div>
    </div>

    <div class="row">
        <!--评论-->
        <div class="col-md-1"></div>
        <div class="col-md-10" style="text-align: center">
            <!--评论-->
            评论系统
        </div>
        <div class="col-md-1"></div>
    </div>
</div>
<script src="../js/jquery-1.11.3.min.js"></script>
<script src="../js/function.js"></script>
</body>
</html>
