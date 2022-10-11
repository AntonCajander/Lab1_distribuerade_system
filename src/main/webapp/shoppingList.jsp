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
<form method="post" action="shoppingList.jsp">
    <select name="item">
        <%
            Collection<ItemInfo> allItemsList = ItemHandler.getAllItems();
            Iterator<ItemInfo> itAllItems = allItemsList.iterator();
            for (;itAllItems.hasNext();){
                ItemInfo allItem = itAllItems.next();%>
            <option name="<%= allItem.getItemId()%>"> <%= allItem.getName() %> </option>
        <%
        }
        %>
    </select>

    <input type="submit" value="add item"><br/>

</form>
<%
    if(request.getParameter("item") != null){
        String itemString = request.getParameter("item");
        int itemId = -1;

        Iterator<ItemInfo> itAllItems2 = allItemsList.iterator();

        for (;itAllItems2.hasNext();) {
            ItemInfo allItem = itAllItems2.next();
            if(itemString.equals(allItem.getName())){
                itemId = allItem.getItemId();
            }
        }
        if(itemId != -1){
            ItemHandler.addItemToCart(itemId, userId);
        }
    }
%>
</body>
</html>
