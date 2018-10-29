package com.me.hyh;

import com.me.hyh.pojo.BugBO;
import com.me.hyh.pojo.BugDO;
import com.me.hyh.pojo.BugDTO;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import org.apache.commons.io.input.BOMInputStream;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CH-yfy
 * @date 2018/8/13
 * csv文件格式：每一列之间用逗号隔开且不能有空格; 最后一列后面不要逗号，也不能有空格
 */
@RestController
public class UploadController {

    /**
     * 作为服务提供方，接收文件，返回文件名
     * @param multipartFile
     * @return
     */
    @RequestMapping(value = "/feign/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadFile(@RequestPart("file") MultipartFile multipartFile) {
        System.out.println("-----enter uploadFile-----");
        return multipartFile.getName();
    }

    /**
     * 接收单个表单数据
     * body={form-data, key=file, value=BUG.txt}
     * @param file
     * @return
     */
    @RequestMapping(value = "/single/file", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String singleFile(@RequestPart("file") MultipartFile file) {
        String path = file.getOriginalFilename();
        Long size = file.getSize();
        String type = file.getContentType();
        return "type="+type+", size="+size+", path="+path;
    }

    /**
     * 批量接收表单数据
     * body={form-data, key1=file1, value=BUG.txt,key2=file2, value=BUG.txt}
     * @param request
     * @return
     */
    @RequestMapping(value = "/batch/file", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String multiFile(HttpServletRequest request) {
        MultipartHttpServletRequest multipart = (MultipartHttpServletRequest) request;
        MultiValueMap<String, MultipartFile> map = multipart.getMultiFileMap();
        Integer size = multipart.getMultiFileMap().size();
        System.out.println("size = " + size);
        String arrays = "";
        for(Map.Entry<String, List<MultipartFile>> entry : map.entrySet()) {
            List<MultipartFile> files = entry.getValue();
            for(MultipartFile tmp : files) {
                //返回文件名，getName返回的是key值
                arrays += tmp.getOriginalFilename();
            }
        }
        return arrays;
    }

    /**
     * 通过Request读取文件输入流(txt,csv)
     * body={binary}
     * @param request
     * @return
     */
    @RequestMapping(value = "/request/file", method = RequestMethod.POST)
    public String requestFile(HttpServletRequest request) throws IOException {
        //获取请求参数长度
        int length = request.getContentLength();
        byte[] bytes = new byte[length];
        //获取请求内容，转成数据输入流
        DataInputStream dis = new DataInputStream(request.getInputStream());
        //定义输入流读取数
        int readCount = 0;
        while(readCount < length) {
            //读取输入流，放入bytes数组，返回每次读取的数量
            int amount = dis.read(bytes, readCount, length);
            readCount = amount + readCount;
        }
        //读完之后bytes就是输入流的字节数组，将其转为字符串就能看到文件内容
        return new String(bytes, "UTF-8");
    }

    /**
     * Java逐行读取CSV文件内容
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/java/file", method = RequestMethod.GET)
    public String javaFile() throws IOException {
        CSVReader reader = new CSVReader(new InputStreamReader(
                new FileInputStream("/Users/CH-yfy/Desktop/BUG.csv"), "UTF-8"));
        //逐行读取
        String[] array;
        while ((array = reader.readNext()) != null) {
            System.out.println("输出结果: " + Arrays.toString(array));
        }
        reader.close();
        return "read csv file success";
    }

    /**
     * Java通过转换成bean解析CSV文件,基于列索引的映射
     * body={binary}
     * @param request
     * @return
     */
    @RequestMapping(value = "/column/file", method = RequestMethod.POST)
    public String columnFile(HttpServletRequest request) {
        try {
            InputStreamReader reader = new InputStreamReader(new BOMInputStream(request.getInputStream()), "UTF-8");
            ColumnPositionMappingStrategy<BugDO> mapper = new ColumnPositionMappingStrategy<>();
            mapper.setType(BugDO.class);
            CsvToBean<BugDO> csvToBean = new CsvToBean<>();
            List<BugDO> list = csvToBean.parse(mapper, reader);
            for (BugDO bug : list) {
                System.out.println("bug = " + bug);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "read csv file success";
    }

    /**
     * java通过转换成bean解析CSV文件，基于列名的映射(csv的列头必须与bean的属性一致，但可通过@CsvBindByName注解进行映射)
     * body={binary}
     * @param request
     * @return
     */
    @RequestMapping(value = "/header/file", method = RequestMethod.POST)
    public String headerFile(HttpServletRequest request) {
        try {
            InputStreamReader reader = new InputStreamReader(new BOMInputStream(request.getInputStream()), "UTF-8");
            HeaderColumnNameMappingStrategy<BugDTO> mapper = new HeaderColumnNameMappingStrategy<>();
            mapper.setType(BugDTO.class);
            CsvToBean<BugDTO> csvToBean = new CsvToBean<>();
            List<BugDTO> list = csvToBean.parse(mapper, reader);
            for (BugDTO bug : list) {
                System.out.println("bug = " + bug);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "read csv file success";
    }

    /**
     * Java通过转换成bean解析CSV文件，基于列名的转换映射(csv的列头可与bean的属性不一致,通过map进行映射)
     * body={binary}
     * @param request
     * @return
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/transfer/file", method = RequestMethod.POST)
    public String transferFile(HttpServletRequest request) throws FileNotFoundException, UnsupportedEncodingException {
        try {
            InputStreamReader reader = new InputStreamReader(new BOMInputStream(request.getInputStream()), "UTF-8");
            HeaderColumnNameTranslateMappingStrategy<BugBO> mapper = new HeaderColumnNameTranslateMappingStrategy<>();
            mapper.setType(BugBO.class);
            Map<String,String> columnMapping = new HashMap<>(100);
            //csv的对应bean
            columnMapping.put("编号", "id");
            columnMapping.put("标题", "content");
            columnMapping.put("日期", "createTime");
            mapper.setColumnMapping(columnMapping);
            CsvToBean<BugBO> csvToBean = new CsvToBean<>();
            List<BugBO> list = csvToBean.parse(mapper, reader);
            for(BugBO bug : list){
                System.out.println("bug = " + bug);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "read csv file success";
    }
}
