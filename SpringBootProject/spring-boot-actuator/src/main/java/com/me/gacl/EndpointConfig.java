package com.me.gacl;

import org.springframework.boot.actuate.endpoint.AbstractEndpoint;
import org.springframework.boot.actuate.endpoint.Endpoint;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author CH-yfy
 * @date 2018/4/23
 */
@Configuration
public class EndpointConfig {

    @Bean
    public Endpoint<Map<String, Object>> autoEndpoint() {
        return new LocalEndpoint();
    }

    /**
     * 自定义endpoint, http://localhost:8080/user_point
     */
    @ConfigurationProperties(prefix = "endpoints.user_point")
    public class LocalEndpoint extends AbstractEndpoint<Map<String, Object>> {

        public LocalEndpoint() {
            //Id just permit letter & number & '_'
            super("user_point");
        }

        /**
         * 用于返回监控的内容
         * @return
         */
        @Override
        public Map<String, Object> invoke() {
            Map<String,Object> map = new HashMap<>(100);
            map.put("user-name",User.getUSERNAME());
            map.put("user-age",User.getAGE());
            map.put("user-address",User.getADDRESS());
            return map;
        }
    }


    @Bean
    public MemoryEndpoint injectEndpoint() {
        return new MemoryEndpoint();
    }
}
