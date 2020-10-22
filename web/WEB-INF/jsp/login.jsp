<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<style><%@include file="/resources/css/mainPage.css"%></style>
    <head>
        <title>User Login</title>
<%--        <link href="${pageContext.request.contextPath}/resources/css/mainPage.css" rel="stylesheet" type="text/css"/>--%>
    </head>
    <body>
    <% if (request.getAttribute("alert")=="needlogin"){%>
        <script type="text/javascript">
            alert("You have to login before completing borrowing!!");
        </script>
    <%}%>

    <div class="log-box-short">
        User Login
        <table align="center">
            <form action="login" method="post">
                <tr><td>Account:</td>
                <td><input type="text" name="username"></td></tr>
                <tr><td>Password:</td>
                <td><input type="password" name="password"></td></tr>

                <tr><td colspan="2" style="text-align: center">
<%--                <input type="submit" value="sign in"></td></tr>--%>
                    <button type="submit">Sign In</button></td></tr>
            </form>
            <form action="regist" method="post" >
                <tr><td colspan="2" style="text-align: center">
                <button type="submit">Register</button></td></tr>
            </form>
            <form action="home" method="get" >
                <tr><td colspan="2" style="text-align: center">
                <button type="submit">Back</button></td></tr>
            </form>
        </table>
    </div>
    </body>
</html>
