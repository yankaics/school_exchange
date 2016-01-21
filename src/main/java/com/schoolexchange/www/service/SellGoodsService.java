package com.schoolexchange.www.service;

import com.schoolexchange.www.entity.SellGoods;
import com.schoolexchange.www.entity.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator
 * on 2015/12/13
 */
public interface SellGoodsService {

    /**
     * 验证用户能不能发布商品
     *
     * @param user 用户信息
     * @return 返回true表示不可以发布商品信息, 否则返回false
     * @see com.schoolexchange.www.action.SellGoodsController
     */
    boolean validateSaveSellGoods(User user);

    /**
     * 发布商品信息
     *
     * @param sellGoods 商品信息
     */
    Integer releaseGoods(SellGoods sellGoods);

    /**
     * 上传商品图片(一张图片)
     *
     * @param pics 要上传图片(数组:一个元素)
     * @return 返回上传图片的名字
     * @see com.schoolexchange.www.action.SellGoodsController
     */
    String uploadGoodsPic(MultipartFile[] pics, HttpServletRequest request) throws Exception;

    /**
     * 修改用户发布商品的数量
     *
     * @param user 用户
     * @param num  修改用户发布商品数量的值
     * @see com.schoolexchange.www.action.SellGoodsController
     */
    void alterGoodsCounts(User user, int num);

    /**
     * 获取大学的商品统计
     *
     * @param type       商品类型 0表示出售的商品,1表示求购的商品
     * @param university 大学名称
     * @return 总的商品数量
     */
    Integer getUniversityGoodsCount(int type, String university);
}
