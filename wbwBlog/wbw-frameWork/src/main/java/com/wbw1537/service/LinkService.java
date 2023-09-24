package com.wbw1537.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wbw1537.domain.ResponseResult;
import com.wbw1537.domain.entity.Link;


/**
 * 友链(Link)表服务接口
 *
 * @author makejava
 * @since 2023-09-21 17:29:29
 */
public interface LinkService extends IService<Link> {

    ResponseResult getAllLink();
}
