package com.me.gacl;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

/**
 * @author CH-yfy
 * @date 2017/12/26
 * 路由模式Routing:生产者发送消息到交换机并指定路由key，消费者将队列绑定到交换机时需路由key
 * 如果vhost中不存在RouteKey中指定的队列名，则该消息会被抛弃，需要将队列绑定到交换机上
 */
public class Send3 {

    private final static String EXCHANGE_NAME = "test3_exchange";

    public static void main(String [] args) throws IOException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setVirtualHost("/test");
        factory.setPort(5672);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        //direct即直接指定模式的交换机
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
        String message = "The mode of Routing";
        channel.basicPublish(EXCHANGE_NAME, "routingKey1", null, message.getBytes());
        System.out.println("sent message");
        channel.close();
        connection.close();
    }

}
