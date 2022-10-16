package ui;

import bo.Item;
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
import java.util.List;


public class Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        String servletPath = request.getServletPath();
        System.out.println(servletPath);
        RequestDispatcher requestDispatcher;
        switch (servletPath) {
            case "/shoppingCartItems" -> {
                getShoppingCartItems(request, response);
                requestDispatcher = request.getRequestDispatcher("shoppingList.jsp");
                requestDispatcher.forward(request, response);
            }
            case "/getAllItems" -> {
                getAllItems(request, response);
                requestDispatcher = request.getRequestDispatcher("store.jsp");
                requestDispatcher.forward(request, response);
            }
            default -> { //TODO Kolla om den ska vara kvar + fixa
                getShoppingCartItems(request, response);
                requestDispatcher = request.getRequestDispatcher("index.jsp");
            }
        }
    }

    private void getShoppingCartItems(HttpServletRequest req, HttpServletResponse res)
    {
        HttpSession session = req.getSession();
        int userId = (int) session.getAttribute("userid");
        Collection<ItemInfo> shoppingCartItemsForUser = ItemHandler.getShoppingCartItems(userId);
        req.setAttribute("shoppingCartItemsForUser", shoppingCartItemsForUser); //TODO kolla "shoppingCArtItemsForUser"
    }

    private void getAllItems(HttpServletRequest req, HttpServletResponse res)
    {
        Collection<ItemInfo> allItems = ItemHandler.getAllItems();
        req.setAttribute("allItems", allItems);
    }

    @Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Inne i do Post");
        String getPath = request.getServletPath();

        if(getPath.equalsIgnoreCase("/login")){
            postLogin(request, response);
        }
        else if (getPath.equalsIgnoreCase("/addItem")) {
            postAddItem(request, response);
        }
        else{
            System.out.println("PATH does not exisit");
        }
    }

    private void postAddItem(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("I Add Item");

        String itemName = request.getParameter("itemName");
        String userIdString = request.getParameter("userid");
        int userId = Integer.parseInt(userIdString);

        if(itemName != null || userId != -1 || userIdString != null){
            boolean success = ItemHandler.addItemToCart(itemName, userId);
            if(success){
                System.out.println("Lyckat"); //TODO Vet inte vad man ska göra här
            }
        }
    }

    private void postLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Inne i do Login");
        String username = request.getParameter("uname");
        String password = request.getParameter("pass");

        System.out.println("Username " + username + " password " + password);

        if(username != null || password != null){
            int userId = UserHandler.findUserByName(username, password);
            if(userId != -1){
                HttpSession session = request.getSession();
                session.setAttribute("userid", userId);
                RequestDispatcher dis = request.getRequestDispatcher("shoppingList.jsp");
                dis.forward(request,response);
            }
            else{
                response.sendRedirect("index.jsp");
            }
        }
    }
}
