package com.lhf.dajiuye.own.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 在StringUtil中创建方法getRandomImgName(String fileName) 根据上传文件名称，使用UUID生成唯一的图片名称，防止命名冲突
 */
@Slf4j
public class FileNameUtil {
    public static String getRandomImgName(String fileName){
        if(!StringUtils.hasLength(fileName)){
            // 判断是否为空或者长度是否为0
            throw new IllegalArgumentException("文件名为null或空");
        }
        int index = fileName.lastIndexOf(".");
        if(index==-1){
            throw new IllegalArgumentException("文件名没有后缀名");
        }
        // 获取文件名前缀
        String prefix = fileName.substring(0,index);
        log.info("prefix:{}",prefix);
        // 获取文件名后缀
        String suffix = fileName.substring(index);
        // 生产uuid
        String uuid = UUID.randomUUID().toString().replace("-", "");
        // 构造路径
        String path="test/"+uuid+new SimpleDateFormat("_YYYY-MM-dd HH:mm:ss-").format(new Date())+prefix+suffix;
        return path;
    }
}
