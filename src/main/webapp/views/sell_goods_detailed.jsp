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
    <link rel="stylesheet" href="../styles/comments.css">
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
    <%--显示评论--%>
    <div class="row">
        <!--显示评论-->
        <div class="col-md-12">

            <h4 style="text-align: center">所有评论</h4>
            <!--表情盒子-->
            <div id="Smohan_FaceBox">
                <textarea name="text" id="Smohan_text" class="smohan_text"></textarea>
                <p>
                    <a href="javascript:void(0)" class="face" title="表情"></a>
                    <button class="button" id="Smohan_Showface" onclick="showComments()">发布评论</button>
                </p>
            </div>
            <!--发布评论框-->
            <div id="publish_comments"></div>
            <div class="every_comments">
                <a class="comment_author">东东东<span>2016-04-13</span></a>
                        <span>
                            萨达是的发送到发送到发送到发送到发送到发送到发送到法萨芬的asdfsadf阿斯顿发送到发送到发送到发送到发送到发送到发送到发送到发送到发送到发送到非
                            萨达是的发送到发送到发送到发送到发送到发送到发送到法萨芬的asdfsadf阿斯顿发送到发送到发送到发送到发送到发送到发送到发送到发送到发送到发送到非
                        </span>
            </div>
            <div class="every_comments">
                <a class="comment_author">东东东<span>2016-04-13</span></a>
                        <span>
                            萨达是的发送到发送到发送到发送到发送到发送到发送到法萨芬的asdfsadf阿斯顿发送到发送到发送到发送到发送到发送到发送到发送到发送到发送到发送到非
                        </span>
            </div>
        </div>
    </div>
</div>

<script src="../js/function.js"></script>
<script src="../js/jquery-1.8.2.min.js"></script>
<script src="../js/comments.js"></script>
<script type="text/javascript">
    $(function () {
        $("a.face").smohanfacebox({
            Event: "click",	//触发事件
            divid: "Smohan_FaceBox", //外层DIV ID
            textid: "Smohan_text" //文本框 ID
        });
        //解析表情  $('#Zones').replaceface($('#Zones').html());
    });
    function showComments() {
        $('#publish_comments').fadeIn(360);
        $("#publish_comments").prepend("<div class='every_comments'>" +
                "<a class='comment_author'>shadow<span>2016-04-13</span></a>" +
                "<span class='comment_content'>" +
                getFace($('#Smohan_text').val()) +
                "</span>" +
                "</div>");
    }
</script>
</body>
</html>
