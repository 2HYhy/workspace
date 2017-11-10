DataSourceAspectpackage com.changhong.usercenter;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Aspect;

import java.lang.reflect.Method;

/**
 * @author yunhua.he
 * @date 2017/11/6
 * 4.定义数据源AOP切面
 */

@Component
@Aspect
public class DataSourceAspect {
    private final static Logger logger = LoggerFactory.getLogger(DataSourceAspect.class);

    //切换置于mapper接口的方法上
    @Pointcut("execution(* com.changhong.usercenter.mapper.*.*(..))")
    public void dataSourcePointCut() {

    }
    @Before("dataSourcePointCut()")
    public void before(JoinPoint joinPoint) {
        Object target = joinPoint.getTarget();
        String method = joinPoint.getSignature().getName();
        Class<?>[] clazz = target.getClass().getInterfaces();
        Class<?>[] parameterTypes = ((MethodSignature) joinPoint.getSignature()).getMethod().getParameterTypes();
        try {
            Method m = clazz[0].getMethod(method, parameterTypes);
            //当方法上有切换数据源的注释时
            if (m != null && m.isAnnotationPresent(TargetDataSource.class)) {
                TargetDataSource data = m.getAnnotation(TargetDataSource.class);
                String dataSourceName = data.value();
                DynamicDataSourceHolder.putDataSource(dataSourceName);
                logger.info("current thread" + Thread.currentThread().getName() + "add" + dataSourceName + "to ThreadLocal");
            } else {
                logger.info("switch datasource fail,use default");
            }
        } catch(Exception e) {
            logger.error("current thread" + Thread.currentThread().getName() + "add data to ThreadLocal error", e);
        }
    }

    //执行完切面后，将线程共享中的数据源名称清空
    @After("dataSourcePointCut()")
    public void after(JoinPoint joinPoint) {
        DynamicDataSourceHolder.removeDataSource();
    }
}
