package com.me.gacl;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author CH-yfy
 * @date 2018/4/28
 */
@Configuration
public class MultiPartConfig {

    @Bean
    public Encoder feignFormEncoder() {
        return new SpringFormEncoder();
    }
}
