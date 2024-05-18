package com.wbw1537.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.wbw1537.domain.ResponseResult;
import com.wbw1537.domain.entity.Category;
import com.wbw1537.domain.vo.ExcelCategoryVo;
import com.wbw1537.enums.AppHttpCodeEnum;
import com.wbw1537.service.CategoryService;
import com.wbw1537.utils.BeanCopyUtils;
import com.wbw1537.utils.WebUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@Api(tags = "AdminCategoryController", description = "Admin Category APIs")
@RequestMapping("/content/category")
public class AdminCategoryController {
    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "List All Categories")
    @GetMapping("/listAllCategory")
    public ResponseEntity listAllCategory() {
        return categoryService.listAllCategory();
    }

    @ApiOperation(value = "Export Categories")
    @GetMapping("/export")
    public ResponseEntity exportCategory(HttpServletResponse response) {
        return categoryService.exportCategory(response);
    }
}
