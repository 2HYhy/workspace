<%--
  Created by IntelliJ IDEA.
  User: CH-yfy
  Date: 2017/12/15
  Time: 上午9:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>servlet</title>
  </head>
  <body>
  已成功启动servlet ！！！
  <br/>
  若JSP没有显示的使用<%@page session="false"%>关闭session则一访问JSP页面，HttpSession对象就会创建。
  <br/>
  sessionID=${pageContext.session.id}
  </body>
</html>
