package com.wbw1537.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wbw1537.domain.ResponseResult;
import com.wbw1537.domain.dto.TagListDto;
import com.wbw1537.domain.entity.Tag;
import com.wbw1537.domain.vo.PageVo;
import com.wbw1537.domain.vo.TagVo;


/**
 * 标签(Tag)表服务接口
 *
 * @author makejava
 * @since 2023-11-09 11:11:36
 */
public interface TagService extends IService<Tag> {

    ResponseResult<PageVo> getTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto);

    ResponseResult addTag(TagListDto tagListDto);

    ResponseResult deleteTag(Long id);

    ResponseResult getTag(Long id);

    ResponseResult updateTag(TagListDto tagListDto);

    ResponseResult<TagVo> listAllTag();

}
