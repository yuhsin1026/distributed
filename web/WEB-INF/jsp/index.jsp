<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="Session_Bean.UserDAO" %>
<%@ page import="Entity_Bean.UserTbEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style><%@include file="/resources/css/mainPage.css"%></style>
<head>
  <title>Welcome!!</title>
<%--    <link href="${pageContext.request.contextPath}/resources/css/mainPage.css" rel="stylesheet" type="text/css"/>--%>
</head>
<body>
<div class="log-box-long">
    Welcome to the online library!
<%
UserTbEntity user =
          (UserTbEntity) session.getAttribute("Cur_User");
  if(user !=null){
%>
    Hello! <%=user.getLastName()+' '+user.getFirstName()%><br>
    <form method="post" action="home">
      <input type="hidden" name="status" value="logout">
      <button type="submit">Log out</button>
    </form>
<%}else{ %>
    <form method="post" action="login">
      <button type="submit">Sign in</button>
    </form>
<%}%>

    <form method="get" action="userinfo">
        <button type="submit">User Info</button>
    </form>
    <div style="float: left; width: 130px">
        <form method="get" action="search">
            <button type="submit">Search</button>
        </form>
    </div>
    <div style="float: right; width: 130px">
        <form method="get" action="library">
            <button type="submit">Library info</button>
        </form>
    </div>
    <div style="float: left; width: 130px">
        <form method="get" action="rank">
            <button type="submit">Leaderboard</button>
        </form>
    </div>
    <div style="float: right; width: 130px">
        <form method="post" action="library">
            <button type="submit">Donate</button>
        </form>
    </div>
    <form action="cart">
        <button type="submit">View Cart</button>
    </form>
</div>
</body>
</html>
