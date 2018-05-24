package com.me.gacl;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author CH-yfy
 * @date 2018/4/18
 * feign中使用断路器，只要实现调用服务的接口即可
 */
@Component
public class HystrixServiceImpl implements FeignService, UploadService {

    @Override
    public String sayHello() {
        return "Sorry, service error, Feign--sayHello";
    }

    @Override
    public String getUser(String name) {
        return "Sorry, service error, Feign--getUser";
    }

    @Override
    public String operateUploadFile(@RequestPart(value = "file") MultipartFile file) {
        return "Sorry, service-error, Feign-operateUploadFile";
    }
}
