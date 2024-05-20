package com.wbw1537.controller;

import com.wbw1537.domain.ResponseResult;
import com.wbw1537.domain.dto.AddMenuDto;
import com.wbw1537.domain.vo.MenuListVo;
import com.wbw1537.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "MenuController", description = "Menu APIs")
@RequestMapping("/system/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @ApiOperation(value = "Get System Menu List")
    @GetMapping("/list")
    public ResponseEntity getSystemMenuList(String status, String menuName){
        return menuService.getSystemMenuList(status,menuName);
    }

    @ApiOperation(value = "Get System Menu List")
    @PostMapping
    public ResponseEntity addSystemMenu(@RequestBody AddMenuDto addMenuDto){
        if(addMenuDto == null){
            throw new IllegalArgumentException("Menu is required");
        }
        return menuService.addSystemMenu(addMenuDto);
    }
}
