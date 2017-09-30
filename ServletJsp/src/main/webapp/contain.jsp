<%--
  Created by IntelliJ IDEA.
  User: CH-yfy
  Date: 2017/9/30
  Time: 上午9:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>被包含include</title>
</head>
<body>
<p>接收从seven.jsp页面传来的参数</p>
<%=request.getParameter("user1")%>
<br/>
<%=request.getParameter("user2")%>
</body>
</html>
