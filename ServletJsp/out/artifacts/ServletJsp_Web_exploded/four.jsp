<%--
  Created by IntelliJ IDEA.
  User: CH-yfy
  Date: 2017/9/30
  Time: 上午9:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSP属性范围（2）</title>
</head>
<body>
<%
//    String pag=pageContext.getAttribute("data1").toString();由于为空，若存在，会报错。
    String req=request.getAttribute("data2").toString();
    String ses=session.getAttribute("data3").toString();
    String app=application.getAttribute("data4").toString();
%>
request= <%=req%><br/>
session=<%=ses%><br/>
application= <%=app%><br/>

<a href="http://localhost:8080/five.jsp"/>页面跳转</a>
</body>
</html>
