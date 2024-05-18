package com.wbw1537.controller;

import com.wbw1537.domain.ResponseResult;
import com.wbw1537.service.LinkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "LinkController", description = "Link Related APIs")
@RequestMapping("/link")
public class LinkController {

    @Autowired
    private LinkService linkService;
    @ApiOperation(value = "Get All Link")
    @GetMapping("/getAllLink")
    public ResponseEntity getAllLink(){
        return linkService.getAllLink();
    }
}
