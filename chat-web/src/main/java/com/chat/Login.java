package com.chat;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import java.io.PrintWriter;


@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 101L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String email = request.getParameter("login");
        String pass = request.getParameter("password");
        Validate.setUser(email, pass);
        
        RequestDispatcher rs = request.getRequestDispatcher("Validate");
        rs.forward(request, response);
    } 
    
}
