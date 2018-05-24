package com.example.controller;

import com.example.entity.OrderInfo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author CH-yfy
 * @date 2018/4/13
 */
@RestController
@RequestMapping(value = "/order", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class OrderController {

    /**
     * 增
     * @param order
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public OrderInfo save(@RequestBody OrderInfo order) {
        OrderInfo newOrder = new OrderInfo();
        newOrder.setId(1);
        newOrder.setName(order.getName());
        newOrder.setAmount(order.getAmount());
        newOrder.setTime(order.getTime());
        return newOrder;
    }

    /**
     * 查
     * @param name
     * @param id
     * @return
     */
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public OrderInfo get(@RequestParam("name") String name,
                         @PathVariable("id") int id) {
        OrderInfo order = new OrderInfo();
        order.setId(id);
        order.setName(name);
        return order;
    }

    /**
     * 改
     * @param order
     * @param id
     * @return
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public OrderInfo update(@RequestBody OrderInfo order,
                            @PathVariable("id") int id) {
        OrderInfo orders = new OrderInfo();
        orders.setId(id);
        orders.setName(order.getName());
        orders.setAmount(order.getAmount());
        orders.setTime(order.getTime());
        return orders;
    }
}
