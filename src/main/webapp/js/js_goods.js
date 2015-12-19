/**
 * Created by shadow on 2015/12/8.
 */
/*
 *图片预览
 */
function previewImageGoods(file, div_id, img_id, img) {
    var flag = checkUploadPic();
    if (!flag) {
        return false;
    }
    var MAXWIDTH = 100;
    var MAXHEIGHT = 100;
    var div = document.getElementById(div_id);
    if (file.files && file.files[0]) {
        div.innerHTML = img;
        var img = document.getElementById(img_id);
        img.onload = function () {
            var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
            img.width = rect.width;
            img.height = rect.height;
            img.style.marginLeft = rect.left + 'px';
            img.style.marginTop = rect.top + 'px';
        }
        var reader = new FileReader();
        reader.onload = function (evt) {
            img.src = evt.target.result;
        }
        reader.readAsDataURL(file.files[0]);
    }
    else {
        console.log('删除滤镜');
    }
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
/**
 * 判断图片大小、格式是否合法
 */
function checkUploadPic() {
    //判断第一张图片是否合法
    var one = document.getElementById('release_goods_pic1').files[0];
    if (null != one) {
        if (one.type != 'image/jpeg' && one.type != 'image/png') {
            clearPreviewPic(1);
            document.getElementById("error_upload").innerHTML = "<span style='color: red'>只能上传jpg和png图片</span>";
            //清空input type='file'
            document.getElementById("firstPic").innerHTML =
                '<a href="javascript:" class="file" style="margin-left: 0">第一张' +
                '<input type="file" name="release_goods_pic1" id="release_goods_pic1" onchange="previewImageGoods(this,\'preview1\',\'one\',\'<img id=one>\')" </a>';
            return false;
        }
        if (one.size > 1048576) {
            clearPreviewPic(1);
            document.getElementById("error_upload").innerHTML = "<span style='color: red'>上传图片不能超过1M</span>";
            document.getElementById("firstPic").innerHTML =
                '<a href="javascript:" class="file" style="margin-left: 0">第一张' +
                '<input type="file" name="release_goods_pic1" id="release_goods_pic1" onchange="previewImageGoods(this,\'preview1\',\'one\',\'<img id=one>\')" </a>';
            return false;
        } else {
            document.getElementById("error_upload").innerText = '上传图片:';
            return true;
        }
    }
    //判断第二张图片是否合法
    var two = document.getElementById('release_goods_pic2').files[0];
    if (null != two) {
        if (two.type != 'image/jpeg' && two.type != 'image/png') {
            clearPreviewPic(2);
            document.getElementById("error_upload").innerHTML = "<span style='color: red'>只能上传jpg和png图片</span>";
            document.getElementById("secondPic").innerHTML =
                '<a href="javascript:" class="file" style="margin-left: 0">第二张' +
                '<input type="file" name="release_goods_pic1" id="release_goods_pic2" onchange="previewImageGoods(this,\'preview2\',\'two\',\'<img id=two>\')" </a>';
            return false;
        }
        if (two.size > 1048576) {
            clearPreviewPic(2);
            document.getElementById("error_upload").innerHTML = "<span style='color: red'>上传图片不能超过1M</span>";
            document.getElementById("secondPic").innerHTML =
                '<a href="javascript:" class="file" style="margin-left: 0">第二张' +
                '<input type="file" name="release_goods_pic1" id="release_goods_pic2" onchange="previewImageGoods(this,\'preview2\',\'two\',\'<img id=two>\')" </a>';
            return false;
        } else {
            document.getElementById("error_upload").innerText = '上传图片:';
            return true;
        }
    }
    var three = document.getElementById('release_goods_pic3').files[0];
    if (null != three) {
        if (three.type != 'image/jpeg' && three.type != 'image/png') {
            clearPreviewPic(3);
            document.getElementById("error_upload").innerHTML = "<span style='color: red'>只能上传jpg和png图片</span>";
            document.getElementById("thirdPic").innerHTML =
                '<a href="javascript:" class="file" style="margin-left: 0">第三张' +
                '<input type="file" name="release_goods_pic1" id="release_goods_pic3" onchange="previewImageGoods(this,\'preview3\',\'three\',\'<img id=three>\')" </a>';
            return false;
        }
        if (three.size > 1048576) {
            clearPreviewPic(3);
            document.getElementById("error_upload").innerHTML = "<span style='color: red'>上传图片不能超过1M</span>";
            document.getElementById("thirdPic").innerHTML =
                '<a href="javascript:" class="file" style="margin-left: 0">第三张' +
                '<input type="file" name="release_goods_pic1" id="release_goods_pic2" onchange="previewImageGoods(this,\'preview3\',\'three\',\'<img id=three>\')" </a>';
            return false;
        } else {
            document.getElementById("error_upload").innerText = '上传图片:';
            return true;
        }
    }

    return true;
}
/**
 * 清空预览的图片
 */
function clearPreviewPic(number) {
    if (1 == number) {
        document.getElementById("preview1").innerHTML =
            '<img id="one" src="../../images/school_exchange_no_upload_pic.jpg" class="img-rounded image_width_height">';
    }
    if (2 == number) {
        document.getElementById("preview2").innerHTML =
            '<img id="two" src="../../images/school_exchange_no_upload_pic.jpg" class="img-rounded image_width_height">';
    }
    if (3 == number) {
        document.getElementById("preview3").innerHTML =
            '<img id="three" src="../../images/school_exchange_no_upload_pic.jpg" class="img-rounded image_width_height">';
    }
}
/*商品名、价格、数量、地址判空*/
function sentencedEmpty(id) {
    var value = document.getElementById(id).value;
    if (null == value || "" == value) {
        if (id == "release_goods_name") {
            document.getElementById("error_goods_name").innerHTML = "<span style='color: red'>商品名不能为空</span>";
            return false;
        }
        if (id == "release_goods_price") {
            document.getElementById("error_goods_price").innerHTML = "<span style='color: red'>商品价格不能为空</span>";
            return false;
        }
        if (id == "release_goods_count") {
            document.getElementById("error_goods_count").innerHTML = "<span style='color: red'>商品数量不能空</span>";
            return false;
        }
        if (id == "goods_address") {
            document.getElementById("error_goods_address").innerHTML = "<span style='color: red'>地址不能为空</span>";
            return false;
        }
    } else {
        if (id == "release_goods_name") {
            document.getElementById("error_goods_name").innerHTML = '商品名(<span class="required_color">*</span>):';
        }
        if (id == "release_goods_price") {
            document.getElementById("error_goods_price").innerHTML = '价格(<span class="required_color">*</span>):';
        }
        if (id == "release_goods_count") {
            document.getElementById("error_goods_count").innerHTML = '地址(<span class="required_color">*</span>):';
        }
        if (id == "goods_address") {
            document.getElementById("error_goods_address").innerHTML = '地址(<span class="required_color">*</span>):';
        }
    }

    return true;
}

/*
 *检测商品名是否合法
 */
function checkGoodsName(id) {
    var flag = sentencedEmpty(id);
    if (flag) {
        var goods_name = document.getElementById(id).value;
        if (goods_name.length > 20 || goods_name.length < 1) {
            if (id == "release_goods_name") {
                document.getElementById("error_goods_name").innerHTML = "<span style='color: red'>商品名字数不能超过20</span>";
                return false;
            }
        } else {
            document.getElementById("error_goods_name").innerHTML = '商品名(<span class="required_color">*</span>):';
            return true;
        }
    } else {
        return flag;
    }
}

/*
 检测商品价格是否合法
 */
function checkGoodsPrice(id) {
    var flag = sentencedEmpty(id);
    if (flag) {
        var goods_price = document.getElementById(id).value;
        var pattern = /^\d+(?=\.{0,1}\d+$|$)/;
        if (!pattern.test(goods_price) || goods_price < 0) {
            document.getElementById("error_goods_price").innerHTML = "<span style='color: red'>商品价格不合法</span>";
            return false;
        }
        if (goods_price > 1000) {
            document.getElementById("error_goods_price").innerHTML = "<span style='color: red'>商品价格不能超过1万</span>";
            return false;
        }
        document.getElementById("error_goods_price").innerHTML = '价格(<span class="required_color">*</span>):';
        return true;
    } else {
        return flag;
    }
}

/*
 *检测商品数量是否合法
 */
function checkGoodsCount(id) {
    var flag = sentencedEmpty(id);
    if (flag) {
        var goods_count = document.getElementById(id).value;
        var pattern = '^[0-9]*[1-9][0-9]*$';
        var re = new RegExp(pattern);
        if (goods_count.match(re) == null) {
            document.getElementById("error_goods_count").innerHTML = "<span style='color: red'>商品数量必须为正整数</span>";
            return false;
        }
        if (goods_count > 1000) {
            document.getElementById("error_goods_count").innerHTML = "<span style='color: red'>商品数量不能超过1000个</span>";
            return false;
        }
        document.getElementById("error_goods_count").innerHTML = '地址(<span class="required_color">*</span>):';
        return true;
    } else {
        return flag;
    }
}

/*
 *商品简介在50字以内
 */
function checkGoodsSummary(id, limit_num) {
    var goods_summary = document.getElementById(id).value;
    if (goods_summary.length > limit_num) {
        document.getElementById("error_goods_summary").innerHTML = "<span style='color: red'>商品简介不能超过50个字</span>";
        return false;
    } else {
        document.getElementById("error_goods_summary").innerText = '商品简介:';
        return true;
    }
}
/*
 *检测标签长度和否合法
 */
function checkGoodsType(id, limit_num) {
    var goods_type = document.getElementById(id).value;
    if (goods_type.length > limit_num) {
        document.getElementById("error_goods_type").innerHTML = "<span style='color: red'>商品标签不能超过10个字</span>";
        return false;
    } else {
        document.getElementById("error_goods_type").innerText = '标签:';
        return true;
    }
}

/*鼠标移上显示删除按钮*/
function show_delete_button(id, img_id) {
    var img_path = $(img_id)[0].src;
    var flag = img_path.indexOf('school_exchange_no_upload_pic.jpg');
    if (-1 == flag) {
        if (img_id == '#one') {
            $('#preview1').prepend(
                '<div id="delete_pic"> <button class="btn btn-danger btn-sm" onclick="return deleteImage(\'#one\')" onmouseover="show_delete_button(\'#delete_pic\',\'#one\')">删除</button></div>'
            );
        }
        if (img_id == '#two') {
            $('#preview2').prepend(
                '<div id="delete_pic"> <button class="btn btn-danger btn-sm" onclick="return deleteImage(\'#two\')" onmouseover="show_delete_button(\'#delete_pic\',\'#two\')">删除</button></div>'
            );
        }
        if (img_id == '#three') {
            $('#preview3').prepend(
                '<div id="delete_pic"> <button class="btn btn-danger btn-sm" onclick="return deleteImage(\'#three\')" onmouseover="show_delete_button(\'#delete_pic\',\'#three\')">删除</button></div>'
            );
        }
    }
    $(id).show();
}

/*鼠标离开隐藏删除按钮*/
function hide_delete_button(id) {
    $(id).hide();
}

/*删除上传的图片*/
function deleteImage(img_id) {
    var img_path = $(img_id)[0].src;
    console.log("图片全路径=== " + img_path);
    var flag = img_path.indexOf('school_exchange_no_upload_pic.jpg');
    if (-1 == flag) {
        if (img_id == '#one') {
            clearPreviewPic(1);
            document.getElementById("firstPic").innerHTML =
                '<a href="javascript:" class="file" style="margin-left: 0">第一张' +
                '<input type="file" name="release_goods_pic1" id="release_goods_pic1" onchange="previewImageGoods(this,\'preview1\',\'one\',\'<img id=one>\')" </a>';
        }
        if (img_id == '#two') {
            clearPreviewPic(2);
            document.getElementById("secondPic").innerHTML =
                '<a href="javascript:" class="file" style="margin-left: 0">第二张' +
                '<input type="file" name="release_goods_pic1" id="release_goods_pic2" onchange="previewImageGoods(this,\'preview2\',\'two\',\'<img id=two>\')" </a>';
        }
        if (img_id == '#three') {
            clearPreviewPic(3);
            document.getElementById("thirdPic").innerHTML =
                '<a href="javascript:" class="file" style="margin-left: 0">第三张' +
                '<input type="file" name="release_goods_pic1" id="release_goods_pic2" onchange="previewImageGoods(this,\'preview3\',\'three\',\'<img id=three>\')" </a>';
        }
    }
}

//日期加减
function DateAdd(number, date) {
    date.setMonth(date.getMonth() + number);
    return date;
}

/**
 * 检测地址
 */
function checkGoodsAddress(id) {
    var flag = sentencedEmpty(id);
    if (!flag) {
        return false;
    }
    var goods_address = document.getElementById(id).value;
    if (goods_address.length > 30) {
        document.getElementById('error_goods_address').innerHTML = "<span style='color:red'>地址不能太长</span>";
        return false;
    } else {
        document.getElementById('error_goods_address').innerHTML = '地址(<span class="required_color">*</span>):';
        return true;
    }
}

/**
 * 检测联系方式
 */
function checkContact() {
    var contact = document.getElementById("goods_contact").value;
    if (contact.length > 50) {
        document.getElementById('error_contact').innerHTML = "<span style='color:red'>联系方式不能太长</span>";
        return false;
    } else {
        document.getElementById('error_contact').innerText = "联系方式:";
        return true;
    }
}

/**
 * 鼠标移上清空商品简短提醒
 */
function clearRemind() {
    document.getElementById("release_goods_summary").innerText = "";
}

/**
 * 检测商品详情
 */
function checkGoodsInfo() {
    var content = document.getElementById("goods_content").value;
    /* console.log("长度=== " + content.length);*/
    if (content.length > 10000) {
        document.getElementById("error_goods_info").innerHTML = "<span style='color: red'>商品详情太长</span>";
        return false;
    } else {
        document.getElementById("error_goods_info").innerText = "商品详情:";
        return true;
    }
}

/**
 *保存发布的商品
 */
function saveReleaseGoods() {
    //商品名
    var flag1 = checkGoodsName('release_goods_name');
    //商品价格
    var flag2 = checkGoodsPrice('release_goods_price');
    //商品数量
    var flag3 = checkGoodsCount('release_goods_count');
    //商品地址
    var flag4 = checkGoodsAddress('goods_address');
    //商品简介
    var flag5 = checkGoodsSummary('release_goods_summary', 79);
    //标签
    var flag6 = checkGoodsType('release_goods_type', 10);
    //联系方式
    var flag7 = checkContact();
    //商品详情
    var flag8 = checkGoodsInfo();
    if (flag1 && flag2 && flag3 && flag4 && flag5 && flag6 && flag7 && flag8) {
        //获取商品的所有的信息
        var goods_name = document.getElementById("release_goods_name").value;
        var goods_price = document.getElementById("release_goods_price").value;
        var goods_count = document.getElementById("release_goods_count").value;
        var goods_desc = document.getElementById("release_goods_summary").value;
        var goods_tag = document.getElementById("release_goods_type").value;
        /* var imageArr = ['release_goods_pic1', 'release_goods_pic2', 'release_goods_pic3'];*/
        var imageArr = ['release_goods_pic1'];
        var goods_deadline = document.getElementById("goods_deadline").value;
        var goods_address = document.getElementById("goods_address").value;
        var goods_contact = document.getElementById("goods_contact").value;
        var goods_info = document.getElementById("goods_content").value;
        var encode_goods_info = encodeURI(goods_info);
        //状态,判断是更新还是插入,goods_status=1表示插入，2更新
        var status = document.getElementById("goods_status").innerText;
        $.ajaxFileUpload({
            url: '/to_release_goods/update_goods?status=' + encodeURI(status),
            secureuri: false,                       //是否启用安全提交,默认为false
            fileElementId: imageArr,        //文件选择框的id属性
            data: {
                goods_name: goods_name,
                goods_price: goods_price,
                goods_count: goods_count,
                goods_desc: goods_desc,
                goods_tag: goods_tag,
                goods_deadline: goods_deadline,
                goods_address: goods_address,
                goods_contact: goods_contact,
                goods_info: encode_goods_info
            },
            dataType: 'xml',                       //服务器返回的格式,可以是json或xml等
            success: function (data) {        //服务器响应成功时的处理函数
                var infos = data.getElementsByTagName("info");
                for (var i = 0; i < infos.length; i++) {
                    var result = infos[i].getElementsByTagName('result')[0].firstChild.data;

                    if (result == 1) {
                        var id = infos[i].getElementsByTagName('id')[0].firstChild.data;
                        location.href = "/to_release_goods?status=3&id=" + id;
                    }
                    if (result == 0) {
                        alert("您发布的商品数量已达上限!!!");
                    }
                }
            },
            error: function (data, status, e) { //服务器响应失败时的处理函数
                console.log(status);
                var obj = eval(data);
                console.log("错误数据=== " + obj.result);
            }
        });
        return true;
    } else {
        console.log("error。。。。。");
        return false;
    }
}

