package com.me.gacl;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

/**
 * @author CH-yfy
 * @date 2017/12/26
 * 通配符模式topic:生产者P发送消息到交换机X，交换机根据绑定队列的routing key的值进行通配符匹配
 * 符号#：匹配一个或者多个词, 符号*：只能匹配一个词， 以.分割字符
 */
public class Send4 {

    private final static String EXCHANGE_NAME = "test4_exchange";

    public static void main(String [] args) throws IOException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setUsername("name");
        factory.setPassword("pwd");
        factory.setVirtualHost("/test");
        factory.setPort(5672);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        //topic即主题模式的交换机
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        String message = "This mode is Topic";
        channel.basicPublish(EXCHANGE_NAME, "myKey.11", null, message.getBytes());
        System.out.println("sent message");
        channel.close();
        connection.close();
    }
}
