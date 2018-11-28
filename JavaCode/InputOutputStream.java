import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 2017/6/25.
 */
public class InputOutputStream {

    public static void main (String [] args) throws IOException {

        //输出args参数
        for (int i = 0; i < args.length; i++) {
            System.out.println("args["+i+"]="+args[i]);
        }

        //一个字节的读取输入流,键盘输入什么，结果输出什么
        int b;
        System.out.println("please input:");
        while ((b = System.in.read()) != -1) {
            System.out.println((char) b );
            System.out.println("char = "+(char)b+", int = "+ b);
        }

        //增加缓存区，一行的读取输入流
        String s;
        System.out.println("please input:");
        InputStreamReader in = new InputStreamReader(System.in);
        BufferedReader buffer = new BufferedReader(in);
        s = buffer.readLine();
        while (s != null) {
            System.out.println("s = "+s);
            s = buffer.readLine();
        }
        buffer.close();

		//读取源文件的内容并在控制台输出
		File file = new File("/Users/CH-yfy/testFile.txt");
		try {
			FileReader reader = new FileReader(file);
			int line;
			while((line = reader.read())!=-1){
				System.out.print((char)(line));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		//读取源文件中50个字节内容并写进新文件中
		File fileInput = new File("/Users/CH-yfy/inputFile.txt");
		File fileOutput = new File("/Users/CH-yfy/outputFile.txt");
		try {
			FileInputStream in = new FileInputStream(fileInput);
			FileOutputStream out = new FileOutputStream(fileOutput);
			//定义一个20字节的数组(一个字母即为一个字节,一个汉字为两个字节)
			byte [] bytes = new byte[20];
			//读到内存
			in.read(bytes);
			//写到文件
			out.write(bytes);
			in.close();
			out.close();
			System.out.println("读写结束");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		//从键盘输入内容写进文件中，当输入的字符串为quit时，回车后结束写入
		File file = new File("/Users/CH-yfy/inputFile.txt");
		try {
			FileWriter fw = new FileWriter(file);
			Scanner in = new Scanner(System.in);
			String content;
			while(!(content = in.nextLine()).equals("quit")) {
				fw.write(content);
			}
			fw.close();
			System.out.println("写入结束!");
		} catch (IOException e) {
			e.printStackTrace();
		}

		//将无行的汉语源文件写入到新文件中，每10个字符一行
		File file1 = new File("/Users/CH-yfy/inputFile.txt");
		File file2 = new File("/Users/CH-yfy/outputFile.txt");
		try {
			FileReader fr = new FileReader(file1);
			FileWriter fw = new FileWriter(file2);
			char temp [] = new char[10];
			int length;
			while((length = fr.read(temp)) != -1) {
				if (length == 10) {
					//windows下的文本文件换行符:\r\n; linux/unix下的文本文件换行符:\r; Mac下的文本文件换行符:\n
					fw.write(new String(temp) + "\n");
				} else {
					fw.write(new String(temp));
				}
			}
			fr.close();
			fw.close();
			System.out.println("读写结束!");
		} catch (IOException e) {
			e.printStackTrace();
		}

		//读取汉字文件，复制到新文件中
		File file1 = new File("/Users/CH-yfy/inputFile.txt");
		File file2 = new File("/Users/CH-yfy/outputFile.txt");
		InputStreamReader in = null;
		OutputStreamWriter out = null;
		try {
			in = new InputStreamReader(new FileInputStream(file1));
			out = new OutputStreamWriter(new FileOutputStream(file2));
			BufferedReader buffer = new BufferedReader(in);
			String line;
			while((line = buffer.readLine()) != null) {
				out.write(line);
				out.write(line+"\n");
				System.out.println(line);
			}
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

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
        


        //输入输出+访问远程API示例 
        String url = "http://ip.taobao.com/service/getIpInfo.php";  
        String content = "ip=219.136.134.157";
        String encoding = "utf-8";
        String result = getResult(url, content, encoding);
        System.out.println(result);
    }

    private static String getResult(String urlStr, String content, String encoding) {
		URL url;
		HttpURLConnection connection = null;
		try {
			url = new URL(urlStr);
			connection = (HttpURLConnection) url.openConnection();  // 新建连接实例
			connection.setConnectTimeout(2000);       // 设置连接超时时间(毫秒)
			connection.setReadTimeout(2000);         // 设置读取数据超时时间(毫秒)
			connection.setDoOutput(true);           // 是否打开输出流
			connection.setDoInput(true);           // 是否打开输入流
			connection.setRequestMethod("POST");  // 提交方法,默认是GET
			connection.setUseCaches(false);      // 是否缓存
			connection.connect();               // 打开连接端口

			DataOutputStream out = new DataOutputStream(connection.getOutputStream());  //打开输出流往远程服务器写数据
			out.writeBytes(content);  // 提交的数据表单 形如name=xxx&pwd=xxx
			out.flush();  // 刷新
			out.close();

			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), encoding));   // 远程端服务器返回数据
			StringBuilder buffer = new StringBuilder();  // 用StringBuilder来读取
			String line;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			reader.close();
			return buffer.toString();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		return null;
	}
}