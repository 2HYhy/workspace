package com.me.hyh.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author CH-yfy
 * @date 2018/8/13
 */
@FeignClient(name = "feign-upload-server", url = "http://localhost:9008")
public interface UploadService {

    /**
     * 上传文件到远程服务器
     * @param multipartFile
     * @return
     */
    @RequestMapping(value = "/feign/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String uploadFile(@RequestPart("file") MultipartFile multipartFile);
}
