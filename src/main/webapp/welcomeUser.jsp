<%--
  Created by IntelliJ IDEA.
  User: Marcus
  Date: 2022-10-07
  Time: 14:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<body>
<%
    String userId=request.getParameter("userId");
    System.out.println("Welcome " + userId);

    session.setAttribute("user", userId);
%>
    <a href="shoppingList.jsp">Shopping list</a>

</body>
</html>
