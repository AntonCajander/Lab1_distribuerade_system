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


<%

    String username = request.getParameter("uname");
    String pwd = request.getParameter("pass");

    if (pwd != null && username != null) {
        int id = ItemHandler.findUserByName(username, pwd);

        if (id != -1) {
            session.setAttribute("userid", id);
            %>
            Logged in as <%= username %>
            <br>
            <a href="store.jsp">Go to store</a>
            <%
        }
        else { %>
            <form method="post" action="index.jsp">
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
                        <td><input type="submit" value="Login"
                        /></td>
                    </tr>
                    </tbody>
                </table>
            </form>
            <a href="index.jsp">aaa</a>

        <% }

    } else { %>
<form method="post" action="index.jsp">
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
</form>
<% }
    String regUsername = request.getParameter("regUname");
    String regPwd = request.getParameter("regPass");
    if (regUsername != null && regPwd != null) {
        ItemHandler.createNewUser(regUsername, regPwd);
    }
%>

<br>
</body>
</html>