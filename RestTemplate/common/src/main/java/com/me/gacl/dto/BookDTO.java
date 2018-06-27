package com.me.gacl.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author momo
 * @date 2018/6/15
 */
public class BookDTO implements Serializable{

    private String name;
    private String price;
    private String author;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date publishTime;

    public BookDTO() {
    }

    public BookDTO(String name, String price, String author, Date publishTime) {
        this.name = name;
        this.price = price;
        this.author = author;
        this.publishTime = publishTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    @Override
    public String toString() {
        return "BookDTO{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", author='" + author + '\'' +
                ", publishTime=" + publishTime +
                '}';
    }
}
