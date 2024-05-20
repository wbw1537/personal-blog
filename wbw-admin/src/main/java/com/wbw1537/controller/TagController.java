package com.wbw1537.controller;

import com.wbw1537.domain.ResponseResult;
import com.wbw1537.domain.dto.TagDto;
import com.wbw1537.domain.vo.PageVo;
import com.wbw1537.domain.vo.TagVo;
import com.wbw1537.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "TagController", description = "Tag APIs")
@RequestMapping("/content/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @ApiOperation(value = "Search Tag and Return Tag List")
    @GetMapping("/list")
    public ResponseEntity<PageVo> searchTag(Integer pageNum, Integer pageSize, TagDto tagDto){
        if (pageNum == null || pageSize == null) {
            throw new IllegalArgumentException("PageNum and PageSize are required");
        }
        PageVo tagList = tagService.searchTag(pageNum, pageSize, tagDto);
        return new ResponseEntity<>(tagList, HttpStatus.OK);
    }

    @ApiOperation(value = "List All Tags")
    @GetMapping("/listAllTags")
    public ResponseEntity<List<TagVo>> listAllTags(){
        List<TagVo> tagVoList = tagService.listAllTags();
        return new ResponseEntity<>(tagVoList, HttpStatus.OK);
    }

    @ApiOperation(value = "Add Tag")
    @PostMapping
    public ResponseEntity<ResponseResult> addTag(@RequestBody TagDto tagDto){
        ResponseResult responseResult = tagService.addTag(tagDto);
        return new ResponseEntity<>(responseResult, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete Tag")
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseResult> deleteTag(@PathVariable Long id){
        ResponseResult responseResult = tagService.deleteTag(id);
        return new ResponseEntity<>(responseResult, HttpStatus.OK);
    }

    @ApiOperation(value = "Get Tag")
    @GetMapping("/{id}")
    public ResponseEntity<TagDto> getTag(@PathVariable Long id){
        TagDto tag = tagService.getTag(id);
        return new ResponseEntity<>(tag, HttpStatus.OK);
    }

    @ApiOperation(value = "Update Tag")
    @PutMapping
    public ResponseEntity<ResponseResult> updateTag(@RequestBody TagDto tagDto){
        ResponseResult responseResult = tagService.updateTag(tagDto);
        return new ResponseEntity<>(responseResult, HttpStatus.OK);
    }
}
