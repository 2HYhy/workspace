<%--
  Created by IntelliJ IDEA.
  User: CH-yfy
  Date: 2017/9/29
  Time: 下午6:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<form action="/user/register2" method="post">
    用户姓名：<input type="text" name="username" title="1"><br/>
    用户密码：<input type="password" name="password" title="2"><br/>
    <input type="submit" value="提交"><br/>
    <font color="red"> ${error}</font>
</form>
</body>
</html>
