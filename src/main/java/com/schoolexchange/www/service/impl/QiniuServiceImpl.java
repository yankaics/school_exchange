package com.schoolexchange.www.service.impl;

import com.qiniu.api.auth.AuthException;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.config.Config;
import com.qiniu.api.io.IoApi;
import com.qiniu.api.io.PutExtra;
import com.qiniu.api.io.PutRet;
import com.qiniu.api.rs.GetPolicy;
import com.qiniu.api.rs.PutPolicy;
import com.qiniu.api.rs.RSClient;
import com.qiniu.api.rs.URLUtils;
import com.schoolexchange.www.service.QiniuService;
import org.apache.commons.codec.EncoderException;
import org.json.JSONException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * Created by shadow
 * on 2015/11/29.
 */
@Service
public class QiniuServiceImpl implements QiniuService {

    private String bucketName;
    private String domain;

    /**
     * 下载路径
     *
     * @param domain 域名
     */
    public void setDomain(String domain) {
        this.domain = domain;
    }

    /**
     * 设置存储空间
     *
     * @param bucketName 存放图片的空间名称
     */
    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    /**
     * 设置AccessKey
     *
     * @param key AccessKey
     */
    public void setAccessKey(String key) {
        Config.ACCESS_KEY = key;
    }

    /**
     * 设置SecretKey
     *
     * @param key SecretKey
     */
    public void setSecretKey(String key) {
        Config.SECRET_KEY = key;
    }

    /**
     * 上传文件
     *
     * @param file 要上传的file
     * @return 上传成功返回true, 否则返回false
     * @see com.schoolexchange.www.action.AccountController
     */
    public boolean uploadFile(File file) throws AuthException, JSONException {
        String uptoken = getUpToken();

        // 可选的上传选项，具体说明请参见使用手册。
        PutExtra extra = new PutExtra();

        // 上传文件
        PutRet ret = IoApi.putFile(uptoken, file.getName(), file.getAbsolutePath(), extra);

        if (ret.ok()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 下载文件
     *
     * @param filename 要下载的文件名
     * @return 返回文件的下载路径
     * @see com.schoolexchange.www.action.AccountController
     */
    public String getDownloadFileUrl(String filename) throws EncoderException, AuthException {
        Mac mac = getMac();
        String baseUrl = URLUtils.makeBaseUrl(domain, filename);
        GetPolicy getPolicy = new GetPolicy();
        String downloadUrl = getPolicy.makeRequest(baseUrl, mac);
        return downloadUrl;
    }

    /**
     * 删除文件
     *
     * @param filename 要删除的文件名
     */
    public void deleteFile(String filename) {
        Mac mac = getMac();
        RSClient client = new RSClient(mac);
        client.delete(domain, filename);
    }

    /**
     * 获取凭证
     *
     * @return 返回凭证
     */
    public String getUpToken() throws AuthException, JSONException {
        Mac mac = getMac();
        PutPolicy putPolicy = new PutPolicy(bucketName);
        String uptoken = putPolicy.token(mac);
        return uptoken;
    }

    public Mac getMac() {
        Mac mac = new Mac(Config.ACCESS_KEY, Config.SECRET_KEY);
        return mac;
    }

    /**
     * 判断上传图片的格式
     *
     * @param picName 传入图片
     * @return 图片为jpg或png返回true, 否则返回false
     * @see com.schoolexchange.www.action.AccountController
     */
    public boolean judgePicExtension(MultipartFile picName) {
        boolean flag = false;
        if (picName.getContentType().equals("image/jpeg") || picName.getContentType().equals("image/png")) {
            flag = true;
        }
        return flag;
    }

    /**
     * 判断上传图片大小
     *
     * @param myPic 传入图片
     * @return 图片超过1M返回false，否则返回true
     */
    public boolean judgePicSize(MultipartFile myPic) {
        boolean flag = false;
        //上限1M
        long maxUpload = 1048576;
        if (myPic.getSize() > maxUpload) {
            flag = true;
        }
        return flag;
    }
}
