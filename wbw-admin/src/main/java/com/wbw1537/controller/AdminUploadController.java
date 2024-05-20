package com.wbw1537.controller;

import com.wbw1537.enums.AppHttpCodeEnum;
import com.wbw1537.exception.SystemException;
import com.wbw1537.service.UploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = "AdminUploadController", description = "Admin Upload APIs")
@RestController
public class AdminUploadController {
    @Autowired
    private UploadService uploadService;

    @ApiOperation(value = "Upload Image")
    @PostMapping("/upload")
    public ResponseEntity<String> uploadImg(@RequestParam("img") MultipartFile img) {
        String url = uploadService.uploadImg(img);
        return new ResponseEntity<>(url, HttpStatus.OK);
    }
}
