<%--
  Created by IntelliJ IDEA.
  User: CH-yfy
  Date: 2017/12/18
  Time: 下午3:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Servlet:请求转发与重定向</title>
</head>
<body>
Data1:
<p>${Data1}</p>
Data2:
<p>${Data2}</p>
<hr/>
<p>
    <%=
    request.getAttribute("Data1")
    %>
</p>
<p>
    <%=
    request.getAttribute("Data2")
    %>
</p>
</body>
</html>
