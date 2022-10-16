<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="ui.ItemInfo" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.Iterator" %>
<html>
<body>
<form method="get" action="shoppingCartItems">
    <input type="submit" value="getShoppingCart">
    <br>
    <%
        if (request.getAttribute("shoppingCartItemsForUser") != null) {
            Collection<ItemInfo> listItems = (Collection<ItemInfo>) request.getAttribute("shoppingCartItemsForUser");

            Iterator<ItemInfo> it = listItems.iterator();
            for (; it.hasNext(); ) {
                ItemInfo item = it.next(); %>
    <%= item.getName() %>
    <%= item.getNrOfItems() %>
    <br>
    <%
            }
        }
    %>
</form>
<br>
<br>
<a href="store.jsp">Store</a>
</body>
</html>
