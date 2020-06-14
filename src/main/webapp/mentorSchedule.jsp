<%--
  Created by IntelliJ IDEA.
  User: lis_macbook
  Date: 06/06/2020
  Time: 18:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Schedule mentor</title>
</head>
<body>
    <form action="schedule\mentorScheduleCreate" method="post">
        <p>
        <input type="text" name="Monday" value="Понедельник">
            <input type="text" name="start1">
            <input type="text" name="end1">
        </p>
        <input type="submit" name="Создать расписание">
    </form>
</body>
</html>
