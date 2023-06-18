package com.lhf.dajiuye.own.service.impl;

import com.google.gson.Gson;
import com.lhf.dajiuye.own.config.CloudStorageConfig;
import com.lhf.dajiuye.own.service.UpLoadImgService;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class UpLoadImgServiceImpl implements UpLoadImgService {

    // 七牛文件上传管理器
    private UploadManager uploadManager;
    // 自定义的七牛的相关配置类
    private CloudStorageConfig config;
    // 七牛认证管理
    private Auth auth;
    // 通过命令空间生成的上传凭证
    private String token;

    public UpLoadImgServiceImpl(CloudStorageConfig config){ // 这里的参数应该会自动从容器中找
        this.config=config;
        init();
    }

    private void init() {
        // 构建一个带指定对象的配置类，注意这里的zone2()根据自己的存储空间确定
        uploadManager=new UploadManager(new Configuration(Zone.zone2()));
        // 通过ak和sk获取认证
        auth=Auth.create(config.getAccesskey(),config.getSecretKey());
        // 通过命令空间生成的上传token
        token=auth.uploadToken(config.getBucketName());
    }

    /**
     * 上传文件到七牛
     * @param file 文件对应的流对象
     * @param key 文件名（唯一key）
     * @return
     */
    @Override
    public String upLoadQNImg(InputStream file, String key) {
        try {
            Response res = uploadManager.put(file, key, token, null, null);
            if(!res.isOK()){
                throw new RuntimeException("上传七牛出错："+res.toString());
            }
            // 默认上传接口回复对象 属性有：文件hash值和文件名key
            DefaultPutRet putRet = new Gson().fromJson(res.bodyString(), DefaultPutRet.class);
            // 得到外链地址
            String path=config.getDomain()+"/"+putRet.key;
            // 返回
            return path;
        } catch (QiniuException e) {
            e.printStackTrace();
        }
        return "";
    }
}
