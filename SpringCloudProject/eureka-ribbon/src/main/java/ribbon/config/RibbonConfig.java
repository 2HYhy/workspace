package ribbon.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author CH-yfy
 * @date 2018/8/8
 */
@Configuration
public class RibbonConfig {

    /**
     * 自定义随机选择策略
     * @return
     */
    @Bean
	public IRule randomRule() {
        System.out.println(">>>>>>RibbonConfig采用随机选择策略>>>>>>");
		return new RandomRule();
	}
}
