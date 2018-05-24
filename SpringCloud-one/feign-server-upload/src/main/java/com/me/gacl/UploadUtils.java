package com.me.gacl;

import com.me.gacl.pojo.UploadInfo;
import com.me.gacl.pojo.UploadMeta;
import com.me.gacl.service.UploadService;
import com.me.gacl.util.FeignEncoder;
import com.me.gacl.util.MemoryFile;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author CH-yfy
 * @date 2018/5/3
 */
public class UploadUtils {

    public static void main(String [] args) {
        Feign.Builder encoder = Feign.builder()
                .decoder(new JacksonDecoder())
                .encoder(new FeignEncoder());
        UploadService service = encoder.target(UploadService.class, "http://localhost:9005");
        //文件存在内存中
        MultipartFile file1 = new MemoryFile("fileOne.tmp", new byte[]{65, 66, 67, 68});
        MultipartFile file2 = new MemoryFile("fileTwo.tmp", new byte[]{69, 70, 71, 72, 73, 68});

        UploadInfo test = service.test(new UploadMeta("thursday"));
        System.out.println("test: " + test);

        UploadInfo upload1 = service.uploadOne("clientFolder", file1, new UploadMeta("thursday"));
        System.out.println("Upload-one test: " + upload1);

        UploadInfo upload2= service.uploadTwo("clientFolder", file1);
        System.out.println("Upload-two test: " + upload2);

        List<UploadInfo> uploads = service.uploadThree("clientFolder", new MultipartFile[]{file1, file2}, new UploadMeta("thursday"));
        int i = 0;
        for (UploadInfo uploadInfo : uploads) {
            System.out.println("Upload-three test[" + (i++) + "]: " + uploadInfo);
        }
    }
}
