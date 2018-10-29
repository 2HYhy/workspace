package com.me.gacl.hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;

/**
 * @author yunhua.he
 * @date 2018/8/24
 */
@Service
public class HystrixService {

    @Autowired
    RestTemplate restTemplate;

    //表示给方法开启缓存,默认将所有参数作为缓存的key,即所有参数都相同时才能走缓存
    @CacheResult
    @HystrixCommand(commandProperties = {@HystrixProperty(name = "requestCache.enabled", value="true")})
    public String getBookOne(Integer id, String name) {
        return restTemplate.getForObject("http://PROVIDER/book_pro/getByIdName?id={0}&name={1}", String.class, id, name);
    }

    //只要name相等，就可以走缓存
    @CacheResult
    @HystrixCommand(commandProperties = {@HystrixProperty(name = "requestCache.enabled", value="true")})
    public String getBookTwo(Integer id, @CacheKey String name) {
        return restTemplate.getForObject("http://PROVIDER/book_pro/getByIdName?id={0}&name={1}", String.class, id, name);
    }

    //调用该方法时，将getBookTwo的缓存清除，必须指定commandKey，它指明了缓存的位置
    @CacheResult
    @CacheRemove(commandKey = "getBookTwo")
    @HystrixCommand(commandProperties = {@HystrixProperty(name = "requestCache.enabled", value="true")})
    public String getBookThree(Integer id, @CacheKey String name) {
        return "clear cache of id="+id+" & name="+name;
    }


    /**
     * 设置为请求上下文（单个Tomcat线程），HystrixRequestContextServletFilter是必需的
     * Future表示一个可能还没有完成的异步任务的结果
     * maxRequestsInBatch代表批量处理的最大请求数量，默认值为Integer.MAX_VALUE
     * timerDelayInMilliseconds代表多长时间之内算一次批处理，默认为10ms，此处改为10s
     * batchMethod代表请求合并后的处理方法
     * @param id
     * @return
     */
    @HystrixCollapser(scope = com.netflix.hystrix.HystrixCollapser.Scope.REQUEST, batchMethod = "findBook",
            collapserProperties = {@HystrixProperty(name = "timerDelayInMilliseconds",value = "10000"),
                    @HystrixProperty(name = "maxRequestsInBatch",value = "5")})
    public Future<String> func1(Integer id) {
        return null;
    }

    /**
     * 设置为全局上下文（跨越所有Tomcat线程），不需要HystrixRequestContextServletFilter
     * @param id
     * @return
     */
    @HystrixCollapser(scope = com.netflix.hystrix.HystrixCollapser.Scope.GLOBAL, batchMethod = "findBook",
            collapserProperties = {@HystrixProperty(name = "timerDelayInMilliseconds",value = "10000"),
                    @HystrixProperty(name = "maxRequestsInBatch",value = "5")})
    public Future<String> func2(Integer id) {
        return null;
    }

    /**
     * 会等待10s，将所有请求合并，一起调用服务提供者的接口
     *
     * 多个请求被合并为一个请求进行一次性处理，可以有效节省网络带宽和线程池资源。
     * 但设置请求合并之后，本来一个请求可能5ms就响应，但现在必须再等10s看还有没有其他请求一起
     * @param ids
     * @return
     */
    @HystrixCommand
    public List<String> findBook(List<Integer> ids) {
        //在RestTemplate中，如果返回值是一个集合，我们得先用一个数组接收，然后再转为集合; 用","将10s内的所有请求id分隔
        String[] arrays = restTemplate.getForObject("http://PROVIDER/book_pro/findBook?ids={0}", String[].class, StringUtils.join(ids,","));
        return Arrays.asList(arrays);
    }
}
