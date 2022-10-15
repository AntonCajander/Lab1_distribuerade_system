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
import java.util.List;


public class Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        String servletPath = request.getServletPath();
        RequestDispatcher requestDispatcher;
        switch (servletPath) {
            case "/getShoppingCartItems" -> {
                getShoppingCartItems(request, response);
                requestDispatcher = request.getRequestDispatcher("index.jsp");
                requestDispatcher.forward(request, response);
            }
            case "/getAllItems" -> {
                getAllItems(request, response);
                requestDispatcher = request.getRequestDispatcher("index.jsp");
                requestDispatcher.forward(request, response);
            }
            default -> {
                getShoppingCartItems(request, response);
                requestDispatcher = request.getRequestDispatcher("index.jsp");
            }
        }
    }

    private void getShoppingCartItems(HttpServletRequest req, HttpServletResponse res)
    {
        System.out.println("getShoppingCartItems");
        HttpSession session = req.getSession();
        int userId = (int) session.getAttribute("userid");
        System.out.println(userId);
        Collection<ItemInfo> shoppingCartItemsForUser = ItemHandler.getShoppingCartItems(userId);
        req.setAttribute("shoppingCartItemsForUser", shoppingCartItemsForUser); //TODO kolla "shoppingCArtItemsForUser"
    }

    private void getAllItems(HttpServletRequest req, HttpServletResponse res)
    {
        System.out.println("get Store!");
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
    }

    private void postLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Inne i do Login");
        String username = request.getParameter("uname");
        String password = request.getParameter("pass");

        System.out.println("Username " + username + " password " + password);

        if(username != null && password != null){
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
