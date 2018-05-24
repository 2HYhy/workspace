package com.me.gacl.service;

import com.me.gacl.pojo.UploadInfo;
import com.me.gacl.pojo.UploadMeta;
import feign.Param;
import feign.RequestLine;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author CH-yfy
 * @date 2018/5/3
 */
public interface UploadService {

    @RequestLine("POST /test")
    UploadInfo test(UploadMeta meta);

    @RequestLine("POST /uploadOne/{folder}")
    UploadInfo uploadOne(@Param("folder") String folder,
                         @Param("file") MultipartFile file,
                         @Param("meta") UploadMeta meta);


    @RequestLine("POST /uploadTwo/{folder}")
    UploadInfo uploadTwo(@Param("folder") String folder,
                         @Param("file") MultipartFile file);

    @RequestLine("POST /uploadThree/{folder}")
    List<UploadInfo> uploadThree(@Param("folder") String folder,
                                 @Param("files") MultipartFile[] files,
                                 @Param("meta") UploadMeta meta);
}
