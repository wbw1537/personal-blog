package com.wbw1537.controller;

import com.wbw1537.domain.ResponseResult;
import com.wbw1537.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Api(tags = "CategoryController", description = "Category Related APIs")
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @ApiOperation(value = "Get Category List")
    @GetMapping("/getCategoryList")
    public ResponseEntity getCategoryList(){
        return categoryService.getCategoryList();
    }
}
