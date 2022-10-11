<%@ page import="bo.ItemHandler" %><%--
  Created by IntelliJ IDEA.
  User: Marcus
  Date: 2022-10-11
  Time: 16:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form method="post" action="register.jsp">
    <table border="1">
        <tbody>
        <tr>
            <td>Registrerings namn</td>
            <td><input type="text" name="regUname" value=""
            /></td>
        </tr>
        <tr>
            <td>Registrerings Password</td>
            <td><input type="password" name="regPass" value="">

        <tr>
            <td><input type="submit" value="Register"
            /></td>
        </tr>
        </tbody>
    </table>
</form>
<%
    String regUsername = request.getParameter("regUname");
    String regPwd = request.getParameter("regPass");
    if (regUsername != null && regPwd != null) {
        ItemHandler.createNewUser(regUsername, regPwd);
    }
%>
<a href="index.jsp">Login</a>

</body>
</html>
