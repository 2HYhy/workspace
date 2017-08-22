package com.me.gacl;

import org.apache.commons.lang.SerializationUtils;

import java.io.IOException;
import java.io.Serializable;

/**
 * Created by yunhua.he on 2017/8/21.
 */
public class Sender extends BaseConnector {

    public Sender(String name) throws IOException{
        super(name);
    }

    public void sendMsg(Serializable object) throws IOException{
        channel.basicPublish("", queueName, null, SerializationUtils.serialize(object));
    }
}
