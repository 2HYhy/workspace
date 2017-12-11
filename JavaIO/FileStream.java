import java.io.*;

/**
 * Created by Administrator on 2017/6/25.
 */
public class FileStream {

    public static void main (String [] args) throws IOException {

        //创建文件和目录
        File dir = new File("E:\\my-files");
        File f1= new File(dir,"first.txt");
        File f2 = new File(dir,"second.txt");
        if (! dir.exists()) {
            dir.mkdir();
        }
        if (! f1.exists()) {
            f1.createNewFile();
        }
        if (! f2.exists()) {
            f2.createNewFile();
        }
        System.out.println("absolutePath is :"+f1.getAbsolutePath());
        System.out.println("canRead is :"+f1.canRead());
        System.out.println("length is :"+f1.length());
        System.out.println("name is :"+f1.getName());
        System.out.println("parent is :"+f1.getParent());
        System.out.println("path is :"+f1.getPath());
        String [] array = dir.list();
        int count = 0;
        for (int i =0; i < array.length; i++) {
            count++;
            System.out.println("my-files including file of "+array[i]);
        }
        System.out.println(count);


        //将已存在文件的内容打印出来
        FileInputStream in = new FileInputStream("E:\\my-files\\first.txt");
        int n =512;
        byte [] buff = new byte[n];
        while(in.read(buff, 0, n) != -1 && n > 0) {
            System.out.println(new String(buff));
        }
        in.close();


        //从键盘输入内容，将其保存在指定文件中
        System.out.println("please input from keyboard:");
        int count1 = 0, n1 = 512;
        byte [] buff1 = new byte[n];
        count = System.in.read(buff);
        FileOutputStream out = new FileOutputStream("E:\\my-files\\third.txt");
        out.write(buff1, 0, count1);
        out.close();
        System.out.println("file has saved successfully!");


        //将一个文件的内容添加到另一个文件内容的后面
        File f11 = new File("E:\\my-files\\second.txt");
        File f12 = new File("E:\\my-files\\third.txt");
        FileInputStream in1 = new FileInputStream(f11);
        FileOutputStream out1 = new FileOutputStream(f12,true);//true表示不会覆盖third文件原有内容
        int count2;
        while ((count2 = in1.read()) != -1) {
            out.write(count2);
            System.out.println((char) count2 +"has been write into!");
        }
        in1.close();
        out1.close();
        System.out.println("copy file success");

        
        //键盘输入流读取到内存，使用缓冲区
        InputStreamReader in2 =new InputStreamReader(System.in);
        BufferedReader buffer1 = new BufferedReader(in2);
        FileWriter fw = new FileWriter("E:\\my-files\\fourth.txt");
        BufferedWriter buffer2 = new BufferedWriter(fw);
        String s;
        while((s = buffer1.readLine()).length() > 0) {
            buffer2.write(s, 0, s.length());
        }
        buffer1.close();
        buffer2.close();
    }
}