package com.lhf.dajiuye.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lhf.dajiuye.seckill.entity.SecLessonEntity;

import java.util.List;

public interface SecKillService extends IService<SecLessonEntity> {
    List<SecLessonEntity> curSec();
    String secItem(Long userId,Long skuId,String secToken);
    void updateLatest3DayLesson();
}
