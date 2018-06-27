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

    }
}