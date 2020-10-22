<%--
  Created by IntelliJ IDEA.
  User: Shin
  Date: 2020/6/28
  Time: 下午 06:16
  To change this template use File | Settings | File Templates.
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style><%@include file="/resources/css/searchPage.css"%></style>
<head>
    <title>Leaderboard</title>
</head>
<body>
    <h2>Leaderboard</h2>
    <form method="get" action="rank">
        <select name="Location" onchange="submit();">
            <option value="default">option</option>
            <option value="all">all</option>
            <c:forEach items="${requestScope.librarys}" var="library" varStatus="status">
                <option value=${library.libraryName}>${library.libraryName}</option>
            </c:forEach>
        </select>
    </form>

    <table width="40%">
        <tr>
            <th>Book name</th>
            <th>Monthly Frequency</th>
            <th>Total Frequency</th>
        </tr>

        <c:forEach items="${rankings}" var="rank" varStatus="status">
            <tr>
                <td>${rank[0]}</td>
                <td>${rank[1]}</td>
                <td>${rank[2]}</td>
            </tr>
        </c:forEach>
    </table>

    <form action="home" method="get">
        <button type="submit">back</button>
    </form>

</body>
</html>
