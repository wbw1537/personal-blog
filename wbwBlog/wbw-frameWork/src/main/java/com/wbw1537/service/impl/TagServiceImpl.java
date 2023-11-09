package com.wbw1537.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wbw1537.domain.entity.Tag;
import com.wbw1537.mapper.TagMapper;
import com.wbw1537.service.TagService;
import org.springframework.stereotype.Service;

/**
 * 标签(Tag)表服务实现类
 *
 * @author makejava
 * @since 2023-11-09 11:11:36
 */
@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

}
