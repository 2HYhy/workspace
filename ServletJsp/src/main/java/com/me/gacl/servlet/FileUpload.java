package com.me.gacl.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Created by yunhua.he on 2017/9/29.
 */
@WebServlet(value = "ServletFileUpload")
public class FileUpload extends HttpServlet {
    private static final String UPLOAD_DIRECTORY = "upload";
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;   //3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 3;
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50;
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //检测是否为多媒体上传
        if (!ServletFileUpload.isMultipartContent(request)) {
            //如果不是则停止
            PrintWriter writer = response.getWriter();
            writer.println("表单必须包含enctype=multipart/form-data");
        }
        //配置上传参数
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //设置内存临界值，超过后将产生临时文件，并存储于临时目录中
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        //设置临时存储目录
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        ServletFileUpload upload = new ServletFileUpload(factory);
        //设置最大文件上传值
        upload.setFileSizeMax(MAX_FILE_SIZE);
        //设置最大请求值(包括文件和表单数据)
        upload.setSizeMax(MAX_REQUEST_SIZE);
        //中文处理
        upload.setHeaderEncoding("utf-8");
        //构造临时路径来存储上传的文件
        String uploadPath = request.getServletContext().getRealPath("./")
                + File.separator + UPLOAD_DIRECTORY;
        //如果目录不存在则创建
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        //解析请求的内容提取文件数据
        try {
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);
            if (formItems != null && formItems.size() >0 ) {
                //迭代表单数据
                for (FileItem item : formItems) {
                    if (! item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        String filePath = uploadPath + File.separator +fileName;
                        File storeFile = new File(filePath);
                        //控制台输出文件的上传路径
                        System.out.println(filePath);
                        //保存文件到硬盘
                        item.write(storeFile);
                        request.setAttribute("message", "文件上传成功");
                    }
                }
            }
        } catch (Exception e) {
            request.setAttribute("message", "错误信息"+e.getMessage());
            e.printStackTrace();
        }
        //跳转至message.jsp
        request.getServletContext().getRequestDispatcher("/message.jsp").forward(request, response);

    }
}