package com.chat;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


@WebServlet("/SignIn")
public class SignIn extends HttpServlet {
	private static final long serialVersionUID = 103L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String email = request.getParameter("user");
        String pass = request.getParameter("password");
        
        try
        {
	        ResultSet rs = getLoginPassword(email);
	        if (rs != null)
	        {
		    	if (rs.next())
		    	{
		    		if (rs.getString("last").equals(pass) )
		    		{
		    			setSession(email);
		    			
		    			RequestDispatcher reqD = request.getRequestDispatcher("template/temp.html");
		    	        reqD.include(request, response);
		    		}
		    		else
		    		{
		    			out.print("bledne haslo");
		    			RequestDispatcher reqD = request.getRequestDispatcher("index.html");
		    	        reqD.forward(request, response);
		    		}
		    	}
		    	else
		    	{
		    		out.print("bledny login");
	    			RequestDispatcher reqD = request.getRequestDispatcher("index.html");
	    	        reqD.forward(request, response);
		    	}
	        }
	        else	System.err.println("bledne polecenie sql");
        }
        catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		} 
	}
	
	public static void setSession(String email)
	{
		try
		{
			ResultSet rs1 = getFreePlace();
			if (rs1.next() )	updateSession(email, rs1.getInt("id")+1);
		}
        catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}
	}
	
    private static ResultSet getLoginPassword(String email)
    {
    	ResultSet result = null;
    	try
    	{
	    	Statement stmt = DatabaseAccess.connect();
	    	String sql = "select first, last from Employees where first ='" + email + "'";
	    	result = DatabaseOperations.select(stmt, sql);
    	}
    	catch (IOException ex){
    		ex.printStackTrace();
    	}
    	return result;
    }
    
    public static void updateSession(String email, int id)
    {
    	try
    	{
	    	Statement stmt = DatabaseAccess.connect();
	    	String sql = "update Employees set id = " + id + " where first = '" + email + "'";
	    	DatabaseOperations.insert(stmt, sql);
    	}
    	catch (IOException ex){
    		ex.printStackTrace();
    	}
    }
    
    public static ResultSet getFreePlace()
    {
    	ResultSet result = null;
    	try
    	{
	    	Statement stmt = DatabaseAccess.connect();
	    	String sql = "select max(id) id from Employees";
	    	result = DatabaseOperations.select(stmt, sql);
    	}
    	catch (IOException ex){
    		ex.printStackTrace();
    	}
    	return result;
    }
}
