package com.me.gacl;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

/**
 *
 * @author yunhua.he
 * @date 2017/8/21
 */
public class BaseConnector {

    protected Channel channel;
    protected Connection connection;
    protected String queueName;

    public BaseConnector(String name) throws IOException{
        this.queueName = name;
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setVirtualHost("/test");
        factory.setPort(5672);
        connection = factory.newConnection();
        channel = connection.createChannel();
        //声明创建队列(是否持久，是否唯一，是否自动删除)
        channel.queueDeclare(queueName, false, false, true, null);
    }
}
