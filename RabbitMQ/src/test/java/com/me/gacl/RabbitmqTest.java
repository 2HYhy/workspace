package com.me.gacl;

/**
 *@date 2017/8/21
 *@author CH-yfy
 */
public class RabbitmqTest {
    public static void main(String [] args) throws Exception {
        Receiver receiver = new Receiver("testQueue");
        Thread thread = new Thread(receiver);
        thread.start();
        Sender sender = new Sender("testQueue");
        for (int i = 0; i < 3; i++) {
            MessageInfo info = new MessageInfo();
            info.setChannel("testChannel");
            info.setContent("msg"+i);
            sender.sendMsg(info);
        }
    }
}
//绑定(路由规则):将交换机xxx中具有路由键xxx的消息送到队列xxx中
