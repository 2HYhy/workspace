package com.me.hyh;


import com.me.hyh.service.RemoteService;
import com.me.hyh.service.UploadService;
import feign.Feign;
import feign.Request;
import feign.Retryer;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FeignServiceApplicationTests {

	@Test
	public void contextLoads() {
	}

	/**
	 * 作为服务消费方，上传文件至服务提供方
	 */
	@Autowired
	UploadService uploadService;
	@Test
	public void uploadFile() {
		File file = new File("/Users/CH-yfy/Desktop/BUG.txt");
		System.out.println("name====="+file.getName()+", file path = " +file.getPath()+", file absolutePath = "+file.getAbsolutePath());
		DiskFileItem disk = (DiskFileItem) new DiskFileItemFactory().createItem("file", MediaType.TEXT_PLAIN_VALUE, true, file.getName());
		try {
			InputStream input = new FileInputStream(file);
			OutputStream output = disk.getOutputStream();
			IOUtils.copy(input, output);
		} catch (Exception e) {
			throw new IllegalArgumentException("Invalid file:" + e, e);
		}
		MultipartFile multi = new CommonsMultipartFile(disk);
		//返回key的值
		System.out.println("上传文件名为:"+ uploadService.uploadFile(multi));
	}

	/**
	 * 使用Feign作为HTTP客户端调用远程HTTP服务
	 * 作为服务消费方，通过feign调用restful接口(单独使用，不用依赖Spring Cloud)
	 */
	@Test
	public void feignRemote() {
		RemoteService service = Feign.builder()
				//指定连接超时时长及响应超时时长
				.options(new Request.Options(1000, 3500))
				//指定重试策略
				.retryer(new Retryer.Default(5000, 5000, 3))
				//绑定接口与服务端地址
				.target(RemoteService.class, "http://localhost:9006");
		Map<String, String> reqMap = new HashMap<>(10);
		reqMap.put("id","1006");
		reqMap.put("code","xx060910");
		reqMap.put("level","medium");
		String token = "token";
		String reqJson = String.valueOf(reqMap);
		System.out.println(service.saveInfo("saveInfo", reqJson, token));
		System.out.println(service.getInfo());
	}

}
