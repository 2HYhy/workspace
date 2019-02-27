<%--
  Created by IntelliJ IDEA.
  User: CH-yfy
  Date: 2017/12/20
  Time: 下午4:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>域对象属性值改变的测试</title>
</head>
<body>
<%
    //ServletContext的增改删
    application.setAttribute("username", "sunshine");
    application.setAttribute("username", "sunny");
    application.removeAttribute("username");
%>
<%
    //HttpSession的增改删
    session.setAttribute("password","12345678");
    session.setAttribute("password","87654321");
    session.removeAttribute("password");
%>
<%
    //ServletRequest的增删改
    request.setAttribute("address", "WallStreet");
    request.setAttribute("address", "ChinaTown");
    request.removeAttribute("address");
%>
console已打印输出
</body>
</html>
