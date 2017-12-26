package com.me.gacl;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

/**
 * @author yunhua.he
 * @date 2017/12/26
 */
public class Receive1 {

    private static final String QUEUE_NAME = "testQueue";

    public static void main(String [] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setVirtualHost("/test");
        factory.setPort(5672);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, true, null);
        //同一时刻服务器只会发送一条消息到消费者来保证资源的合理应用
        channel.basicQos(1);
        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(QUEUE_NAME, false, consumer);
        while (true) {
           QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String information = new String(delivery.getBody());
            System.out.println("receive "+ information);
            Thread.sleep(10);
            //返回确认状态
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }
    }
}
