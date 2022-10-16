<%@ page import="ui.ItemInfo" %>
<%@ page import="java.util.Iterator" %>
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
<form method="get" action="getAllItems">
    <input type="submit" value="getAllItems">
    <%
        if (request.getAttribute("allItems") != null){
            Collection<ItemInfo> listItems = (Collection<ItemInfo>) request.getAttribute("allItems");

            Iterator<ItemInfo> it = listItems.iterator();
            for (;it.hasNext();){
                ItemInfo item = it.next(); %>
    <%= item.getName() %>
    <br>
    <%
            }
        }
    %>
</form>
</body>
</html>
