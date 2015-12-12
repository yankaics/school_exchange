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
            <form method="post" onsubmit="return false">
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
                    <label for="release_goods_name" class="div_space" id="error_goods_name">
                        商品名(<span class="required_color">*</span>):
                    </label>

                    <div class="div_input_width">
                        <input type="text" name="release_goods_name" id="release_goods_name"
                               class="form-control"  onblur="checkGoodsName('release_goods_name')">
                    </div>
                </div>
                <div class="clear_float"></div>
                <br/>

                <div class="form-group">
                    <label for="release_goods_price" class="div_space" id="error_goods_price">
                        价格(<span class="required_color">*</span>):
                    </label>

                    <div class="div_space">
                        <input type="text" class="form-control" name="release_goods_price"
                               id="release_goods_price" onblur="checkGoodsPrice('release_goods_price')">
                    </div>
                    <span class="unit">元</span>
                </div>
                <div style="clear: both"></div>
                <br/>

                <div class="form-group">
                    <label for="release_goods_count" class="div_space" id="error_goods_count">
                        数量(<span class="required_color">*</span>):
                    </label>

                    <div class="div_space">
                        <input type="text" name="release_goods_count" id="release_goods_count"
                               class="form-control" onblur="checkGoodsCount('release_goods_count')">
                    </div>
                    <span class="unit">个</span>
                </div>
                <div class="clear_float"></div>
                <br/>

                <div class="form-group">
                    <label for="release_goods_summary" class="div_space" id="error_goods_summary">
                        商品简介:
                    </label>

                    <div class="div_input_width">
                                <textarea class="form-control" name="release_goods_summary"
                                          id="release_goods_summary" rows="4" cols="37"
                                          onblur="checkGoodsSummary('release_goods_summary',79)" onfocus="clearRemind()">商品简介,50字以内
                            </textarea>
                    </div>
                </div>
                <div class="clear_float"></div>
                <br/>

                <div class="form-group">
                    <label for="release_goods_type" class="div_space" id="error_goods_type">
                        标签:
                    </label>

                    <div class="div_input_width">
                        <input type="text" class="form-control" name="release_goods_type"
                               id="release_goods_type" onblur="checkGoodsType('release_goods_type',10)">
                    </div>
                </div>
                <div class="clear_float"></div>
                <div class="form-group dmt">
                    <label for="release_goods_pic1" class="div_space" id="error_upload">
                        上传图片:
                    </label>

                    <div class="display_images">
                        <span id="firstPic">
                            <a href="javascript:" class="file" style="margin-left: 0">第一张
                                <input type="file" name="release_goods_pic1" id="release_goods_pic1"
                                       onchange="previewImageGoods(this,'preview1','one','<img id=one>')">
                            </a>
                        </span>
                        <span id="secondPic">
                            <a href="javascript:" class="file">第二张
                                <input type="file" name="release_goods_pic1" id="release_goods_pic2"
                                       onchange="previewImageGoods(this,'preview2','two','<img id=two>')">
                            </a>
                        </span>
                        <span id="thirdPic">
                            <a href="javascript:" class="file">第三张
                                <input type="file" name="release_goods_pic1" id="release_goods_pic3"
                                       onchange="previewImageGoods(this,'preview3','three','<img id=three>')">
                            </a>
                        </span>
                    </div>
                </div>
                <div class="clear_float"></div>
                <div class="form-group dmt">
                    <label class="div_space">
                        预览:
                    </label>

                    <div id="preview1" class="display_images" onmouseover="show_delete_button('#delete_pic','#one')"
                         onmouseout="hide_delete_button('#delete_pic','#one')">
                        <img id="one" src="../../images/school_exchange_no_upload_pic.jpg"
                             class="img-rounded image_width_height">
                    </div>
                    <div id="preview2" class="display_images" onmouseover="show_delete_button('#delete_pic','#two')"
                         onmouseout="hide_delete_button('#delete_pic','#two')">
                        <img id="two" src="../../images/school_exchange_no_upload_pic.jpg"
                             class="img-rounded image_width_height">
                    </div>
                    <div id="preview3" class="display_images" onmouseover="show_delete_button('#delete_pic','#three')"
                         onmouseout="hide_delete_button('#delete_pic','#two')">
                        <img id="three" src="../../images/school_exchange_no_upload_pic.jpg"
                             class="img-rounded image_width_height">
                    </div>
                </div>
                <div class="clear_float"></div>
                <div class="form-group dmt">
                    <label for="goods_deadline" class="div_space">
                        设置过期时间:
                    </label>

                    <div class="display_images">
                        <input type="text" class="form-control" id="goods_deadline" name="goods_deadline"
                               readonly style="background: #fff">
                    </div>
                </div>
                <div class="clear_float"></div>
                <div class="form-group dmt">
                    <label for="goods_address" class="div_space" id="error_goods_address">
                        地址(<span class="required_color">*</span>):
                    </label>

                    <div class="div_input_width">
                        <input type="text" class="form-control" name="goods_address" id="goods_address" onblur="checkGoodsAddress('goods_address')">
                    </div>
                </div>
                <div class="clear_float"></div>
                <div class="form-group dmt">
                    <label for="goods_contact" class="div_space" id="error_contact">
                        联系方式:
                    </label>

                    <div class="display_images">
                        <input type="text" id="goods_contact" name='goods_contact' class="form-control"
                               placeholder="电话/QQ/微信" onblur="checkContact()">
                    </div>
                </div>
                <div class="clear_float"></div>
                <div class="form-group dmt">
                    <label for="goods_content" class="div_space" id="error_goods_info">
                        商品详情:
                    </label>

                    <div class="div_space">
                                <textarea name="goods_content" cols="80" rows="16" id="goods_content" onblur="checkGoodsInfo()">

                                </textarea>
                    </div>
                </div>
                <div class="clear_float"></div>
                <div class="form-group release_btn">
                    <label class="div_input_width"></label>

                    <div>
                        <button class="btn btn-primary form-control" onclick="saveReleaseGoods()">
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
<script src="../../js/ajaxfileupload.js"></script>
<script src="../../js/function.js"></script>
<script src="../../js/js_goods.js"></script>
<script src="../../js/jquery.cxcalendar.min.js"></script>
<script>
    //HTML在线编辑器
    new TQEditor('goods_content', {advToolbarMode: true});


    //日期开始时间
    var now = new Date();
    $.cxCalendar.defaults.startDate = now;
    //日期选择器结束时间
    var newNow = new Date();
    var end = DateAdd(6, newNow);
   /* var endStr = end.getFullYear() + '-' + end.getMonth() + '-' + end.getDay();
    console.log("endStr=== " + end.toLocaleDateString());*/
    $.cxCalendar.defaults.endDate = new Date(end.toLocaleDateString());
    // 初始化日期选择器
    $('#goods_deadline').cxCalendar();
    //设置默认日期
    document.getElementById('goods_deadline').value = end.toLocaleDateString();
</script>
</body>
</html>

