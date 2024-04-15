package com.wbw1537.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wbw1537.constants.SystemConstants;
import com.wbw1537.domain.ResponseResult;
import com.wbw1537.domain.vo.BackGroundImgVo;
import com.wbw1537.enums.AppHttpCodeEnum;
import com.wbw1537.exception.SystemException;
import com.wbw1537.mapper.BackGroundImgMapper;
import com.wbw1537.service.BackGroundImgService;
import com.wbw1537.utils.BeanCopyUtils;
import com.wbw1537.utils.RedisCache;
import com.wbw1537.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wbw1537.domain.entity.BackGroundImg;

import java.util.ArrayList;
import java.util.List;

/**
 * 背景图表(BackGroundImg)表服务实现类
 *
 * @author makejava
 * @since 2023-11-08 11:36:27
 */
@Service("backGroundImgService")
public class BackGroundImgServiceImpl extends ServiceImpl<BackGroundImgMapper,BackGroundImg> implements BackGroundImgService {

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult getBackGroundImgList(Long id) {
//        // 根据token判断用户id是否匹配，如id为-1则说明未登录，返回默认图片（我的用户的背景图片）
//        if(!id.equals(SystemConstants.NOT_LOGIN_ID)){
//            Long idFromToken = SecurityUtils.getUserId();
//            if(!idFromToken.equals(id)){
//                throw new SystemException(AppHttpCodeEnum.NO_OPERATOR_AUTH);
//            }
//        } else {
//            id = SystemConstants.ROOT_USER_ID;
//        }
        id = 1L;
        // 根据用户id从redis中获取图片
//        LambdaQueryWrapper<BackGroundImg> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(BackGroundImg::getCreateBy,id);
//        List<BackGroundImg> backGroundImgList = list(queryWrapper);
        List<BackGroundImg> backGroundImgList = redisCache.getCacheList(SystemConstants.BACK_GROUND_IMG);
        List<BackGroundImgVo> backGroundImgVos = new ArrayList<>();
        //根据用户id查询对应的背景图片
        for(BackGroundImg backGroundImg : backGroundImgList){
            if(backGroundImg.getCreateBy().equals(id)){
                // 将url记录到集合中
                backGroundImgVos.add(BeanCopyUtils.copyBean(backGroundImg,BackGroundImgVo.class));
            }
        }
        // 获取集合中随机的某一个元素
        int randomIndex = (int) (Math.random() * backGroundImgVos.size());
        BackGroundImgVo backGroundImgVo = backGroundImgVos.get(randomIndex);
        // 返回
        return ResponseResult.okResult(backGroundImgVo.getContentUrl());
    }
}
