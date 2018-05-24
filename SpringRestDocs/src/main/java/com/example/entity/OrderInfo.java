package com.example.entity;

import java.util.Date;

/**
 * @author CH-yfy
 * @date 2018/4/13
 */
public class OrderInfo {

   private int id;
    private String name;
    private int amount;
    private Date time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
