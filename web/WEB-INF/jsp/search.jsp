<%--
  Created by IntelliJ IDEA.
  User: Shin
  Date: 2020/6/25
  Time: 下午 11:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<style><%@include file="/resources/css/searchPage.css"%></style>
<head>
    <title>Collection of books</title>
<%--    <link href="${pageContext.request.contextPath}/resources/css/searchPage.css" rel="stylesheet" type="text/css"/>--%>
</head>
<body>
<form:form method="get" action="search">
    <table>
        <tr>
            <th>Book Name</th>
            <th>Author lastname</th>
            <th>Author firstname</th>
            <th>Location</th>
        </tr>
            <tr>
                <td><input type="text" name="bookname"/></td>
                <td><input type="text" name="authorlast"/></td>
                <td><input type="text" name="authorfirst"/></td>
                <td><input type="text" name="library"/></td>
            </tr>
    </table>
    <br/>
<%--    <input type="submit" value="Search" />--%>
    <button type="submit">Search</button>

</form:form>
<!--${books.size()}-->

<h2>Collection of books</h2>
<table width="50%">
    <tr>
        <th>Book name</th>
        <th>Location</th>
        <th># of book left</th>
        <th>button</th>
    </tr>

    <c:forEach items="${books}" var="book" varStatus="status">
        <tr>
            <td>${book[0]}</td>
            <td>${book[1]}</td>
            <td>${book[2]-book[3]}</td>

            <td>
            <form action="cart" method="get">
                <input type="hidden" name="bookname" value=${book[0]}>
                <input type="hidden" name="location" value=${book[1]}>
                <input type="hidden" name="status" value="add">
                <button type="submit" ${book[2]-book[3] eq 0? 'disabled':''}>Add to cart</button>
            </form>
            </td>
        </tr>
    </c:forEach>
</table>

<br/>
<form action="home">
    <button type="submit">Back</button>
</form>
<form action="cart">
    <button type="submit">View Cart</button>
</form>
</body>
</html>
