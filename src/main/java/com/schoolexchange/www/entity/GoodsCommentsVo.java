package com.schoolexchange.www.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2016/4/14.
 * 商品评论Vo
 */
public class GoodsCommentsVo implements Serializable {

    private static final long serialVersionUID = 1L;

    //评论名
    private String cName;
    //评论内容
    private String content;
    //评论时间
    private Date createDate;

    public GoodsCommentsVo(String cName, String content, Date createDate) {
        this.cName = cName;
        this.content = content;
        this.createDate = createDate;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
