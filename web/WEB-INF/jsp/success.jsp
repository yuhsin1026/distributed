<%@ page import="Entity_Bean.UserTbEntity" %>
<%@ page import="Session_Bean.UserDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<style><%@include file="/resources/css/mainPage.css"%></style>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Index</title>
<%--    <link href="${pageContext.request.contextPath}/resources/css/mainPage.css" rel="stylesheet" type="text/css"/>--%>
</head>
<body>
<div class="log-box-long">
<%UserTbEntity user =
        (UserTbEntity) session.getAttribute("Cur_User");
    if(user !=null){
    %>
<tr>
    <td><%=user.getLastName()%><br></td>
    <td><%=user.getFirstName()%><br></td>
    <td><%=user.getEmail()%><br></td>
    <%}%>
</tr>
Login Successful<br>
<form action="home" method="get" >
    <button type="submit">Back</button>
</form>
<form action="cart">
    <button type="submit">View Cart</button>
</form>


</div>
</body>
</html>


