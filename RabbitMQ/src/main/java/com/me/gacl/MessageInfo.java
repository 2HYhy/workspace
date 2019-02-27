package com.me.gacl;

import java.io.Serializable;

/**
 * Created by CH-yfy on 2017/8/21.
 */
//发送对象消息的过程需要序列化
public class MessageInfo implements Serializable {
    private static final long serialVersionUID = -6757355292169009397L;

    private String channel;
    private String content;

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
