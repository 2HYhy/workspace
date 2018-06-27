package com.me.gacl.controller;

import com.me.gacl.dto.BookDTO;
import com.me.gacl.hystrix.BookCommand;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * @author momo
 * @date 2018/6/19
 * 去消费provider中的接口
 */
@RestController
@RequestMapping("/book_con")
public class BookController {

    @Autowired
    RestTemplate restTemplate;

    /**
     * get请求，与getForEntity的区别在于关注返回的消息体的内容，对其他信息都不关注
     * @return
     */
    @RequestMapping("/get")
    public BookDTO get() {
        BookDTO book = restTemplate.getForObject("http://PROVIDER/book_pro/get", BookDTO.class);
        return book;
    }

    /**
     * post请求，参数1表示调用的服务的地址，参数2表示上传的参数，参数3表示返回的数据类型
     * @return
     * 同样有postForObject方法
     */
    @RequestMapping(value = "/save")
    public BookDTO save() {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setName("孤独里");
        bookDTO.setAuthor("戴子月");
        ResponseEntity<BookDTO> entity = restTemplate.postForEntity("http://PROVIDER/book_pro/save", bookDTO, BookDTO.class);
        return entity.getBody();
    }

    /**
     * post请求,提交新资源后返回的是新资源的uri,服务提供者只要返回一个uri
     * @return
     */
    @RequestMapping(value = "/save2")
    public URI save2() {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setName("繁华外");
        URI uri = restTemplate.postForLocation("http://PROVIDER/book_pro/xxx",bookDTO, BookDTO.class);
        return uri;
    }

    /**
     * put请求，无返回值
     */
    @RequestMapping(value = "/put")
    public void put() {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setName("云过天空");
        restTemplate.put("http://PROVIDER/book_pro/put/{0}",bookDTO,999);
    }

    /**
     * delete请求，无返回值
     */
    @RequestMapping(value = "/delete")
    public void delete() {
        restTemplate.delete("http://PROVIDER/book_pro/delete/{0}",666);
    }

    /**
     * 连着发起3个相同的请求，验证缓存是否生效
     * @return
     * 通console过日志，provider只执行了一次，其余两次都是缓存数据
     */
    @RequestMapping(value = "getById")
    public BookDTO getById() {
        HystrixCommandKey command = HystrixCommandKey.Factory.asKey("commandKey");
        HystrixRequestContext.initializeContext();
        BookCommand bcOne = new BookCommand(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("")).andCommandKey(command), restTemplate, 1L);
        BookDTO book1 = bcOne.execute();
        BookCommand bcTwo = new BookCommand(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("")).andCommandKey(command), restTemplate, 1L);
        BookDTO book2 = bcTwo.execute();
        BookCommand bcThree = new BookCommand(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("")).andCommandKey(command), restTemplate, 1L);
        BookDTO book3 = bcThree.execute();
        System.out.println("book1= " +book1);
        System.out.println("book2= " +book2);
        System.out.println("book3= " +book3);
        return book3;
    }

}
