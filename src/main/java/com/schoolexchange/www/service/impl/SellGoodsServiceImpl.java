package com.schoolexchange.www.service.impl;

import com.schoolexchange.www.dao.SellGodsDao;
import com.schoolexchange.www.dao.UserDao;
import com.schoolexchange.www.entity.SellGoods;
import com.schoolexchange.www.entity.User;
import com.schoolexchange.www.service.QiniuService;
import com.schoolexchange.www.service.SellGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.UUID;

/**
 * Created by Administrator
 * on 2015/12/13
 */
@Service
public class SellGoodsServiceImpl implements SellGoodsService {

    @Autowired
    private SellGodsDao sellGodsDao;

    @Autowired
    private QiniuService qiniuService;

    @Autowired
    private UserDao userDao;

    /**
     * 验证用户能不能发布商品
     *
     * @param user 用户信息
     * @return 返回true表示不可以发布商品信息, 否则返回false
     */
    public boolean validateSaveSellGoods(User user) {
        boolean flag = false;
        if (user.getUser_goods_counts() < 15) {
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
    public String uploadGoodsPic(MultipartFile[] pics, HttpServletRequest request) throws Exception {
        String picName = null;
        if (null != pics) {
            for (MultipartFile pic : pics) {
                if (!pic.isEmpty()) {
                    String tmpPath = request.getServletContext().getRealPath("views") + "\\";
                    //System.out.println("根路径" + request.getServletContext().getRealPath("tmp") );
                    String resetName = UUID.randomUUID().toString();
                    String newPicName = resetName + pic.getOriginalFilename().substring(pic.getOriginalFilename().lastIndexOf('.'));
                    picName = newPicName;
                    String path = tmpPath + newPicName;
                    pic.transferTo(new File(path));
                    File file = new File(path);
                    qiniuService.setAccessKey("Zm0x_pMEfrKAWYlzSAnMXvdEXuOP3kaCFhBebuf4");
                    qiniuService.setSecretKey("Ypu9e__2WJxsL-MoUTGqUR4EyexVMdXd_DT-4Olx");
                    qiniuService.setDomain("7xo7z2.com1.z0.glb.clouddn.com");
                    qiniuService.setBucketName("schoolexchange");
                    /*qiniuService.uploadFile(file);*/
                    if (file.exists()) {
                        file.delete();
                    }
                }
            }
        }
        return picName;
    }

    /**
     * 修改用户发布商品的数量
     *
     * @param user 用户
     * @param num  修改用户发布商品数量的值
     * @see com.schoolexchange.www.action.SellGoodsController
     */
    public void alterGoodsCounts(User user, int num) {
        user.setUser_goods_counts(user.getUser_goods_counts() + num);
        userDao.updateUserGoodsCount(user);
    }
}
