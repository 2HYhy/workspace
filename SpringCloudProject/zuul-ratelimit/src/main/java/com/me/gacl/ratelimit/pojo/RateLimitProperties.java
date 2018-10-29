package com.me.gacl.ratelimit.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.LinkedHashMap;
import java.util.Map;
import static com.me.gacl.ratelimit.pojo.RateLimitProperties.PREFIX;

/**
 * @author CH-yfy
 * @date 2018/5/31
 */
@Data
@ConfigurationProperties(prefix = PREFIX)
public class RateLimitProperties {

    /**
     * 配置文件前缀
     */
    public static final String PREFIX = "zuul.ratelimit";
    /**
     * 限流策略配置集合
     */
    private Map<String, Policy> policies = new LinkedHashMap<>();
    /**
     * 是否启用开关
     */
    private boolean enabled;
}
