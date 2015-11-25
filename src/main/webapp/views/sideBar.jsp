<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="sidebar">
    <div id="wrap">
        <div id="release_goods" class="item" onmouseover="deepen_div('#release_goods');"
             onmouseout="noDeepen_div('#release_goods');">
                <span>
                    <i class="glyphicon glyphicon-send"></i>
                </span>

            <div>
                发布商品
            </div>
        </div>
        <div id="buy_goods" class="item" onmouseover="deepen_div('#buy_goods');"
             onmouseout="noDeepen_div('#buy_goods');">
                <span>
                    <i class="glyphicon glyphicon-backward"></i>
                </span>

            <div>
                求购商品
            </div>
        </div>
        <div id="my_collection" class="item" onmouseover="deepen_div('#my_collection');"
             onmouseout="noDeepen_div('#my_collection');">
                <span>
                    <i class="glyphicon glyphicon-heart"></i>
                </span>

            <div>
                我的收藏
            </div>
        </div>
        <div id="already_release" class="item" onmouseover="deepen_div('#already_release');"
             onmouseout="noDeepen_div('#already_release');">
                <span>
                    <i class="glyphicon glyphicon-triangle-left"></i>
                </span>

            <div>
                已发布商品
            </div>
        </div>
        <div id="already_buy" class="item" onmouseover="deepen_div('#already_buy');"
             onmouseout="noDeepen_div('#already_buy');">
                <span>
                    <i class="glyphicon glyphicon-triangle-right"></i>
                </span>

            <div>
                已求购商品
            </div>
        </div>
        <div id="my_message" class="item" onmouseover="deepen_div('#my_message');"
             onmouseout="noDeepen_div('#my_message');">
                <span>
                    <i class="glyphicon glyphicon-comment"></i>
                </span>

            <div>
                我的消息
            </div>
        </div>
        <div id="foot_print" class="item" onmouseover="deepen_div('#foot_print');"
             onmouseout="noDeepen_div('#foot_print');">
                <span>
                    <i class="glyphicon glyphicon-eye-open"></i>
                </span>

            <div>
                我的足迹
            </div>
        </div>
        <div id="account_set" class="item" onmouseover="deepen_div('#account_set');"
             onmouseout="noDeepen_div('#account_set');">
                <span>
                    <i class="glyphicon glyphicon-user"></i>
                </span>

            <div>
                账号设置
            </div>
        </div>
    </div>
    <div onclick="closeBar()" id="closeBar">
        <div>
            <i class="glyphicon glyphicon-remove"></i>
        </div>
    </div>
</div>
