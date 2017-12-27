import com.me.gacl.AopService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author CH-yfy
 * @date 2017/12/25
 */
public class AopTest {

    public static void main(String [] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("aop.xml");
        AopService aop1 = (AopService) ctx.getBean("aopServiceImpl");
        AopService aop2 = (AopService) ctx.getBean("aopServiceImpls");
        aop1.doFunc();
        System.out.println("-----------");
        aop1.showFunc();

        aop2.doFunc();
        aop2.showFunc();

    }
}
