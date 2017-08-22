package com.me.gacl;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

/**
 * Created by yunhua.he on 2017/8/21.
 */
public class BaseConnector {

    protected Channel channel;
    protected Connection connection;
    protected String queueName;

    public BaseConnector(String name) throws IOException{
        this.queueName = name;
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        connection = factory.newConnection();
        channel = connection.createChannel();
        channel.queueDeclare(queueName, false, false, false, null);  //声明创建队列
    }
}
