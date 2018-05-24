package com.me.gacl;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author CH-yfy
 * @date 2018/4/28
 * value=服务提供方的名字
 */
@FeignClient(name = "feign-upload-server", configuration = MultiPartConfig.class, fallback = HystrixServiceImpl.class)
public interface UploadService {

    /**
     * 充当服务消费方，发送文件
     * @param file
     * @return
     */
    @PostMapping(value = "/feign/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String operateUploadFile(@RequestPart(value = "file")MultipartFile file);
}
