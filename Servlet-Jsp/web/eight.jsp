<%--
  Created by IntelliJ IDEA.
  User: CH-yfy
  Date: 2017/9/30
  Time: 上午9:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSP include标签</title>
</head>
<body>
<h5>inclue标签用于把另一个页面的内容插入到本页内容中</h5>
<%!
    int i=100;
%>
<p>本页的i值为：<%=i%></p>
<jsp:include page="nine.jsp"/>
<%--&lt;%&ndash;<%@ include file="nine.jsp"%>  &ndash;%&gt; 一旦添加，i值即报错--%>
</body>
</html>
