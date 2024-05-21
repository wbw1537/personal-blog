package com.wbw1537.controller;

import com.wbw1537.domain.ResponseResult;
import com.wbw1537.domain.dto.UserInfoDto;
import com.wbw1537.domain.entity.User;
import com.wbw1537.enums.AppHttpCodeEnum;
import com.wbw1537.exception.SystemException;
import com.wbw1537.service.UploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = "UploadController", description = "Upload APIs")
@RestController
public class UploadController {
    @Autowired
    private UploadService uploadService;

    @ApiOperation(value = "Upload Image")
    @PostMapping("/upload")
    public ResponseEntity<String> uploadImg(@RequestParam("img") MultipartFile img) throws SystemException{
        if (img == null) {
            throw new IllegalArgumentException("Image is required");
        }
        String url = uploadService.uploadImg(img);
        return new ResponseEntity<>(url, HttpStatus.OK);
    }

}
