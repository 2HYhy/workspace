<%--
  Created by IntelliJ IDEA.
  User: CH-yfy
  Date: 2017/9/29
  Time: 下午6:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>数据绑定方式</title>
</head>
<body>
<h3>test1-></h3>
username: ${userName1}<br/>
password: ${passWord1}<br/>
<h3>test2-></h3>
username: ${userName2}<br/>
password: ${passWord2}<br/>
<h3>test3-></h3>
username: ${userName3}<br/>
password: ${passWord3}<br/>
<h3>test4-></h3>
information: ${infos}
<h3>test5-></h3>
username: ${users.id}<br/>
password: ${users.password}<br/>
</body>
</html>
