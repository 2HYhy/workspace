<%--
  Created by IntelliJ IDEA.
  User: CH-yfy
  Date: 2017/12/22
  Time: 上午11:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加用户</title>
</head>
<body>
<%--添加一个用户--%>
用户id： ${usersget.id}<br/>
用户名： ${usersget.username}<br/>
用户密码：${usersget.password}

<%--有构函时，添加多个用户--%>
用户id：  ${user1.id}  <br/>
用户名：  ${user1.username}<br/>
用户密码：${user1.password}<br/>
用户id：  ${user2.id}  <br/>
用户名：  ${user2.username}<br/>
用户密码：${user2.password}
</body>
</html>
