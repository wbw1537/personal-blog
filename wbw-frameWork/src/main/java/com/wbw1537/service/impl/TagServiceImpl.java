package com.wbw1537.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wbw1537.domain.ResponseResult;
import com.wbw1537.domain.dto.TagListDto;
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
    public ResponseResult<PageVo> getTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto) {
        // 判断pageNum和pageSize是否为空
        if(pageNum == null || pageSize == null){
            throw new SystemException(AppHttpCodeEnum.PARAM_INVALID);
        }
        // 分页查询
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        // 条件查询
        queryWrapper.like(StringUtils.hasText(tagListDto.getName()), Tag::getName, tagListDto.getName());
        queryWrapper.like(StringUtils.hasText(tagListDto.getRemark()), Tag::getRemark, tagListDto.getRemark());
        // 分页查询
        Page<Tag> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page, queryWrapper);
        // 封装数据返回
        PageVo pageVo = new PageVo(page.getRecords(),page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult addTag(TagListDto tagListDto) {
        // 判断tagListDto中的数据是否为空
        if(!StringUtils.hasText(tagListDto.getName())){
            throw new SystemException(AppHttpCodeEnum.TAG_NAME_NULL);
        } else if(!StringUtils.hasText(tagListDto.getRemark())){
            throw new SystemException(AppHttpCodeEnum.TAG_REMARK_NULL);
        }
        // 封装数据
        Tag tag = BeanCopyUtils.copyBean(tagListDto, Tag.class);
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
    public ResponseResult getTag(Long id) {
        // 判断id是否为空
        if(id == null){
            throw new SystemException(AppHttpCodeEnum.SYSTEM_ERROR);
        }
        // 根据id查询对应的数据
        Tag tag = getById(id);
        // 封装数据返回
        TagListDto tagDto = BeanCopyUtils.copyBean(tag, TagListDto.class);
        return ResponseResult.okResult(tagDto);
    }

    @Override
    public ResponseResult updateTag(TagListDto tagListDto) {
        // 判断tagListDto中的数据是否为空
        if(!StringUtils.hasText(tagListDto.getName())){
            throw new SystemException(AppHttpCodeEnum.TAG_NAME_NULL);
        } else if(!StringUtils.hasText(tagListDto.getRemark())){
            throw new SystemException(AppHttpCodeEnum.TAG_REMARK_NULL);
        }
        // 封装数据
        Tag tag = BeanCopyUtils.copyBean(tagListDto, Tag.class);
        updateById(tag);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult<TagVo> listAllTag() {
        // 查询所有的标签
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Tag::getId, Tag::getName);
        List<Tag> tags = list(queryWrapper);
        List<TagVo> tagVos = BeanCopyUtils.copyBeanList(tags, TagVo.class);
        return ResponseResult.okResult(tagVos);
    }
}
