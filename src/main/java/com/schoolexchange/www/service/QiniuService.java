package com.schoolexchange.www.service;

import com.qiniu.api.auth.AuthException;
import com.qiniu.api.auth.digest.Mac;
import org.apache.commons.codec.EncoderException;
import org.json.JSONException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * 七牛云存储接口
 * Created by shadow
 * on 2015/11/29.
 */
public interface QiniuService {

    /**
     * 下载路径
     *
     * @param domain 域名
     */
    void setDomain(String domain);

    /**
     * 设置存储空间
     *
     * @param bucketName 存放图片的空间名称
     */
    void setBucketName(String bucketName);

    /**
     * 设置AccessKey
     *
     * @param key AccessKey
     */
    void setAccessKey(String key);

    /**
     * 设置SecretKey
     *
     * @param key SecretKey
     */
    void setSecretKey(String key);

    /**
     * 上传文件
     *
     * @param file 要上传的file
     * @return 上传成功返回true, 否则返回false
     * @see com.schoolexchange.www.action.AccountController
     */
    boolean uploadFile(File file) throws AuthException, JSONException;

    /**
     * 下载文件
     *
     * @param filename 要下载的文件名
     * @return 返回文件的下载路径
     * @see com.schoolexchange.www.action.AccountController
     */
    String getDownloadFileUrl(String filename) throws EncoderException, AuthException;

    /**
     * 删除文件
     *
     * @param filename 要删除的文件名
     */
    void deleteFile(String filename);

    /**
     * 获取凭证
     *
     * @return 返回凭证
     */
    String getUpToken() throws AuthException, JSONException;

    Mac getMac();

    /**
     * 判断上传图片的格式
     *
     * @param myPic 传入图片
     * @return 图片为jpg或png返回true, 否则返回false
     * @see com.schoolexchange.www.action.AccountController
     */
    boolean judgePicExtension(MultipartFile myPic);

    /**
     * 判断上传图片大小
     *
     * @param myPic 传入图片
     * @return 图片超过1M返回false，否则返回true
     */
    boolean judgePicSize(MultipartFile myPic);
}
