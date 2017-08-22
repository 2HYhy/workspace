package com.me.gacl;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;

/**
 * Created by yunhua.he on 2017/8/21.
 */
public class Receive {

    private final static String QUEUE_NAME = "queue";  //队列名称

    public static void main(String[] args) throws IOException, InterruptedException {
        ConnectionFactory factory = new ConnectionFactory();   //创建连接到rabbitmq
        factory.setHost("127.0.0.1");  //创建rabbitmq所在主机IP或主机名
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();  //创建一个频道
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);  //声明队列，防止消息接收者先运行程序，队列不存在时创建队列
        System.out.println("Waiting for message, To exit press 'CTRL+C'");
        QueueingConsumer consumer = new QueueingConsumer(channel);  //创建队列消费者
        channel.basicConsume(QUEUE_NAME, true, consumer);  //指定消费队列
        while(true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();  //阻塞方法
            String message = new String(delivery.getBody());
            System.out.println("Received '"+message+"'");
        }
    }
}
