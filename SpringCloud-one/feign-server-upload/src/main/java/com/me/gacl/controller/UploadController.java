package com.me.gacl.controller;

import com.me.gacl.pojo.BugBO;
import com.me.gacl.pojo.BugDO;
import com.me.gacl.pojo.BugDTO;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import org.apache.commons.io.input.BOMInputStream;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CH-yfy
 * @date 2018/4/28
 * 客户端验证在service-feign模块
 */
@RestController
public class UploadController {
    /**
     * spring cloud feign 上传文件
     * 此接口充当服务提供方，接收文件，获取文件名
     * @param file
     * @return
     */
    @PostMapping(value = "/feign/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadFile(@RequestPart(value = "file")MultipartFile file) {
        return file.getName();
    }



    /**
     * 接收单个表单数据
     * body={form-data, key=file, value=BUG.txt}
     * @param file
     * @return
     */
    @RequestMapping(value ="/hyh/file1", method = RequestMethod.POST,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Object uploadFile1(@RequestPart(value = "file1")MultipartFile file) {
        String name = file.getName();
        long size = file.getSize();
        String path = file.getOriginalFilename();
        System.out.println("name="+name+", size="+size+", path="+path);
        return "name="+name+", size="+size+", path="+path;
    }

    /**
     * 接收批量表单数据
     * body={form-data, key=file1,value1=xxx key=file2,value=xxx key=file3,value=xxx}
     * @param request
     * @return
     */
    @RequestMapping(value = "/hyh/file3", method = RequestMethod.POST,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Object uploadFile3(HttpServletRequest request) {
        String arrays = "";
        MultipartHttpServletRequest multipart = (MultipartHttpServletRequest) request;
        long size = multipart.getMultiFileMap().size();
        System.out.println("size="+size);
        MultiValueMap<String, MultipartFile> map = multipart.getMultiFileMap();
        for(Map.Entry<String, List<MultipartFile>> entry : map.entrySet()) {
            List<MultipartFile> files = entry.getValue();
            for(MultipartFile tmp : files) {
                arrays += tmp.getName();
            }
        }
        return arrays;
    }

    /**
     * 通过Request读取文件输入流(txt,csv)
     * body={binary}
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/hyh/file4", method = RequestMethod.POST)
    public Object uploadFile4(HttpServletRequest request) throws IOException {
        //获取请求参数长度
        int length = request.getContentLength();
        byte[] bytes = new byte[length];
        //获取请求内容，转成数据输入流
        DataInputStream dis = new DataInputStream(request.getInputStream());
        //定义输入流读取数
        int readCount = 0;
        while(readCount < length) {
            //读取输入流，放入bytes数组，返回每次读取的数量
            int amount= dis.read(bytes,readCount,length);
            readCount = amount + readCount;
        }
        //读完之后bytes就是输入流的字节数组，将其转为字符串就能看到
        String total= new String(bytes,"UTF-8");
        return total;
    }

    /**
     * java逐行读取csv文件
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/hyh/file5", method = RequestMethod.GET)
    public String uploadFile5() throws IOException {
        CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream("/Users/CH-yfy/myproject/BUG.csv"), "UTF-8"));
        //逐行读取
        String[] array;
        while ((array = reader.readNext()) != null) {
            System.out.println("输出结果: "+array[0]+array[1]+array[2]);
        }
        reader.close();
        return "download success";

    }

    /**
     *java通过转换成bean解析CSV文件， 基于列索引的映射
     * @param request
     * @return
     */
    @RequestMapping(value = "/hyh/file6", method = RequestMethod.POST)
    public String uploadFile6(HttpServletRequest request) {
        try {
            InputStreamReader reader = new InputStreamReader(new BOMInputStream(request.getInputStream()), "UTF-8");
            ColumnPositionMappingStrategy<BugDO> mapper = new ColumnPositionMappingStrategy<>();
            mapper.setType(BugDO.class);
            CsvToBean<BugDO> csvToBean = new CsvToBean<>();
            List<BugDO> list = csvToBean.parse(mapper, reader);
            for (BugDO bug : list) {
                System.out.println("content6 = " + bug);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "upload success";
    }

    /**
     * java解析CSV文件，基于列名的映射(csv的列头必须与bean的属性一致，但可通过@CsvBindByName注解进行映射)
     * @param request
     * @return
     */
    @RequestMapping(value = "/hyh/file7", method = RequestMethod.POST)
    public String uploadFile7(HttpServletRequest request) {
        try {
            InputStreamReader reader = new InputStreamReader(new BOMInputStream(request.getInputStream()), "UTF-8");
            HeaderColumnNameMappingStrategy<BugDTO> mapper = new HeaderColumnNameMappingStrategy<>();
            mapper.setType(BugDTO.class);
            CsvToBean<BugDTO> csvToBean = new CsvToBean<>();
            List<BugDTO> list = csvToBean.parse(mapper, reader);
            for (BugDTO bug : list) {
                System.out.println("content7 = " + bug);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "upload success";
    }

    /**
     * java解析CSV文件，基于列名的转换映射(csv的列头可与bean的属性不一致,通过map进行映射)
     * @param request
     * @return
     */
    @RequestMapping(value = "/hyh/file8", method = RequestMethod.POST)
    public String uploadFile8(HttpServletRequest request) throws FileNotFoundException, UnsupportedEncodingException {
        try {
            //CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream("/Users/CH-yfy/myproject/BUG.csv"),"UTF-8"));
            InputStreamReader reader = new InputStreamReader(new BOMInputStream(request.getInputStream()), "UTF-8");
            HeaderColumnNameTranslateMappingStrategy<BugBO> mapper = new HeaderColumnNameTranslateMappingStrategy<>();
            mapper.setType(BugBO.class);
            Map<String,String> columnMapping = new HashMap<>(100);
            //csv的对应bean的
            columnMapping.put("缺陷标题", "title");
            columnMapping.put("编号", "itemId");
            columnMapping.put("项目号", "projectId");
            mapper.setColumnMapping(columnMapping);
            CsvToBean<BugBO> csvToBean = new CsvToBean<>();
            List<BugBO> list = csvToBean.parse(mapper, reader);
            for(BugBO bug : list){
                System.out.println("content8 = " + bug);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "upload success";
    }
}
