package com.schoolexchange.www.service;

import com.schoolexchange.www.entity.MyCollection;
import com.schoolexchange.www.entity.SellGoods;
import com.schoolexchange.www.entity.SellGoodsToUser;
import com.schoolexchange.www.entity.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    /**
     * 分页查询商品
     *
     * @param pageNo     第几页
     * @param maxResult  每页查询的数据量
     * @param university 所属大学
     * @return 返回分页查询的结果集SellGoods
     */
    List<SellGoodsToUser> getPageContent(int pageNo, int maxResult, String university);

    /**
     * 通过商品id获取商品信息
     *
     * @param sell_goods_id 商品id
     * @return 成功则返回商品信息 ,查找失败则返回null
     * @see com.schoolexchange.www.action.SellGoodsController
     */
    SellGoods getSellGoodsDetailed(Integer sell_goods_id);

    /**
     * 通过用户名和商品id判断该商品是否已经收藏
     *
     * @param userName 用户名
     * @param goodsId  商品id
     * @return true 已经收藏，否则还没收藏
     */
    boolean isCollectionGoods(String userName, Integer goodsId);

    /**
     * 添加收藏
     *
     * @param goodsId 商品id
     * @param userId  用户id
     */
    void addCollection(Integer goodsId, Integer userId);

    /**
     * 取消收藏
     *
     * @param goodId 商品id
     * @param userId 用户id
     */
    void cancelCollection(Integer goodId, Integer userId);

    /**
     * 获取搜索结果
     *
     * @param searchContent 搜索内容
     * @param university    所属大学
     * @return 返回分页查询的结果集SellGoods
     */
    List<SellGoodsToUser> searchResult(String searchContent, String university);

    /**
     * 获取总页数
     *
     * @param university 大学
     * @param maxResult  每页显示的页数
     * @return 当前大学的商品总页数
     */
    int totalPageCount(String university, int maxResult);

    /**
     * 获取我的收藏
     *
     * @param user_id 用户id
     */
    List<MyCollection> getMyCollection(Integer user_id);

    /**
     * 返回我发布的商品集合
     *
     * @param userId 用户id
     * @return 商品集合
     */
    List<SellGoods> getMyReleaseGoods(Integer userId);

    /**
     * 根据商品id删除商品
     *
     * @param goodsId 商品id
     */
    void deleteMyGoodsById(Integer goodsId);
}
