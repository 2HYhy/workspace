1. Http和Tcp协议
> TPC/IP协议是传输层协议，主要解决数据如何在网络中传输，而HTTP是应用层协议，主要解决如何包装数据。

2. 关于Servlet
> 是运行在web服务器上的程序,用于交互式地浏览和修改数据，生成动态web内容。    
> 客户端(发送请求)——>服务器(调用并启动)——>Servlet(根据请求生成响应内容)——>服务器(返回响应)——>客户端
> 常用对的容器有tomcat和jetty,即提供了Servlet功能的服务器。
> 环境设置包括Jdk(Java Development Kit)和Tomcat。
> 生命周期包括: init()方法在第一次创建Servlet时调用;service()方法处理客户端请求，调用doGet()/doPost()方法;destroy()方法终止Servlet。
> 请求转发: 一个web资源收到客户端请求后，通知服务器去调用另一个web资源进行处理。
> 请求重定向: 一个web资源收到客户端请求后，通知浏览器去调用另一个web资源进行处理。

3. 关于Cookie和Session
> cookie是一种将会话数据保存在浏览器客户端的技术。
> cookie的内容主要包括: 名字，值，过期时间，路径(path)和域(domain)。domain+path决定了Cookie的作用范围。
> 删除cookie时，path必须保持一致，否则不会删除。
> 是为弥补Http协议无状态的不足而出现的。  
> cookie默认的过期时间(expire)是浏览器会话期间。设有expire的Cookie保存在硬盘中，没设expire的Cookie保存在内存中。 
> Cookie不能修改和删除。若要修改某个Cookie，只需新建一个同名的Cookie，添加到response中覆盖原来的Cookie。若要删除某个Cookie，只需新建一个同名的Cookie，将maxAge设置为0，添加到response中覆盖原来的Cookie。

>- 1.服务器端通过HTTP响应报文将Cookie发送给浏览器 

>- 2.浏览器将Cookie保存，之后每次http请求都会将其带上发送给服务器

> session是一种将会话数据保存在服务器端的技术。
> 服务器向客户端浏览器发送一个名为JSESSIONID的Cookie，值为该Session的id(即HttpSession.getId()的返回值)，Session依据该Cookie来识别是否为同一用户。
> session的存储方式通常包括: 使用Cookie，URL重写(一般在Cookie被禁用时使用)

