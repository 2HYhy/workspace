package com.me.gacl.controller;

import com.me.gacl.pojo.UploadInfo;
import com.me.gacl.pojo.UploadMeta;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.MultipartAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author CH-yfy
 * @date 2018/5/2
 */

@EnableAutoConfiguration
@Import(value = MultipartAutoConfiguration.class)
@RestController
public class TestController {

    private int i = 0;

    @RequestMapping(path = "/test", method = POST, consumes = "application/json")
    public HttpEntity<UploadInfo> test(@RequestBody UploadMeta meta) {
        return ResponseEntity.ok(new UploadInfo(i++, 0, "test1.tmp"));
    }

    @RequestMapping(path = "/uploadOne/{folder}", method = POST)
    public HttpEntity<UploadInfo> uploadOne(@PathVariable String folder, @RequestParam MultipartFile file, @RequestParam UploadMeta meta) {
        return ResponseEntity.ok(new UploadInfo(i++, file.getSize(), folder + "/" + file.getOriginalFilename()));
    }

    @RequestMapping(path = "/uploadTwo/{folder}", method = POST)
    public HttpEntity<UploadInfo> uploadTwo(@PathVariable String folder, @RequestPart MultipartFile file) {
        return ResponseEntity.ok(new UploadInfo(i++, file.getSize(), folder + "/" + file.getOriginalFilename()));
    }

    @RequestMapping(path = "/uploadThree/{folder}", method = POST)
    public HttpEntity<List<UploadInfo>> uploadThree(@PathVariable String folder, @RequestPart MultipartFile[] files, @RequestPart UploadMeta meta) {
        List<UploadInfo> response = new ArrayList<>();
        for (MultipartFile file : files) {
            response.add(new UploadInfo(i++, file.getSize(), folder + "/" + file.getOriginalFilename()));
        }
        return ResponseEntity.ok(response);
    }
}
