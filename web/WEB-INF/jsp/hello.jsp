<%@ page import="Entity_Bean.UserTbEntity" %><%--
  Created by IntelliJ IDEA.
  User: Shin
  Date: 2020/6/25
  Time: 下午 12:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Hello World!</h2>

session:${sessionScope.Cur_User.borrowbooks.size()}<br>
db:${requestScope.now}<br>
<table width="30%">
    <tr>
        <th>Book name</th>
        <th>Location</th>
        <th># of book left</th>
        <th>button</th>


    </tr>
    <c:forEach items="${sessionScope.Cur_User.borrowbooks}" var="book" varStatus="status">
        <tr>
            <td>${book.bookName}</td>
            <td>${book.location.libraryName}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
