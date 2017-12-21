package com.me.gacl.pojo;

/**
 * @author CH-yfy
 * 事件对象，用于封装事件源
 */
public class Events {

    private User source;

    public Events(User u) {
        this.source = u;
    }

    public User getSource() {
        return source;
    }

    public void setSource(User source) {
        this.source = source;
    }
}
