package com.me.gacl.controller;


import com.me.gacl.BookDTO;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author CH-yfy
 * @date 2018/6/19
 */
@RestController
@RequestMapping("/book_pro")
public class BookController {

    private Logger logger = Logger.getLogger(getClass());

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public BookDTO book1() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse("1979-08-20");
        return new BookDTO("三国演义", "罗贯中", "90￥", date);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public BookDTO book2(@RequestBody BookDTO book) {
        book.setPrice("33￥");
        book.setPublishTime(new Date());
        return book;
    }

    @RequestMapping(value = "/put/{id}", method = RequestMethod.PUT)
    public void book3(@RequestBody BookDTO book, @PathVariable int id) {
        logger.info("book name is:" + book.getName());
        logger.info("id is:" + id);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void book4(@PathVariable int id) {
        logger.info("id is:" + id);
    }


    /**
     * 3.用于消费者验证缓存接口
     * @param id
     * @return
     */
    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
    public BookDTO getById(@PathVariable Integer id) {
        System.out.println("======getById, id==" + id);
        if (id == 1) {
            return new BookDTO("book-one","author-one","86￥",new Date());
        }
        return new BookDTO("book-three","author-three","46￥",new Date());
    }

    /**
     * 用于消费者验证注解缓存接口
     * @param name
     * @return
     */
    @RequestMapping(value = "/getByIdName", method = RequestMethod.GET)
    public String getByName(@RequestParam("id")Integer id, @RequestParam("name")String name) {
        System.out.println("======getByName======");
        return "name of book is "+name+", id of book is "+id;
    }

    /**
     * 用于消费者验证请求合并接口
     * @param ids
     * @return
     */
    @RequestMapping(value = "/findBook", method = RequestMethod.GET)
    public List<String> findBook(@RequestParam("ids") String ids) {
        System.out.println("------ids = "+ids);
        String[] array = ids.split(",");
        List<String> list = new ArrayList<>();
        for (String id : array) {
            list.add(id);
        }
        return list;
    }

}
