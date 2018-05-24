package com.me.gacl.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;

/**
 * @author CH-yfy
 * @date 2018/5/3
 * 该类中配置的两个用户，可以替代application.properties中默认的用户user，执行登录，从而授权
 */
@Configuration
public class SecurityUserConfig extends GlobalAuthenticationConfigurerAdapter {

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        //添加两个用户
        auth.inMemoryAuthentication()
                .withUser("monday").password("100001").roles("USER").and()
                .withUser("tuesday").password("900009").roles("USER","ADMIN");
    }
}