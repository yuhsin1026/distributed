<%--
  Created by IntelliJ IDEA.
  User: Shin
  Date: 2020/6/30
  Time: 上午 12:01
  To change this template use File | Settings | File Templates.
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style><%@include file="/resources/css/searchPage.css"%></style>
<head>
    <title>Library Info</title>
</head>
<body>
<h2>Address</h2>
<form method="get" action="library">
    <select name="Location" onchange="submit();">
        <option value="default">option</option>
        <option value="all">all</option>
        <c:forEach items="${requestScope.librarys}" var="library" varStatus="status">
            <option value=${library.libraryName}>${library.libraryName}</option>
        </c:forEach>
    </select>
</form>
<table width="50%">
    <tr>
        <th>Library name</th>
        <th>Adddress</th>
        <th>Button</th>


    </tr>
    <c:forEach items="${requestScope.libinfo}" var="lib" varStatus="status">
        <tr>
            <td align="center">${lib.libraryName}</td>
            <td align="center">${lib.address}</td>
            <td><form action="webresources/library/${lib.libraryName}" method="get">
                <button type="submit">Rest</button>
            </form></td>
        </tr>
    </c:forEach>
</table>
<form action="home">
    <button type="submit">Back</button>
</form>
<form action="webresources/library/" method="get">
    <button type="submit">See All</button>
</form>
</body>
</html>
