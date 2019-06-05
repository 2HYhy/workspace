package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author CH-yfy
 * @date 2018/5/3
 */
@RestController
@EnableOAuth2Sso
public class UserConfigController extends WebSecurityConfigurerAdapter {

    private Map<Integer, UserDO> userMap = new HashMap<>();

    @Autowired
    private OAuth2ClientContext context;

    /**
     * 直接访问
     * @return
     */
    @RequestMapping(value="/", method=RequestMethod.GET)
    public String show() {
        return "security<<<<<<>>>>>>oauth2";
    }

    /**
     * 直接访问
     * @return
     */
    @RequestMapping(value="/user", method=RequestMethod.GET)
    public Collection<UserDO> get(){
        if (ObjectUtils.isEmpty(userMap)){
            userMap.put(1, new UserDO(1, "apple", 25, "ShannXi"));
            userMap.put(2, new UserDO(2, "pear", 27, "HuNan"));
            userMap.put(3, new UserDO(3, "peach", 38, "ShanDong"));
        }
        return userMap.values();
    }

    /**
     * 需要登录gitHub授权，若已登录则直接通过
     * @param uid
     * @return
     */
    @RequestMapping(value="/user/{uid}", method= RequestMethod.GET)
    public Collection<UserDO> save(@PathVariable Integer uid){
        userMap.put(uid, new UserDO(uid, "banana", 100, "Apple"));
        return userMap.values();
    }

    /**
     * 需要登录gitHub授权，若已登录则直接通过
     * @return
     */
    @RequestMapping("/access_token")
    public String getToken(){
        String token = context.getAccessToken().getValue();
        System.out.println ("access_token : "+token);
        return token;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                //取消安全验证，访问不受限制
                .antMatchers("/", "/user")
                .permitAll()
                .anyRequest()
                .authenticated();
    }

}
