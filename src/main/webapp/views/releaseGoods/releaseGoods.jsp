<%--
  Created by IntelliJ IDEA.
  User: shadow
  Date: 2015/12/8
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>发布商品-校内交易</title>
    <link rel="stylesheet" href="../../styles/bootstrap.css">
    <link rel="stylesheet" href="../../styles/main.css">
</head>
<body onload="getSideNumber()">
<%@include file="../top.jsp" %>
<div class="container" style="margin-top: 20px">
    <span id="sp" style="display: none">1</span>
    <%@include file="../sideBar.jsp" %>

    <%--发布商品--%>
    <div class="row">
        <div class="col-md-2 release_goods list-group">
            <div class="list-group-item list-group-item-success">
                <h2>发布商品</h2>
            </div>
            <div class="list-group-item list-group-item-success release_remind">
                <span><br/>已发布 <span id="release_count">8</span> 个<br/>最多能发布15个商品</span>
            </div>
        </div>
        <div class="col-md-2"></div>
        <div class="col-md-8">
            <form method="post">
                <div class="form-group">
                    <label for="release_university" class="div_space">
                        学校:
                    </label>

                    <div class="div_input_width">
                        <input type="text" name="release_university" id="release_university"
                               class="form-control" value="烟台大学文经学院" readonly="readonly" disabled>
                    </div>
                </div>
                <div class="clear_float"></div>
                <br/>

                <div class="form-group">
                    <label for="release_goods_name" class="div_space">
                        商品名(<span class="required_color">*</span>):
                    </label>

                    <div class="div_input_width">
                        <input type="text" name="release_goods_name" id="release_goods_name"
                               class="form-control" value="java核心技术">
                    </div>
                </div>
                <div class="clear_float"></div>
                <br/>

                <div class="form-group">
                    <label for="release_goods_price" class="div_space">
                        价格(<span class="required_color">*</span>):
                    </label>

                    <div class="div_space">
                        <input type="text" class="form-control" name="release_goods_price"
                               id="release_goods_price">
                    </div>
                    <span class="unit">元</span>
                </div>
                <div style="clear: both"></div>
                <br/>

                <div class="form-group">
                    <label for="release_goods_count" class="div_space">
                        数量(<span class="required_color">*</span>):
                    </label>

                    <div class="div_space">
                        <input type="text" name="release_goods_count" id="release_goods_count"
                               class="form-control">
                    </div>
                    <span class="unit">个</span>
                </div>
                <div class="clear_float"></div>
                <br/>

                <div class="form-group">
                    <label for="release_goods_summary" class="div_space">
                        商品简介:
                    </label>

                    <div class="div_input_width">
                                <textarea class="form-control" name="release_goods_summary"
                                          id="release_goods_summary" rows="4" cols="37">商品简介,50字以内

                            </textarea>
                    </div>
                </div>
                <div class="clear_float"></div>
                <br/>

                <div class="form-group">
                    <label for="release_goods_type" class="div_space">
                        标签:
                    </label>

                    <div class="div_input_width">
                        <input type="text" class="form-control" name="release_goods_type"
                               id="release_goods_type">
                    </div>
                </div>
                <div class="clear_float"></div>
                <div class="form-group dmt">
                    <label for="release_goods_pic1" class="div_space">
                        上传图片:
                    </label>

                    <div class="display_images">
                        <a href="" class="file" style="margin-left: 0">第一张
                            <input type="file" name="release_goods_pic1" id="release_goods_pic1"
                                   onchange="previewImageGoods(this,'preview1','one','<img id=one>')">
                        </a>
                        <a href="javascript:" class="file">第二张
                            <input type="file" name="release_goods_pic1" id="release_goods_pic2"
                                   onchange="previewImageGoods(this,'preview2','two','<img id=two>')">
                        </a>
                        <a href="javascript:" class="file">第三张
                            <input type="file" name="release_goods_pic1" id="release_goods_pic3"
                                   onchange="previewImageGoods(this,'preview3','three','<img id=three>')">
                        </a>
                    </div>
                </div>
                <div class="clear_float"></div>
                <div class="form-group dmt">
                    <label class="div_space">
                        预览:
                    </label>

                    <div id="preview1" class="display_images">
                        <img id="one" src="../../images/nopic.jpg" class="img-rounded image_width_height">
                    </div>
                    <div id="preview2" class="display_images">
                        <img id="two" src="../../images/nopic.jpg" class="img-rounded image_width_height">
                    </div>
                    <div id="preview3" class="display_images">
                        <img id="three" src="../../images/nopic.jpg" class="img-rounded image_width_height">
                    </div>
                </div>
                <div class="clear_float"></div>
                <div class="form-group dmt">
                    <label for="goods_deadline" class="div_space">
                        设置过期时间:
                    </label>

                    <div class="display_images">
                        <input type="text" class="form-control" id="goods_deadline" name="goods_deadline"
                               readonly style="background: #fff" value="2016-01-01">
                    </div>
                </div>
                <div class="clear_float"></div>
                <div class="form-group dmt">
                    <label for="goods_address" class="div_space">
                        地址(<span class="required_color">*</span>):
                    </label>

                    <div class="div_input_width">
                        <input type="text" class="form-control" name="goods_address" id="goods_address">
                    </div>
                </div>
                <div class="clear_float"></div>
                <div class="form-group dmt">
                    <label for="goods_contact" class="div_space">
                        联系方式:
                    </label>

                    <div class="display_images">
                        <input type="text" id="goods_contact" name=goods_contact class="form-control"
                               value="电话/QQ/微信">
                    </div>
                </div>
                <div class="clear_float"></div>
                <div class="form-group dmt">
                    <label for="content" class="div_space">
                        商品详情:
                    </label>

                    <div class="div_space">
                                <textarea name="content" cols="80" rows="16" id="content">

                                </textarea>
                    </div>
                </div>
                <div class="clear_float"></div>
                <div class="form-group release_btn">
                    <label class="div_input_width"></label>

                    <div>
                        <button class="btn btn-primary form-control">
                            发布
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="../../js/jquery-1.11.3.min.js"></script>
<script src="../../editor/TQEditor.js?skin=yellow"></script>
<script src="../../js/bootstrap.js"></script>
<script src="../../js/function.js"></script>
<script src="../../js/js_release_goods.js"></script>
<script src="../../js/jquery.cxcalendar.min.js"></script>
<script>
    //HTML在线编辑器
    new TQEditor('content', {advToolbarMode: true});

    // 日期选择器
    $('#goods_deadline').cxCalendar();
</script>
</body>
</html>

