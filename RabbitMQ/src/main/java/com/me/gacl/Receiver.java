package com.me.gacl;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;
import org.apache.commons.lang.SerializationUtils;

import java.io.IOException;

/**
 *
 * @author yunhua.he
 * @date 2017/8/21
 */
public class Receiver extends BaseConnector implements Runnable,Consumer {

    public Receiver(String queueName) throws IOException {
        super(queueName);
    }

    @Override
    public void run() {  //为了实现多线程
        try{
            channel.basicConsume(queueName, true, this);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    //当消费者注册完成时自动调用
    @Override
    public void handleConsumeOk(String consumerTag) {  //当消费者注册完成自动调用
        System.out.println("Consumer "+consumerTag +" registered");
    }

    //当消费者接收到消息时自动调用
    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        MessageInfo message = (MessageInfo) SerializationUtils.deserialize(body);
        System.out.println("Message ( channel =" + message.getChannel()+ ", content="+message.getContent() + ") received!");
    }

    @Override
    public void handleCancelOk(String consumerTag) {
    }

    @Override
    public void handleCancel(String consumerTag) throws IOException {
    }

    @Override
    public void handleShutdownSignal(String consumerTag, ShutdownSignalException sig) {
    }

    @Override
    public void handleRecoverOk(String consumerTag) {
    }
}
