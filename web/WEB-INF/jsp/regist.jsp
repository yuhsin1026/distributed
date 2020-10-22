<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<style><%@include file="/resources/css/mainPage.css"%></style>
<head>
    <title>User Registration</title>
<%--    <link href="${pageContext.request.contextPath}/resources/css/mainPage.css" rel="stylesheet" type="text/css"/>--%>
</head>
<body>
<div class="log-box-long">
Registration
<table>
<form action="regist" method="post">

    <tr><td>*Account:</td>
    <td><input type="text" name="userId" id="userId"></td></tr>
    <tr><td>*Last Name:</td>
        <td><input type="text" name="lastName" id="lastName"></td></tr>
    <tr><td>*First Name:</td>
        <td><input type="text" name="firstName"id="firstName"></td></tr>
    <tr><td>Phone Number:</td>
        <td><input type="text" name="phoneNum"></td></tr>
    <tr><td>Email address:</td>
        <td><input type="text" name="email"></td></tr>
    <tr><td>*Password:</td>
    <td><input type="password" name="userpassword" id="NewPassword" onchange="isPasswordMatch()"></td></tr>
    <tr><td>*Repeat Password:</td>
        <td><input type="password" name="password" id="ConfirmPassword" onchange="isPasswordMatch()"></td></tr>

        <script>
            function isPasswordMatch() {
                var password = document.getElementById("NewPassword").value;
                var confirmPassword = document.getElementById("ConfirmPassword").value;;

                if (password != confirmPassword || password=="") {
                    document.getElementById("id_button").disabled = true;
                    //alert("Passwords do not match!");
                }
                else
                    document.getElementById("id_button").disabled = false;
            }

            function myCheck(){
                var ids =['userId', 'lastName','firstName'];
                var names =['Account', 'Last Name','First Name'];
                var value="";
                ids.forEach(function(item,index) {
                    if(document.getElementById(item).value=="")
                        value=value+names[index]+" can not be blank. \n"; //將value值抓出
                });
                if(value!=""){
                    alert(value);
                    return false;
                }
                else
                    return true;
            }
        </script>
    <tr><td colspan="2" style="text-align: center">
<%--        <input type="submit" value="sign in" id="id_button" disabled="true">--%>
        <button type="submit" id="id_button" disabled="true">Sign In</button>
</form>
    <form action="login" method="post" >

<%--    <input type="submit" value="back">--%>
        <button type="submit">Back</button>
    </form></td></tr>

</table>

</div>
</body>
</html>
