package com.wbw1537.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wbw1537.domain.ResponseResult;


/**
 * 背景图表(BackGroundImg)表服务接口
 *
 * @author makejava
 * @since 2023-11-08 11:36:27
 */
public interface BackGroundImgService extends IService<com.wbw1537.domain.entity.BackGroundImg> {

    ResponseResult getBackGroundImgList(Long id);
}
