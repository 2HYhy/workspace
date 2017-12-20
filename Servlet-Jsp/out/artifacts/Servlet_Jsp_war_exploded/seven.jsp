<%--
  Created by IntelliJ IDEA.
  User: CH-yfy
  Date: 2017/9/30
  Time: 上午9:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSP param标签</title>
</head>
<body>
<h5>当使用include/forward标签引入/将请求转发给其他资源时，可以使用param向该资源传递参数</h5>
<%--<p>向被包含的页面传参</p>--%>
<%--<jsp:include page="contain.jsp">--%>
<%--<jsp:param name="user1" value="zhangsan"/>--%>
<%--<jsp:param name="user2" value="wangwu"/>--%>
<%--</jsp:include>--%>
<p>向要跳转的页面传参</p>
<jsp:forward page="transfer.jsp">
    <jsp:param name="info1" value="thanks"/>
    <jsp:param name="info2" value="welcome"/>
</jsp:forward>
</body>
</html>
