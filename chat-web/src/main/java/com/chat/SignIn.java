package com.chat;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;


@WebServlet("/SignIn")
public class SignIn extends HttpServlet {
	private static final long serialVersionUID = 103L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String email = request.getParameter("login");
        String pass = request.getParameter("password");
        
        InetAddress ip = InetAddress.getLocalHost();
        Cookie user = new Cookie(email, ip.toString());
		user.setComment(pass);
		user.setMaxAge(24*60*60);
		user.setPath("/SignIn");
		
		response.addCookie(user);
		
		RequestDispatcher rs = request.getRequestDispatcher("wschat.html");
        rs.include(request, response);
    } 
}
