<%--
  User: shadow
  Date: 2015/11/10
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
    <link rel="stylesheet" href="../styles/bootstrap.css">
    <link rel="stylesheet" href="../styles/main.css">
</head>
<body>
<%@include file="top.jsp" %>
<!--所有商品-->
<div class="container" style="margin-top: 40px;background: #E3EEEC">
    <div class="row">
        <div class="col-md-12">
            <div>
                <div class="nav_top_all">
                    <a href="#all">所有商品</a>
                </div>
                <div class="nav_top_want">
                    <a href="#want">求购商品</a>
                </div>
            </div>
        </div>
    </div>
    <div class="clear_float"></div>
    <div style="margin-top: 30px"></div>
    <%--//首页商品展示--%>
    <c:if test="${null != indexGoods}">
        <c:forEach items="${indexGoods}" var="goods" varStatus="status" step="5">
            <%--${status.count}:${indexGoods.get(status.count-1).goods_name}--%>
            <div class="row">
                <div class="col-md-2">
                    <a href="/sell_goods?detail=${indexGoods.get(status.index).id}" target="_blank" style="text-decoration: none;">
                        <img src="${indexGoods.get(status.index).goods_images}" class="img-thumbnail image_style">
                        <em style="color: red">
                            <b>¥</b>${indexGoods.get(status.index).goods_price}
                        </em><br/>
                        <h5 style="color: black">${indexGoods.get(status.index).goods_name}</h5>
                    </a>
                </div>
                <c:if test="${indexGoods.size() > (status.index + 1 )}">
                    <div class="col-md-2">
                        <a href="/sell_goods?detail=${indexGoods.get(status.index + 1).id}" target="_blank" style="text-decoration: none;">
                            <img src="${indexGoods.get(status.index + 1 ).goods_images}"
                                 class="img-thumbnail image_style">
                            <em style="color: red">
                                <b>¥</b>${indexGoods.get(status.index + 1 ).goods_price}
                            </em><br/>
                            <h5>${indexGoods.get(status.index + 1 ).goods_name}</h5>
                        </a>
                    </div>
                </c:if>

                <c:if test="${indexGoods.size() > (status.index + 2)}">
                    <div class="col-md-2">
                        <a href="/sell_goods?detail=${indexGoods.get(status.index + 2).id}" target="_blank" style="text-decoration: none;">
                            <img src="${indexGoods.get(status.index + 2 ).goods_images}"
                                 class="img-thumbnail image_style">
                            <em style="color: red">
                                <b>¥</b>${indexGoods.get(status.index + 2 ).goods_price}
                            </em><br/>
                            <h5>${indexGoods.get(status.index + 2 ).goods_name}</h5>
                        </a>
                    </div>
                </c:if>
                <c:if test="${indexGoods.size() > (status.index + 3)}">
                    <div class="col-md-2">
                        <a href="/sell_goods?detail=${indexGoods.get(status.index + 3).id}" target="_blank" style="text-decoration: none;">
                            <img src="${indexGoods.get(status.index + 3 ).goods_images}"
                                 class="img-thumbnail image_style">
                            <em style="color: red">
                                <b>¥</b>${indexGoods.get(status.index + 3 ).goods_price}
                            </em><br/>
                            <h5>${indexGoods.get(status.index + 3 ).goods_name}</h5>
                        </a>
                    </div>
                </c:if>
                <c:if test="${indexGoods.size() > (status.index + 4)}">
                    <div class="col-md-2">
                        <a href="/sell_goods?detail=${indexGoods.get(status.index + 3).id}" target="_blank" style="text-decoration: none;">
                            <img src="${indexGoods.get(status.index + 4 ).goods_images}"
                                 class="img-thumbnail image_style">
                            <em style="color: red">
                                <b>¥</b>${indexGoods.get(status.index + 4 ).goods_price}
                            </em><br/>
                            <h5>${indexGoods.get(status.index + 4 ).goods_name}</h5>
                        </a>
                    </div>
                </c:if>
                <c:if test="${indexGoods.size() > (status.index + 5)}">
                    <div class="col-md-2">
                        <a href="/sell_goods?detail=${indexGoods.get(status.index + 3).id}" target="_blank" style="text-decoration: none;">
                            <img src="${indexGoods.get(status.index + 5 ).goods_images}"
                                 class="img-thumbnail image_style">
                            <em style="color: red">
                                <b>¥</b>${indexGoods.get(status.index + 5 ).goods_price}
                            </em><br/>
                            <h5>${indexGoods.get(status.index + 5 ).goods_name}</h5>
                        </a>
                    </div>
                </c:if>

            </div>
        </c:forEach>
    </c:if>


    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-7" style="text-align: center">
            <nav>
                <ul class="pagination pagination-lg">
                    <li>
                        <a href="#" aria-label="Previous">
                            <span aria-hidden="true">&lt;上一页</span>
                        </a>
                    </li>
                    <li class="active"><a href="#">1</a></li>
                    <li><a href="#">2</a></li>
                    <li><a href="#">3</a></li>
                    <li><a href="#">4</a></li>
                    <li><a href="#">5</a></li>
                    <li class="disabled"><a href="#">....</a></li>
                    <li>
                        <a href="#" aria-label="Next">
                            <span aria-hidden="true">下一页&gt;</span>
                        </a>
                    </li>
                </ul>

            </nav>
        </div>
        <div class="col-md-1">
            <div style="margin-top: 35px;float: left">
                共<span style="color: green">100</span>页
            </div>
        </div>
        <div class="col-md-2"></div>
    </div>
</div>
<p id="back-to-top" style="display: block;"><a href="#top"><span></span>回到顶部</a></p>
<script src="../js/jquery-1.11.3.min.js"></script>
<script src="../js/bootstrap.js"></script>
<script src="../js/function.js"></script>
<script>
    $(document).ready(function () {
        //首先将#back-to-top隐藏
        $("#back-to-top").hide();
        //当滚动条的位置处于距顶部100像素以下时，跳转链接出现，否则消失
        $(function () {
            $(window).scroll(function () {
                if ($(window).scrollTop() > 100) {
                    $("#back-to-top").fadeIn(500);
                }
                else {
                    $("#back-to-top").fadeOut(500);
                }
            });
            //当点击跳转链接后，回到页面顶部位置
            $("#back-to-top").click(function () {
                $('body,html').animate({scrollTop: 0}, 100);
                return false;
            });
        });
    });
</script>
</body>
</html>
