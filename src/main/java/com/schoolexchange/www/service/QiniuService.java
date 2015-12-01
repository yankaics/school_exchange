package com.schoolexchange.www.service;

import com.qiniu.api.auth.AuthException;
import com.qiniu.api.auth.digest.Mac;
import org.apache.commons.codec.EncoderException;
import org.json.JSONException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * Created by shadow on 2015/11/29.
 * Qiniu接口
 */
public interface QiniuService {

    //下载路径
    void setDomain(String domain);

    //设置存储空间
    void setBucketName(String bucketName);


    void setAccessKey(String key);

    void setSecretKey(String key);

    //上传文件
    boolean uploadFile(File file) throws AuthException, JSONException;

    //下载文件
    String getDownloadFileUrl(String filename) throws EncoderException, AuthException;

    //删除文件
    void deleteFile(String filename);

    //获取凭证
    String getUpToken() throws AuthException, JSONException;

    Mac getMac();

    //判断上传图片的格式
    boolean judgePicExtension(MultipartFile myPic);

    //判断上传图片大小
    boolean judgePicSize(MultipartFile myPic);
}
