package com.wbw1537.controller;

import com.wbw1537.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class AdminUploadController {
    @Autowired
    private UploadService uploadService;

    @PostMapping("/upload")
    public ResponseEntity uploadImg(@RequestParam("img") MultipartFile img) {
        try {
            return uploadService.uploadImg(img);
        } catch (Exception e) {
            e.printStackTrace();
            throw  new RuntimeException("上传失败");
        }

    }
}
