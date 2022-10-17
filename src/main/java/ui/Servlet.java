package ui;


import bo.ItemHandler;
import bo.UserHandler;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Collection;


public class Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();
        RequestDispatcher requestDispatcher;

        switch (servletPath) {
            case "/shoppingCartItems" -> {
                getShoppingCartItems(request, response);
                requestDispatcher = request.getRequestDispatcher("shoppingList.jsp");
                requestDispatcher.forward(request, response);
            }
            case "/getAllItems" -> {
                getAllItems(request);
                requestDispatcher = request.getRequestDispatcher("store.jsp");
                requestDispatcher.forward(request, response);
            }
            default -> {
                requestDispatcher = request.getRequestDispatcher("index.jsp");
                requestDispatcher.forward(request, response);
            }
        }
    }

    private void getShoppingCartItems(HttpServletRequest req, HttpServletResponse res) {
        HttpSession session = req.getSession();
        int userId = (int) session.getAttribute("userid");
        Collection<ItemInfo> shoppingCartItemsForUser = ItemHandler.getShoppingCartItems(userId);
        req.setAttribute("shoppingCartItemsForUser", shoppingCartItemsForUser);
    }

    private void getAllItems(HttpServletRequest req) {
        Collection<ItemInfo> allItems = ItemHandler.getAllItems();
        req.setAttribute("allItems", allItems);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String getPath = request.getServletPath();
        RequestDispatcher requestDispatcher;


        if (getPath.equalsIgnoreCase("/login")) {
            boolean success = postLogin(request);

            if (success) {
                requestDispatcher = request.getRequestDispatcher("shoppingList.jsp");
                requestDispatcher.forward(request, response);
            } else {
                requestDispatcher = request.getRequestDispatcher("index.jsp");
                requestDispatcher.forward(request, response);
            }
        } else if (getPath.equalsIgnoreCase("/addItem")) {
            boolean success = postAddItem(request);

            if (success) {
                requestDispatcher = request.getRequestDispatcher("shoppingList.jsp");
                requestDispatcher.forward(request, response);
            } else {
                requestDispatcher = request.getRequestDispatcher("index.jsp");
                requestDispatcher.forward(request, response);
            }
        } else {
            requestDispatcher = request.getRequestDispatcher("index.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    private boolean postAddItem(HttpServletRequest request) {
        String itemName = request.getParameter("item");
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userid");

        if (itemName != null || userId != -1) {
            return ItemHandler.addItemToCart(itemName, userId);
        }
        return false;
    }

    private boolean postLogin(HttpServletRequest request){
        String username = request.getParameter("uname");
        String password = request.getParameter("pass");

        if (username != null || password != null) {
            int userId = UserHandler.findUserByName(username, password);
            if (userId != -1) {
                HttpSession session = request.getSession();
                session.setAttribute("userid", userId);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
