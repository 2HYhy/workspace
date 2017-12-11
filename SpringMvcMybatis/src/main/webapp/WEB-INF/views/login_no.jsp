<%--
  Created by IntelliJ IDEA.
  User: CH-yfy
  Date: 2017/9/29
  Time: 下午6:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户登录界面</title>
</head>
<body>
<form action="/user/login" method="post">
    <font color="red">${error}</font><br/>
    用户姓名：<input type="text" name="username" title="2"/><br/>
    用户密码：<input type="password" name="password" title="3"><br/>
    <input type="submit" value="登录">
</form>
</body>
</html>
