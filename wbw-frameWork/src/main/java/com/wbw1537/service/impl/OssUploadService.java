package com.wbw1537.service.impl;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.wbw1537.domain.ResponseResult;
import com.wbw1537.domain.dto.UserInfoDto;
import com.wbw1537.enums.AppHttpCodeEnum;
import com.wbw1537.exception.SystemException;
import com.wbw1537.service.UploadService;
import com.wbw1537.utils.PathUtils;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.google.gson.Gson;

import java.io.InputStream;

@Service
@Data
@ConfigurationProperties(prefix = "oss")
public class OssUploadService implements UploadService {
    @Override
    public String uploadImg(MultipartFile img) {
        // TODO:判断文件类型或者文件大小
        // 获取原始文件名
        String originalFilename = img.getOriginalFilename();
        // 对文件名进行判断
        if ((!originalFilename.endsWith(".png") && !originalFilename.endsWith(".jpg") && !originalFilename.endsWith(".jpeg"))) {
            throw new SystemException(AppHttpCodeEnum.FILE_TYPE_ERROR);
        }
        // 如果判断通过，上传文件到oss
        String generateFileName = PathUtils.generateFilePath(originalFilename);
        return uploadOss(img,generateFileName);
    }


    private String accessKey;
    private String secretKey;
    private String bucket;
    private String domain;

    private String uploadOss(MultipartFile img, String FileName) {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.autoRegion());
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
        //...其他参数参考类注释

        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
//        String accessKey = accessKey;
//        String secretKey = secretKey;
//        String bucket = bucket;

        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = FileName;

        try {
//            byte[] uploadBytes = "hello qiniu cloud".getBytes("utf-8");
//            ByteArrayInputStream byteInputStream = new ByteArrayInputStream(uploadBytes);

            InputStream inputStream = img.getInputStream();
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);

            try {
                Response response = uploadManager.put(inputStream, key, upToken, null, null);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);
                return domain + '/' +key;
            } catch (QiniuException ex) {
                ex.printStackTrace();
                if (ex.response != null) {
                    System.err.println(ex.response);

                    try {
                        String body = ex.response.toString();
                        System.err.println(body);
                    } catch (Exception ignored) {
                    }
                }
            }
        } catch (Exception ex) {
            //ignore
        }
        return "test";
    }
}
