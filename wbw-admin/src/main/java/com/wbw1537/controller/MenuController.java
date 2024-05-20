package com.wbw1537.controller;

import com.wbw1537.domain.ResponseResult;
import com.wbw1537.domain.dto.AddMenuDto;
import com.wbw1537.domain.vo.MenuListVo;
import com.wbw1537.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "MenuController", description = "Menu APIs")
@RequestMapping("/system/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @ApiOperation(value = "Get System Menu List")
    @GetMapping("/list")
    public ResponseEntity<List<MenuListVo>> searchSystemMenuList(String status, String menuName){
        List<MenuListVo> menuListVos = menuService.searchSystemMenuList(status,menuName);
        return new ResponseEntity<>(menuListVos, HttpStatus.OK);
    }

    @ApiOperation(value = "Get System Menu List")
    @PostMapping
    public ResponseEntity<ResponseResult> addSystemMenu(@RequestBody AddMenuDto addMenuDto){
        ResponseResult result = menuService.addSystemMenu(addMenuDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
