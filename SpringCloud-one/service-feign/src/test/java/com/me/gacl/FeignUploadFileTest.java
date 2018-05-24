package com.me.gacl;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author CH-yfy
 * @date 2018/4/28
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class FeignUploadFileTest {

    @Autowired
    private UploadService uploadService;

    @Test
    public void testUpload() {
        File file = new File("/Users/CH-yfy/Desktop/BUG.txt");
        System.out.println("name====="+file.getName());
        System.out.println("file path = " +file.getPath()+", file absolutePath = "+file.getAbsolutePath());
        DiskFileItem disk = (DiskFileItem) new DiskFileItemFactory().createItem("file", MediaType.TEXT_PLAIN_VALUE, true, file.getName());
        try (InputStream input = new FileInputStream(file); OutputStream output = disk.getOutputStream()) {
            IOUtils.copy(input, output);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid file:" + e, e);
        }
        MultipartFile multi = new CommonsMultipartFile(disk);
        //返回的值为文件名为file
        System.out.println("测试文件上传的结果:"+ uploadService.operateUploadFile(multi));
    }
}
