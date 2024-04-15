package com.wbw1537.runner;

import com.wbw1537.constants.SystemConstants;
import com.wbw1537.domain.entity.BackGroundImg;
import com.wbw1537.domain.vo.BackGroundImgVo;
import com.wbw1537.mapper.BackGroundImgMapper;
import com.wbw1537.service.BackGroundImgService;
import com.wbw1537.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class BackGroundImgRunner implements CommandLineRunner {
    @Autowired
    private BackGroundImgMapper backGroundImgMapper;

    @Autowired
    private RedisCache redisCache;
    @Override
    public void run(String... args) throws Exception {
        // 查询背景图片url信息
        List<BackGroundImg> backGroundImgList = backGroundImgMapper.selectList(null);

        // 存储到redis中
        redisCache.setCacheList(SystemConstants.BACK_GROUND_IMG,backGroundImgList);

    }
}
