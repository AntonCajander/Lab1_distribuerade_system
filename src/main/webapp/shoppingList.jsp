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
    System.out.println("AAA ");


    Collection<ItemInfo> listItems = ItemHandler.getShoppingCartItems(1);
    System.out.println("Längd " + listItems.size());

    Iterator<ItemInfo> it = listItems.iterator();
    for (;it.hasNext();){
        ItemInfo item = it.next(); %>
        <%= item.getItemId() %>
        <%= item.getName() %>s
        <br>
<%
    }
%>

<%
    ArrayList<String> list = new ArrayList<>();
    list.add("Banan");
    list.add("Ananas");
    list.add("Citron");
    list.add("Lime");


%>
<form action="#">
    <select name="item">
        <%
        for (String item: list ) {%>
            <option> <%= item %> </option>
        <%
        }
        %>
    </select>
    <input type="submit" value="go"><br/>

</form>
<%
    if(request.getParameter("item") != null){
        String itemString = request.getParameter("item");
        System.out.println("VALD " + itemString);
        int itemId = -1;
        for(int i = 0; i < list.size(); i++){
            if(itemString.equalsIgnoreCase(list.get(i))){
                itemId = i;
            }
        }
        ItemHandler.addItemToCart(itemId, 1);
    }
%>
</body>
</html>
