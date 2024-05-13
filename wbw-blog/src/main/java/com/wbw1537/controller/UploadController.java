package com.wbw1537.controller;

import com.wbw1537.domain.ResponseResult;
import com.wbw1537.domain.dto.UserInfoDto;
import com.wbw1537.domain.entity.User;
import com.wbw1537.enums.AppHttpCodeEnum;
import com.wbw1537.exception.SystemException;
import com.wbw1537.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {

    @Autowired
    private UploadService uploadService;
    @PostMapping("/upload")
    public ResponseEntity uploadImg(@RequestParam("img") MultipartFile img){
        try {
            if (img == null) {
                throw new IllegalArgumentException("Image is required");
            }
            return uploadService.uploadImg(img);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException(AppHttpCodeEnum.UPLOAD_ERROR);
        }
    }

}
