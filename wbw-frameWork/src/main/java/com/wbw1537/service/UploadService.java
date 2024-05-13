package com.wbw1537.service;

import com.wbw1537.domain.ResponseResult;
import com.wbw1537.domain.dto.UserInfoDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService{
    ResponseEntity uploadImg(MultipartFile img);
}
