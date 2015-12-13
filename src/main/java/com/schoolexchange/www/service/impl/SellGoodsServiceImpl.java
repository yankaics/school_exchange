package com.schoolexchange.www.service.impl;

import com.schoolexchange.www.dao.SellGodsDao;
import com.schoolexchange.www.entity.SellGoods;
import com.schoolexchange.www.entity.User;
import com.schoolexchange.www.service.SellGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Administrator
 * on 2015/12/13
 */
@Service
public class SellGoodsServiceImpl implements SellGoodsService {

    @Autowired
    private SellGodsDao sellGodsDao;

    /**
     * 验证用户能不能发布商品
     *
     * @param user 用户信息
     * @return 返回true表示不可以发布商品信息, 否则返回false
     */
    public boolean validateSaveSellGoods(User user) {
        boolean flag = false;
        if (user.getUser_goods_counts() < 16) {
            flag = true;
        }
        return flag;
    }

    /**
     * 发布商品信息
     *
     * @param sellGoods 商品信息
     */
    public void releaseGoods(SellGoods sellGoods) {
        sellGodsDao.saveSellGoods(sellGoods);
    }

    /**
     * 上传商品图片(一张图片)
     *
     * @param pics 要上传图片(数组:一个元素)
     * @return 返回上传图片的名字
     * @see com.schoolexchange.www.action.SellGoodsController
     */
    public String uploadGoodsPic(MultipartFile[] pics) {
        String picName = null;
        if (null != pics){
            for (MultipartFile pic : pics) {
                if (!pic.isEmpty()) {
                    System.out.println("文件名=== " + pic.getOriginalFilename());
                }
            }
        }
        return picName;
    }
}
