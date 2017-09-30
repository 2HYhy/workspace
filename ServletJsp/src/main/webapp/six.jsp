<%--
  Created by IntelliJ IDEA.
  User: CH-yfy
  Date: 2017/9/30
  Time: 上午9:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>request请求转发与重定向</title>
</head>
<body>
<p>${Data1}</p>
<p>${Data2}</p>
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
<hr/>
</body>
</html>
