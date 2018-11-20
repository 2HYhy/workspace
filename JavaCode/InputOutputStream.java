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