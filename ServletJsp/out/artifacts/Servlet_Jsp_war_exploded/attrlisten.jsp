<%@ page import="com.me.gacl.listener.BeanBindingListener" %>
<%--
  Created by IntelliJ IDEA.
  User: CH-yfy
  Date: 2017/12/21
  Time: 上午9:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>HttpSessionBindingListener监听</title>
</head>
<body>
<%
    //将javabean对象绑定到session中
    session.setAttribute("JavaBean", new BeanBindingListener("Apple-Watch"));
    //从session中移除javabean对象
    session.removeAttribute("JavaBean");
%>
console打印输出
</body>
</html>
