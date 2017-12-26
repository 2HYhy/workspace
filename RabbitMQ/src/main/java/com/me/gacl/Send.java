package com.me.gacl;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

/**
 *
 * @author yunhua.he
 * @date 2017/8/21
 * 简单模式hello world：一个生产者发送消息到队列，一个消费者接收
 */
public class Send {

    /**
     * 队列名称
     */
    private final static String QUEUE_NAME = "myQueue";

    public static void main(String[] args) throws IOException {
        //创建连接到rabbitmq
        ConnectionFactory factory = new ConnectionFactory();
        //创建rabbitmq所在主机IP或主机名
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setVirtualHost("/test");
        Connection connection = factory.newConnection();
        //创建一个通道
        Channel channel = connection.createChannel();
        //声明(创建)一个队列,服务器重启时是否能存活(不能)，是否为当前连接的专用队列(否，连接断开后会自动删除)，没有任何消费者时，是否自动删除(否)
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        //要发送的消息
        String message = "hello, world!";
        //往队列中发出一条消息
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println("Sent '"+message+"'");
        channel.close();
        connection.close();
    }
}
