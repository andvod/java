package com.chat;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 108L;
       
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("user");
        String pass = request.getParameter("password");
        
        PrintWriter out = response.getWriter();
        
        int id;
        try
        {
	        ResultSet rs = SignIn.getFreePlace();
	        if (rs.next())
	        {
	        	id = (int)rs.getInt("id")+1;
			}
	        else	id = 1;
	        
	        String sql = "insert into Employees(id, age, first, last) "
	        		+ "values (" + id + ", 20, '" + email + "', '" + pass + "')";
	        Statement stmt = DatabaseAccess.connect();
	        DatabaseOperations.insert(stmt, sql);
        }
        catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		} 
        RequestDispatcher reqD = request.getRequestDispatcher("template/temp.html");
        reqD.include(request, response);
	}

}
