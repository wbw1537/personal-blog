package com.wbw1537.job;

import com.wbw1537.constants.SystemConstants;
import com.wbw1537.domain.entity.BackGroundImg;
import com.wbw1537.mapper.BackGroundImgMapper;
import com.wbw1537.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

// 当背景图片被更新时，更新redis中的背景图片信息
@Component
public class UpdateNewBackGroundImgJob {
    @Autowired
    private BackGroundImgMapper backGroundImgMapper;

    @Autowired
    private RedisCache redisCache;
    public void updateNewBackGroundImg(){
        // 查询背景图片url信息
        List<BackGroundImg> backGroundImgList = backGroundImgMapper.selectList(null);

        // 存储到redis中
        redisCache.setCacheList(SystemConstants.BACK_GROUND_IMG,backGroundImgList);
    }
}
