package com.wbw1537.controller;

import com.wbw1537.domain.ResponseResult;
import com.wbw1537.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/link")
public class LinkController {

    @Autowired
    private LinkService linkService;
    @GetMapping("/getAllLink")
    public ResponseEntity getAllLink(){
        return linkService.getAllLink();
    }
}
