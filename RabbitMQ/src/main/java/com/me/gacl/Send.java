package com.me.gacl;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

/**
 * Created by yunhua.he on 2017/8/21.
 */
public class Send {

    private final static String QUEUE_NAME = "queue";  //队列名称

    public static void main(String[] args) throws IOException {
        ConnectionFactory factory = new ConnectionFactory();   //创建连接到rabbitmq
        factory.setHost("127.0.0.1");  //创建rabbitmq所在主机IP或主机名
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();  //创建一个频道
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //指定一个队列,服务器重启时是否能存活(不能)，是否为当前连接的专用队列(否，连接断开后会自动删除)，没有任何消费者时，是否自动删除(否)
        String message = "hello, world!";  //要发送的消息
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());  //往队列中发出一条消息
        System.out.println("Sent '"+message+"'");
        channel.close();
        connection.close();
    }
}
