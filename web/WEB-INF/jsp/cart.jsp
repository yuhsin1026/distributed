<%@ page import="Session_Bean.CartBean" %><%--
  Created by IntelliJ IDEA.
  User: Shin
  Date: 2020/6/26
  Time: 下午 07:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<style><%@include file="/resources/css/mainPage.css"%></style>
<head>
    <title>Ordering Cart</title>
<%--    <link href="${pageContext.request.contextPath}/resources/css/mainPage.css" rel="stylesheet" type="text/css"/>--%>
</head>
<body>
<div class="log-box-long">
<% if (request.getAttribute("alert")=="nobook"){%>
<script type="text/javascript">
    alert("There is no book in the cart.");
</script>
<%}%>
Dear ${sessionScope.Cur_User.lastName},<br>
You can book another
${5-sessionScope.BorrowCart.books.size()-sessionScope.Cur_User.borrowbooks.size()}
books<br>
<table width="100%" align="center">
<c:forEach items="${sessionScope.BorrowCart.books}" var="book" varStatus="status">
    <tr>
        <td>${book.first()}</td>
        <td>${book.second()}</td>
        <td>
            <form action="cart" method="get">
                <input type="hidden" name="bookname" value=${book.first()}>
                <input type="hidden" name="location" value=${book.second()}>
                <input type="hidden" name="status" value="remove">
                <button type="submit" >Remove</button>
            </form>
        </td>
    </tr>
</c:forEach>
</table>
<form action="cart" method="get">
    <input type="hidden" name="status" value="finish">
    <button type="submit" >Complete</button>
</form>
<form action="search" method="get">
    <button type="submit" >Back</button>
</form>
</div>
</body>
</html>
