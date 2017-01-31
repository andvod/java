package com.chat;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Validate
 */
@WebServlet("/Validate")
public class Validate extends HttpServlet {
	private static final long serialVersionUID = 102L;
	private static String emailUser;
	private static String password; 
	
    public static void setUser(String email,String pass) 
    {
    	emailUser = email;
    	password = pass;
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Cookie[] requestCookies = request.getCookies();
		
		if(requestCookies != null){
			for(Cookie c : requestCookies){
				if ( (emailUser.equals(c.getName())) && (password.equals(c.getComment())) )
				{
					out.println("Username is correct");
					RequestDispatcher rs = request.getRequestDispatcher("wschat.html");
		            rs.include(request, response);	
				}
			}
		}
		else
		{
			out.println("Username or Password incorrect");
	        RequestDispatcher rs = request.getRequestDispatcher("index.html");
	        rs.forward(request, response);
		}
					
			/*		
			if(c.getName().equals("Test")){
				c.setMaxAge(0);
				response.addCookie(c);
			}*/
	}
}
