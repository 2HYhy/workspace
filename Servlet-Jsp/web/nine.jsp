<%--
  Created by IntelliJ IDEA.
  User: CH-yfy
  Date: 2017/9/30
  Time: 上午9:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>include标签与指令</title>
</head>
<body>
<%!
    int i=200;
%>
<p>demo.jsp页面的i值为：<%=i%></p>
<span>标签是动态引入，涉及到的n个JSP页面会翻译成n个servlet，其内容在执行时合并！</span>
<span>指令是静态引入，涉及到的n个JSP页面会翻译成1个servlet，其内容在源文件级别合并！</span>
<h6>include指令页面中，由于本身定义了变量i,而本页面的i值就变成重复定义，所以报错！</h6>
</body>
</html>
