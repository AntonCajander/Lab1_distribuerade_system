<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="ui.ItemInfo"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.applet.Applet" %>

<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "HEJ!" %>
</h1>
<br/>
<form action ="welcomeUser.jsp">
    <input type="text" name="userId">
    <input type="submit" value="go"><br/>
</form>
<a href="shoppingList.jsp">Shopping list</a>

<br>
</body>
</html>