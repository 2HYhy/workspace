package com.me.gacl.controller;

import com.me.gacl.dto.BookDTO;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author momo
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
        System.out.println(book.getName());
        book.setPrice("33￥");
        book.setPublishTime(new Date());
        return book;
    }

    @RequestMapping(value = "/put/{id}", method = RequestMethod.PUT)
    public void book3(@RequestBody BookDTO book, @PathVariable int id) {
        logger.info("book is:" + book);
        logger.info("id is:" + id);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void book4(@PathVariable int id) {
        logger.info("id is:" + id);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public BookDTO getById(@PathVariable Integer id) {
        System.out.println("======getById======");
        if (id == 1) {
            return new BookDTO("book-one","author-one","86￥",new Date());
        }
        return new BookDTO("book-three","author-three","46￥",new Date());
    }

}
