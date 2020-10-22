<%--
  Created by IntelliJ IDEA.
  User: Shin
  Date: 2020/6/27
  Time: 下午 06:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<style><%@include file="/resources/css/searchPage.css"%></style>
<head>
    <title>User Info</title>
<%--    <link href="${pageContext.request.contextPath}/resources/css/searchPage.css" rel="stylesheet" type="text/css"/>--%>
</head>
<body>
<div>
<h2>User Information</h2>
<table width="40%">
    <tr><td>User name :     </td><td>   ${sessionScope.Cur_User.lastName} ${sessionScope.Cur_User.firstName}</td></tr>
    <tr><td>User ID :       </td><td>   ${sessionScope.Cur_User.userId}        </td></tr>
    <tr><td>Email address : </td><td>   ${sessionScope.Cur_User.email}          </td></tr>
    <tr><td>User password : </td><td>   ${sessionScope.Cur_User.userPassword}   </td></tr>
    <tr><td>Phone number :  </td><td>   ${sessionScope.Cur_User.phoneNum}</td></tr>
    <tr><td># of borrowed : </td><td>   ${sessionScope.Cur_User.borrowbooks.size()}</td></tr>
</table>
<h2>Borrowed Books</h2>
<table width="50%">
    <tr>
        <th>Book name</th>
        <th>Location</th>
        <th># of days left</th>
        <th>Return</th>
    </tr>
    <c:forEach items="${sessionScope.Cur_User.borrowbooks}" var="book" varStatus="status">
        <tr>
            <td align="center">${book.bookName}</td>
            <td align="center">${book.location.libraryName}</td>
            <td align="center">${book.dayLeft}</td>
            <td align="center">
            <form method="post" action="userinfo">
                <input type="hidden" name="book" value=${book.bookId}>
                <button type="submit">Yes</button>
            </form></td>
        </tr>
    </c:forEach>
</table>
<form action="home">
    <button type="submit">Back</button>
</form>
</div>
</body>
</html>
