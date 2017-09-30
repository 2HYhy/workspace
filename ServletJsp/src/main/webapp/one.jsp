<%--
  Created by IntelliJ IDEA.
  User: CH-yfy
  Date: 2017/9/30
  Time: 上午9:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="person" class="com.me.gacl.domain.Person" scope="page"/>
<html>
<head>
    <title>Jsp与JavaBean相关的三大标签之useBean</title>
</head>
<body>
<%
    person.setName("hyh");
    person.setAge(24);
    person.setBirth(new Date());
    person.setMarry(false);
%>

Name= <%=person.getName()%><br/>
Age= <%=person.getAge()%><br/>
Birth= <%=person.getBirth()%><br/>
Mary= <%=person.isMarry()%><br/>

<a href="two.jsp">页面跳转</a>
</body>
</html>
