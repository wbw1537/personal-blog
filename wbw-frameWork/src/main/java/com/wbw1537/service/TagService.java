package com.wbw1537.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wbw1537.domain.ResponseResult;
import com.wbw1537.domain.dto.TagDto;
import com.wbw1537.domain.entity.Tag;
import com.wbw1537.domain.vo.PageVo;
import com.wbw1537.domain.vo.TagVo;

import java.util.List;


/**
 * 标签(Tag)表服务接口
 *
 * @author makejava
 * @since 2023-11-09 11:11:36
 */
public interface TagService extends IService<Tag> {
    PageVo searchTag(Integer pageNum, Integer pageSize, TagDto tagDto);
    ResponseResult addTag(TagDto tagDto);
    ResponseResult deleteTag(Long id);
    TagDto getTag(Long id);
    ResponseResult updateTag(TagDto tagDto);
    List<TagVo> listAllTags();
}
