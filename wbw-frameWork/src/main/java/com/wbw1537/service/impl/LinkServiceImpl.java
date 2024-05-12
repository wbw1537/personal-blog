package com.wbw1537.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wbw1537.constants.SystemConstants;
import com.wbw1537.domain.ResponseResult;
import com.wbw1537.domain.entity.Link;
import com.wbw1537.domain.vo.LinkVo;
import com.wbw1537.mapper.LinkMapper;
import com.wbw1537.service.LinkService;
import com.wbw1537.utils.BeanCopyUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 友链(Link)表服务实现类
 *
 * @author makejava
 * @since 2023-09-21 17:30:19
 */
@Service("linkService")
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

    @Override
    public ResponseEntity getAllLink() {
        //查询所以审核通过的链接
        LambdaQueryWrapper<Link> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Link::getStatus, SystemConstants.LINK_STATUS_NORMAL);
        List<Link> links = list(lambdaQueryWrapper);
        //转换vo
        List<LinkVo> linkVo = BeanCopyUtils.copyBeanList(links, LinkVo.class);
        //封装返回
        return new ResponseEntity<>(linkVo, HttpStatus.OK);
    }
}
