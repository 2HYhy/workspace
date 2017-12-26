package com.me.gacl;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

/**
 * @author yunhua.he
 * @date 2017/12/26
 * 发布/订阅模式：一个生产者，一个交换机，多个队列，多个消费者
 * 如果消息发送到没有队列绑定的交换机上，那么消息将丢失,所以要在消费之前就创建好队列
 */
public class Send2 {

    private final static String EXCHANGE_NAME = "test_exchange";

    public static void main(String [] args) throws IOException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setVirtualHost("/test");
        factory.setPort(5672);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        //fanout即广播模式的交换机
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        String message = "mode of publish and subscribe";
        //使用通道向交换机发送消息
        channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
        System.out.println("sent message");
        channel.close();
        connection.close();
    }
}
//创建一个非持久化、独立的、自动删除的队列，且名字是随机生成的
//String queueName = channel.queueDeclare().getQueue();