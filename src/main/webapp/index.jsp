<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

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
</form>
<br>
</body>
</html>