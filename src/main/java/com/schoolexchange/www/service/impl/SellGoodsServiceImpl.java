package com.schoolexchange.www.service.impl;

import com.schoolexchange.www.dao.SellGoodsDao;
import com.schoolexchange.www.dao.UserDao;
import com.schoolexchange.www.entity.*;
import com.schoolexchange.www.service.QiniuService;
import com.schoolexchange.www.service.SellGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator
 * on 2015/12/13
 */
@Service
public class SellGoodsServiceImpl implements SellGoodsService {

    @Autowired
    private SellGoodsDao sellGoodsDao;

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
    public Integer releaseGoods(SellGoods sellGoods) {

        return sellGoodsDao.saveSellGoods(sellGoods);
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
                    String tmpPath = request.getServletContext().getRealPath("views");
                    //System.out.println("根路径" + request.getServletContext().getRealPath("tmp") );
                    String resetName = UUID.randomUUID().toString();
                    String newPicName = resetName + pic.getOriginalFilename().substring(pic.getOriginalFilename().lastIndexOf('.'));
                    picName = newPicName;
                    String path = tmpPath + File.separator + newPicName;
                    pic.transferTo(new File(path));
                    File file = new File(path);
                    qiniuService.setAccessKey("Zm0x_pMEfrKAWYlzSAnMXvdEXuOP3kaCFhBebuf4");
                    qiniuService.setSecretKey("Ypu9e__2WJxsL-MoUTGqUR4EyexVMdXd_DT-4Olx");
                    qiniuService.setDomain("7xo7z2.com1.z0.glb.clouddn.com");
                    qiniuService.setBucketName("schoolexchange");
                    qiniuService.uploadFile(file);
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

    /**
     * 获取大学的商品统计
     *
     * @param type       商品类型 0表示出售的商品,1表示求购的商品
     * @param university 大学名称
     * @return 总的商品数量
     */
    public Integer getUniversityGoodsCount(int type, String university) {
        Integer goodsCount = 0;
        String hql = null;
        if (0 == type) {
            hql = "select count(*) from SellGoods as sg, User as u where  sg.id=u.id and u.user_university=? and sg.goods_deadline >?";
        } else {
            hql = "select count(*) from BuyGoods as bg, User as u where  bg.id=u.id and u.user_university=? and bg.goods_deadline >?";
        }
        goodsCount = sellGoodsDao.getGoodsCount(hql, university);
        return goodsCount;
    }

    /**
     * 分页查询商品
     *
     * @param pageNo     第几页
     * @param maxResult  每页查询的数据量
     * @param university 所属大学
     * @return 返回分页查询的结果集SellGoods
     */
    public List<SellGoodsToUser> getPageContent(int pageNo, int maxResult, String university) {
        String hql = "select new com.schoolexchange.www.entity.SellGoodsToUser(sg.id,sg.goods_name,sg.goods_price,sg.goods_images) from SellGoods sg, User u where sg.user_id=u.id and u.user_university= '" + university + "' order by sg.create_time desc";
        List<SellGoodsToUser> list = sellGoodsDao.sellGoodsQuery(hql);
        int start = (pageNo - 1) * maxResult;
        int end = (start + maxResult - 1) <= list.size() - 1 ? (start + maxResult - 1) : (list.size() - 1);
        List<SellGoodsToUser> sellGoodsToUsers = new ArrayList<SellGoodsToUser>();
        if (0 != list.size()) {
            for (int i = start; i <= end; i++) {
                sellGoodsToUsers.add(list.get(i));
            }
        }
        return sellGoodsToUsers;
    }

    /**
     * 通过商品id获取商品信息
     *
     * @param sell_goods_id 商品id
     * @return 成功则返回商品信息 ,查找失败则返回null
     * @see com.schoolexchange.www.action.SellGoodsController
     */
    public SellGoods getSellGoodsDetailed(Integer sell_goods_id) {
        List<SellGoods> list = sellGoodsDao.getAllGoods();
        if (null != list) {
            for (SellGoods goods : list) {
                if (sell_goods_id.equals(goods.getId()))
                    return goods;
            }
        }
        return null;
    }

    @Override
    public boolean isCollectionGoods(String userName, Integer goodsId) {
        List<Object[]> list = sellGoodsDao.queryCollection(userName, goodsId);

        if (0 == list.size()) {
            return false;
        } else {
            //获取查询的数据
            /*System.out.println("====id==" + list.get(0)[0] + "==goodId=" + list.get(0)[1]);*/
            return true;
        }
    }

    @Override
    public void addCollection(Integer goodsId, Integer userId) {
        Collection collection = new Collection();
        collection.setGoods_id(goodsId);
        collection.setUser_id(userId);
        sellGoodsDao.saveCollection(collection);
    }

    @Override
    public void cancelCollection(Integer goodId, Integer userId) {
        sellGoodsDao.deleteCollection(goodId, userId);
    }

    @Override
    public List<SellGoodsToUser> searchResult(String searchContent, String university) {
        String hql = "select new com.schoolexchange.www.entity.SellGoodsToUser(sg.id,sg.goods_name,sg.goods_price,sg.goods_images) from SellGoods sg, User u where sg.user_id=u.id and u.user_university= '" + university + "' and sg.goods_name like '%" + searchContent + "%' order by sg.create_time desc";
        return sellGoodsDao.sellGoodsQuery(hql);
    }

    @Override
    public int totalPageCount(String university, int maxResult) {
        String hql = "select new com.schoolexchange.www.entity.SellGoodsToUser(sg.id,sg.goods_name,sg.goods_price,sg.goods_images) from SellGoods sg, User u where sg.user_id=u.id and u.user_university= '" + university + "' order by sg.create_time desc";
        List<SellGoodsToUser> list = sellGoodsDao.sellGoodsQuery(hql);
        int all = list.size();
        if (0 == all % maxResult) {
            return all / maxResult;
        } else {
            return all / maxResult + 1;
        }
    }

    @Override
    public List<MyCollection> getMyCollection(Integer user_id) {
        List<Object[]> list = sellGoodsDao.queryMyCollection(user_id);
        List<MyCollection> myCollections = new ArrayList<>();
        if (list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                MyCollection collection = new MyCollection((Integer) list.get(i)[0], (String) list.get(i)[1]);
                myCollections.add(collection);
            }
        }
        return myCollections;
    }

    @Override
    public List<SellGoods> getMyReleaseGoods(Integer userId) {
        List<SellGoods> myGoods = new ArrayList<>();
        List<SellGoods> list = sellGoodsDao.getAllGoods();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUser_id().equals(userId)) {
                myGoods.add(list.get(i));
            }
        }
        return myGoods;
    }


}
