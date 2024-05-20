package com.wbw1537.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wbw1537.domain.ResponseResult;
import com.wbw1537.domain.dto.TagDto;
import com.wbw1537.domain.entity.Tag;
import com.wbw1537.domain.vo.PageVo;
import com.wbw1537.domain.vo.TagVo;
import com.wbw1537.enums.AppHttpCodeEnum;
import com.wbw1537.exception.SystemException;
import com.wbw1537.mapper.TagMapper;
import com.wbw1537.service.TagService;
import com.wbw1537.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 标签(Tag)表服务实现类
 *
 * @author makejava
 * @since 2023-11-09 11:11:36
 */
@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Override
    public PageVo searchTag(Integer pageNum, Integer pageSize, TagDto tagDto) {
        // 分页查询
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        // 条件查询
        queryWrapper.like(StringUtils.hasText(tagDto.getName()), Tag::getName, tagDto.getName());
        queryWrapper.like(StringUtils.hasText(tagDto.getRemark()), Tag::getRemark, tagDto.getRemark());
        // 分页查询
        Page<Tag> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page, queryWrapper);
        return new PageVo(page.getRecords(),page.getTotal());
    }
    @Override
    public List<TagVo> listAllTags() {
        // 查询所有的标签
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Tag::getId, Tag::getName);
        List<Tag> tags = list(queryWrapper);
        return BeanCopyUtils.copyBeanList(tags, TagVo.class);
    }
    @Override
    public ResponseResult addTag(TagDto tagDto) {
        // 判断tagListDto中的数据是否为空
        if(!StringUtils.hasText(tagDto.getName())){
            throw new SystemException(AppHttpCodeEnum.TAG_NAME_NULL);
        } else if(!StringUtils.hasText(tagDto.getRemark())){
            throw new SystemException(AppHttpCodeEnum.TAG_REMARK_NULL);
        }
        // 封装数据
        Tag tag = BeanCopyUtils.copyBean(tagDto, Tag.class);
        save(tag);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteTag(Long id) {
        // 判断id是否为空
        if(id == null){
            throw new SystemException(AppHttpCodeEnum.SYSTEM_ERROR);
        }
        // 删除对应id的字段（逻辑删除）
        TagMapper tagMapper = getBaseMapper();
        tagMapper.deleteById(id);
        return ResponseResult.okResult();
    }

    @Override
    public TagDto getTag(Long id) {
        // 判断id是否为空
        if(id == null){
            throw new SystemException(AppHttpCodeEnum.SYSTEM_ERROR);
        }
        // 根据id查询对应的数据
        Tag tag = getById(id);
        // 封装数据返回
        return BeanCopyUtils.copyBean(tag, TagDto.class);
    }

    @Override
    public ResponseResult updateTag(TagDto tagDto) {
        // 判断tagListDto中的数据是否为空
        if(!StringUtils.hasText(tagDto.getName())){
            throw new SystemException(AppHttpCodeEnum.TAG_NAME_NULL);
        } else if(!StringUtils.hasText(tagDto.getRemark())){
            throw new SystemException(AppHttpCodeEnum.TAG_REMARK_NULL);
        }
        // 封装数据
        Tag tag = BeanCopyUtils.copyBean(tagDto, Tag.class);
        updateById(tag);
        return ResponseResult.okResult();
    }
}
