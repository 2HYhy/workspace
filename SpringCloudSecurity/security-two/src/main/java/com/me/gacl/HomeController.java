package com.me.gacl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CH-yfy
 * @date 2018/4/27
 */
@RestController
public class HomeController {

    /**
     * 不做安全限制
     * @param id
     * @return
     */
    @GetMapping("/product/{id}")
    public String getProduct(@PathVariable String id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "product id : " + id;
    }

    /**
     * 添加访问控制
     * @param id
     * @return
     */
    @GetMapping("/order/{id}")
    public String getOrder(@PathVariable String id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "order id : " + id;
    }

}
