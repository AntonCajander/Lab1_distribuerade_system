<%@ page import="ui.ItemInfo" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="bo.ItemHandler" %>
<%@ page import="java.util.Collection" %><%--
  Created by IntelliJ IDEA.
  User: Marcus
  Date: 2022-10-07
  Time: 14:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<body>
<label> <b>Stores Items:</b> </label>
<br>
<br>
<%
    Collection<ItemInfo> allItemsList = ItemHandler.getAllItems();

    Iterator<ItemInfo> it = allItemsList.iterator();
        for (;it.hasNext();){
        ItemInfo item = it.next(); %>
            <%= item.getName() %>
        <br>
            <%
    }
%>
<br>
<form method="post" action="store.jsp">
    <select name="item">
        <%
            Iterator<ItemInfo> itAllItems = allItemsList.iterator();
            for (;itAllItems.hasNext();){
                ItemInfo allItem = itAllItems.next();%>
        <option> <%= allItem.getName() %> </option>
        <%
            }
        %>
    </select>

    <input type="submit" value="add item"><br/>

</form>
<%
    int userId = (int) session.getAttribute("userid");

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
    <br>
    <a href="shoppingList.jsp">Shopping list</a>
    <br>
    <br>
    <br>
    <br>
    <a href="index.jsp">Change Account</a>

</body>
</html>
