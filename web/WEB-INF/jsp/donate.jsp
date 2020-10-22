<%--
  Created by IntelliJ IDEA.
  User: Shin
  Date: 2020/6/30
  Time: 上午 10:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style><%@include file="/resources/css/searchPage.css"%></style>
<head>
    <title>Donate</title>
    ************************************<br>
    This used the restful Service : POST<br>
    ************************************<br>
    <h2>Donation</h2>
    <form method="post" action="library">
        Book Name: <input type="text" name="bookname"> To (library): <input type="text" name="libname">
        <button type="submit">Confirm</button>
    </form>
    <form action="home">
        <button type="submit">Back</button>
    </form>
    Status : ${requestScope.status}
    <% if (request.getAttribute("status")=="invalid"){%>
    <script type="text/javascript">
        alert("Please enter a valid library");
    </script>
    <%} else if (request.getAttribute("status")=="success"){%>
    <script type="text/javascript">
        alert("Thanks for your donation");
    </script>
    <%}%>
    <br>
    Rest Service respond : ${requestScope.json}
</head>
<body>

</body>
</html>
