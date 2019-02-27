<%--
  Created by IntelliJ IDEA.
  User: CH-yfy
  Date: 2017/9/30
  Time: 上午9:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Jsp与JavaBean相关的三大标签之setProperty/getProperty</title>
</head>
<body>
<jsp:setProperty name="person" value="hyh" property="name"/>
<jsp:setProperty name="person" property="age" value="24"/>
<jsp:setProperty name="person" property="birth" value="<%=new Date()%>"/>
<jsp:setProperty name="person" property="marry" param="param_marry"/>

Name= <jsp:getProperty name="person" property="name"/><br/>
Age= <jsp:getProperty name="person" property="age"/><br/>
Birth= <jsp:getProperty name="person" property="birth"/><br/>
Marry= <jsp:getProperty name="person" property="marry"/><br/>
<hr/>
<p>也可以在请求参数中为所有属性赋值，url=/two.jsp?name=x&age=&birth=&marry=x,jsp:setProperty name=person property=*</p>
</body>
</html>
