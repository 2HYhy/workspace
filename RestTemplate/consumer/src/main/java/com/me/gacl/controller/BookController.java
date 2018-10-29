package com.me.gacl.controller;

import com.me.gacl.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author CH-yfy
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
     * 同样有postForLocation方法，返回的是新资源的uri,服务提供者只要返回一个uri
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
     * put请求，无返回值
     */
    @RequestMapping(value = "/put")
    public void put() {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setName("云过天空");
        restTemplate.put("http://PROVIDER/book_pro/put/{0}",bookDTO, 9);
    }

    /**
     * delete请求，无返回值
     */
    @RequestMapping(value = "/delete")
    public void delete() {
        restTemplate.delete("http://PROVIDER/book_pro/delete/{0}", 9);
    }

}
