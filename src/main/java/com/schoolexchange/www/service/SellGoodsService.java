package com.schoolexchange.www.service;

import com.schoolexchange.www.entity.SellGoods;
import com.schoolexchange.www.entity.User;
import org.springframework.web.multipart.MultipartFile;

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
    void releaseGoods(SellGoods sellGoods);

    /**
     * 上传商品图片(一张图片)
     *
     * @param pics 要上传图片(数组:一个元素)
     * @return 返回上传图片的名字
     * @see com.schoolexchange.www.action.SellGoodsController
     */
    String uploadGoodsPic(MultipartFile[] pics);
}
