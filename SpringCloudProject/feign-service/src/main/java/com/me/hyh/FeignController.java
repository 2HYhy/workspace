package com.me.hyh;

import com.me.hyh.service.FeignService;
import com.me.hyh.service.RemoteService;
import feign.Feign;
import feign.Request;
import feign.Retryer;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author CH-yfy
 * @date 2018/8/10
 */
@RestController
@RequestMapping("/feign")
public class FeignController {

    @Autowired
    private FeignService feignService;

    @GetMapping("/client")
    public String getClient() {
        return feignService.getClient();
    }

    @GetMapping("/hello")
    public String getHello(@RequestParam Integer id) {
        return feignService.getHello(id);
    }

    @GetMapping("/user/{id}")
    public String getUser(@PathVariable Integer id) {
        return feignService.getUser(id);
    }

    /**
     * 作为客户端，消费eureka-feign的接口
     * 当参数为对象时，需要添加编解码，当参数为单个变量时则不需要
     */
    @GetMapping("/post/user")
    public void postUser() {
        RemoteService service = Feign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .options(new Request.Options(1000, 3500))
                .retryer(new Retryer.Default(5000, 5000, 3))
                .target(RemoteService.class, "http://localhost:9006");
        UserDO user = new UserDO();
        user.setName("hyh");
        user.setId(66);
        service.postUser(user);
    }

}
