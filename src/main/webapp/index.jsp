<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="ui.ItemInfo"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.applet.Applet" %>
<%@ page import="bo.ItemHandler" %>

<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Välkommen till affären!" %>
</h1>
<br/>



<form method="post" action="login">
    <table border="1">
        <tbody>
        <tr>
            <td>User Name</td>
            <td><input type="text" name="uname" value=""
            /></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="pass" value="">

        <tr>
            <td><input type="submit" value="Login"
            /></td>
        </tr>
        </tbody>
    </table>
    <a href="register.jsp"> Register account </a>
</form>



<br>
</body>
</html>