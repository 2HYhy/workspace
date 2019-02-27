<%--
  Created by IntelliJ IDEA.
  User: CH-yfy
  Date: 2017/9/30
  Time: 上午9:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSP属性范围（1）</title>
</head>
<body>
<%
    pageContext.setAttribute("data1","Math");
    request.setAttribute("data2","Music");
    session.setAttribute("data3","noodles");
    application.setAttribute("data4","long hair");
%>

pageContext= <%=pageContext.getAttribute("data1")%><br/>
request= <%=request.getAttribute("data2")%><br/>
session= <%=session.getAttribute("data3")%><br/>
applicaton= <%=application.getAttribute("data1")%><br/>
<hr/>
<p>pageContext是当前页，跳转至其他页面就失效</p>
<p>request是一次服务器请求，经服务器跳转仍有效，但超链接跳转无效</p>
<p>session是一次会话</p>
<p>application是上下文中</p>

<span>forword指令用于把请求转发给另一个资源，即服务器会直接跳转至另一个页面，此页面会直接跳过</span>
<jsp:forward page="four.jsp"></jsp:forward>
</body>
</html>
