package com.me.gacl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.io.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author CH-yfy
 * @date 2018/5/2
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class UploadFileTest {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception{
        mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    /**
     * 测试上传表单数据
     * @throws Exception
     */
    @Test
    public void func1() throws Exception {
        byte[] bytes = getBytes("/Users/CH-yfy/Desktop/BUG.txt");
        mockMvc.perform(fileUpload("/hyh/file1")
                .file("file1", bytes)
                .accept(MediaType.MULTIPART_FORM_DATA_VALUE).characterEncoding("UTF-8"))
                .andExpect(status().isOk());
    }
    //文件转byte
    private byte[] getBytes(String filePath){
        byte[] buffer = null;
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    /**
     * 测试上传表单数据
     */
    @Test
    public void func() throws Exception {
        this.mockMvc.perform(fileUpload("/hyh/file1").file(
                new MockMultipartFile("file1", new FileInputStream("/Users/CH-yfy/Desktop/BUG.txt"))));
    }

    /**
     * 测试上传二进制文件
     * @throws Exception
     */
    @Test
    public void testCsv() throws Exception {
        String stringFile = "123, 缺陷标题,编号,项目号\n" +
                "123,【1运营平台】【掮客账户管理】查看掮客账户明细时，‘掮客圈回工单’内容异位,999,1011\n" +
                "123,【2运营平台】【掮客账户管理】查看掮客账户明细时，‘掮客圈回工单’内容异位,888,1011";
        mockMvc.perform(post("/hyh/file6").content(stringFile))
                .andExpect(status().isOk())
                .andExpect(content().string("upload success"));
    }

}
