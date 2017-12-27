package com.me.gacl;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;

/**
 *
 * @author CH-yfy
 * @date 2017/8/21
 */
public class Receive {

    /**
     * 队列名称
     */
    private final static String QUEUE_NAME = "myQueue";

    public static void main(String[] args) throws IOException, InterruptedException {
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
        //声明队列，防止消息接收者先运行程序，队列不存在时创建队列
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        System.out.println("Waiting for message, To exit press 'CTRL+C'");
        //创建队列消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        //指定消费队列
        channel.basicConsume(QUEUE_NAME, true, consumer);
        //获取消息
        while(true) {
            //阻塞方法
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println("Received '"+message+"'");
        }
    }
}
