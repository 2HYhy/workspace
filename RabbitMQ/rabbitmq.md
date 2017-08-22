## rabbitmqRabbitMQ是用Erlang实现的一个高并发高可靠AMQP消息队列服务器  
1. 下载Erlang:[http://www.rabbitmq.com/releases/erlang/](http://www.rabbitmq.com/releases/erlang/)  
2. 下载rabbitmq-server服务器:[https://www.rabbitmq.com/install-standalone-mac.html](https://www.rabbitmq.com/install-standalone-mac.html)   
> *该rabbitmq-server中已包含erlang,可以不用下载它*  

3. 运行`tar zxvf rabbitmq-server-mac-standalone-3.6.11.tar.gz`解压,得到文件夹。
4. 启动服务，进入rabbitmq-server安装目录路径sbin下,运行:`rabbitmq-server start`。成功启动如下:![Alt-text](/images/rabbit1.png) 
5. Send.java和Receive.java只适合发送字符串类型的消息,运行结果如图:![Alt-text](/images/rabbit2.png) , ![Alt-text](/images/rabbit3.png)  
6. 消息类型也可为一个对象,运行结果如图:![Alt-text](/images/rabbit4.png)

