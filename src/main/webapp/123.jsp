<%--
  Created by IntelliJ IDEA.
  User: lis_macbook
  Date: 13/06/2020
  Time: 21:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация JSP</title>
</head>
<body>
<h1>Регистрация успешно завершена</h1>
<a href="login.html">Страница входа </a>
<%
    String name=request.getParameter("mentorId");
    out.print("welcome "+name);
%>
<p>mentorId: ${mentorId}</p>
</body>
</html>
