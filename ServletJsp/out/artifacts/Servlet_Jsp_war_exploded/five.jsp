<%--
  Created by IntelliJ IDEA.
  User: CH-yfy
  Date: 2017/9/30
  Time: 上午9:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSP属性范围（3）</title>
</head>
<body>
<%
    String ses=session.getAttribute("data3").toString();
    String app=application.getAttribute("data4").toString();
%>
session= <%=ses%><br/>
application= <%=app%><br/>
</body>
</html>
