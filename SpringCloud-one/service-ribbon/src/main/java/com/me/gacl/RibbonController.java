package com.me.gacl;

import com.me.gacl.source.RibbonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.Properties;

/**
 * @author CH-yfy
 * @date 2018/4/18
 */
@RestController
public class RibbonController {

    @Autowired
    RibbonService ribbonService;

    @RequestMapping("/ribbon/hello")
    public String sayHello() {
        return ribbonService.sayHello();
    }

    @RequestMapping("/ribbon/user")
    public String getUser(@RequestParam("name") String name) {
        return ribbonService.getUser(name);
    }

    /**
     * eureka-client的9001，9002不是轮询出现，而是随机出现的
     * @return
     * 此处不能使用自动注入的restTemplate，否则会报错(no instance...)
     */
    @Autowired
    LoadBalancerClient loadBalancerClient;

    @GetMapping("/ribbon/strategy")
    public String byStrategy() {
        ServiceInstance instance = this.loadBalancerClient.choose("EUREKA-CLIENT");
        URI uri = URI.create(String.format("http://%s:%s/ribbon/test", instance.getHost(),instance.getPort()));
        System.out.println("uri= "+uri);
        return new RestTemplate().getForEntity(uri, String.class).getBody();
    }

    @Autowired
    RibbonConfig ribbonConfig;

    @GetMapping("/ribbon/sourceCode")
    public String bySourceCode() {
        ServiceInstance instance = ribbonConfig.choose("EUREKA-CLIENT");
        URI uri = URI.create(String.format("http://%s:%s/ribbon/test", instance.getHost(),instance.getPort()));
        System.out.println("uri= "+uri);
        return new RestTemplate().getForEntity(uri, String.class).getBody();
    }





    @Value("${com.me.port}")
    private Integer port;
    @GetMapping("/ribbon/port")
    public Integer getPort(HttpServletRequest request){
        System.out.println("PORT = "+port);
        int port_real = request.getServerPort();
        System.out.println("PORT_REAL = "+port_real);

        //修改配置文件中key的值
        this.writeData("com.me.port", port_real);

        //修改后再次获取key的值,仍然为修改前的值; 将文件中的常量封装在一个constantConfig类中，通过get/set的方式操作，即可获取修改后的值
        int port_proper = this.newPort();

        return port_proper;
    }
    @Value("${com.me.port}")
    private int ports;
    private int newPort(){
        return ports;
    }
    private void writeData(String key, Integer value) {
        File file = new File("service-ribbon/src/main/resources/application.properties");
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(file);
            Properties prop = new Properties();
            prop.load(fis);
            System.out.println("------server.port = "+prop.get("server.port")+"------");
            prop.setProperty(key, String.valueOf(value));
            System.out.println("PORT after = "+prop.get(key));
            fos = new FileOutputStream(file);
            prop.store(fos, null);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                fis.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
