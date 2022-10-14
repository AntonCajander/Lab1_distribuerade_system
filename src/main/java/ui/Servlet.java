package ui;

import bo.ItemHandler;

import bo.UserHandler;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


public class Servlet extends HttpServlet {

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
