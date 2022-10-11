<%--
  Created by IntelliJ IDEA.
  User: Marcus
  Date: 2022-10-07
  Time: 13:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="ui.ItemInfo"%>
<%@ page import="java.util.Collection" %>
<%@ page import="bo.ItemHandler" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.ArrayList" %>
<html>
<body>
<%
    int userId = (int) session.getAttribute("userid");

    Collection<ItemInfo> listItems = ItemHandler.getShoppingCartItems(userId);

    Iterator<ItemInfo> it = listItems.iterator();
    for (;it.hasNext();){
        ItemInfo item = it.next(); %>
        <%= item.getName() %>
        <%= item.getNrOfItems() %>
        <br>
<%
    }
%>


<br>
<br>
<a href="store.jsp">Shopping list</a>

</body>
</html>
