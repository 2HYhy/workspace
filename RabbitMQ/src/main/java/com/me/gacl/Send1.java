package com.me.gacl;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author CH-yfy
 * @date 2017/12/26
 * 工作队列模式：一个生产者，多个消费者，且消息唯一
 */
public class Send1 {

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
        for (int i=0; i<3; i++) {
            String message = "This is the message of " + i;
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println("sent message:"+ i);
            Thread.sleep(i*10);
        }
        channel.close();
        connection.close();
    }
}
