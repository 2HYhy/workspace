import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.UUID;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
/**
 * Created by Administrator on 2017/6/21.
 */
@WebServlet("/uploadFile")
public class FileUploadServlet extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //上传文件保存路径
        String savePath = this.getServletContext().getRealPath("/WEB-INF/upload");
        //上传时生成的临时文件保存目录
        String tempPath = this.getServletContext().getRealPath("/WEB-INF/temp");
        File file = new File(savePath);
        if (!file.exists() && !file.isDirectory()) {
            System.out.println("目录或文件不存在！");
            file.mkdir();
        }
        String message = "";
        try {
            DiskFileItemFactory factory =new DiskFileItemFactory();
            //工厂的缓冲区的大小，当上传文件大小超过它时，就会生成临时文件存放至指定目录中
            factory.setSizeThreshold(1024 * 1024 * 100);
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setHeaderEncoding("utf-8");
            if (! upload.isMultipartContent(request)) {
                System.out.println("提交上来的数据不是上传表单的数据");
                return;
            }
            //单个上传文件的大小1MB
            upload.setFileSizeMax(1024 * 1024 * 1);
            //上传文件总量的最大值10MB
            upload.setSizeMax(1024 * 1024 * 10);
            List<FileItem> list = upload.parseRequest(request);
            for (FileItem item : list) {
                if (item.isFormField()) {
                    //是普通文本
                    String name = item.getFieldName();
                    String value1 = item.getString("utf-8");
                    String value2 = new String(name.getBytes("iso8859-1"), "utf-8");
                    System.out.println(name+ "  " +value1);
                    System.out.println(name+ "  " +value2);
                } else {
                    //是文件类型
                    String fileName = item.getName();
                    System.out.println("完整的文件名为： "+fileName);
                    if (fileName == null || fileName.trim().equals("")) {
                        continue;
                    }
                    //只保留文件路径名的文件名部分
                    fileName = fileName.substring(fileName.lastIndexOf(File.separator) +1);
                    //得到文件扩展名，限制文件上传类型
                    String extraName = fileName.substring(fileName.lastIndexOf(".")+1);
                    if ("zip".equals(extraName) || "rar".equals(extraName) || "tar".equals(extraName)) {
                        request.setAttribute("message","上传文件类型不符合！");
                        request.getRequestDispatcher("/message.jsp").forward(request, response);
                    }
                    System.out.println("文件；类型为： "+extraName);
                    //最终的文件保存名，确保文件名唯一，不会因相同而被覆盖
                    String fileSaveName = UUID.randomUUID().toString()+fileName;
                    InputStream in = item.getInputStream();
                    OutputStream out = new FileOutputStream(savePath+File.separator+fileName);
                    byte [] buffer = new byte[1024];
                    int length = 0 ;   //判断文件数据是否已读完的标志
                    while ((length = in.read(buffer))>0 ) {
                        out.write(buffer, 0 ,length);
                    }
                    in.close();
                    out.close();
                    message = "文件上传成功！";
                }
            }
            
        } catch (FileUploadBase.FileSizeLimitExceededException e1) {
            e1.getMessage();
            request.setAttribute("message", "单个文件超出最大值！");
            request.getRequestDispatcher("/meessage.jsp").forward(request, response);
            return;
        } catch (FileUploadBase.SizeLimitExceededException e2) {
            e2.getMessage();
            request.setAttribute("message", "上传文件总量超出最大值！");
            request.getRequestDispatcher("/meessage.jsp").forward(request, response);
            return;
        } catch (FileUploadException e) {
            e.getMessage();
            message = "文件上传失败";
        }
        request.setAttribute("message", message);
        request.getRequestDispatcher("/message.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        this.doGet(request, response);
    }
}
} 

//upload.jsp
<form method = "post" action="http://localhost:8080/uploadFile" enctype="multipart/form-data">
        选择一个文件：
<input type = "file" name = "uploadFile"/>
<br/><br/>
<input type = "submit" value = "上传"/>
</form>