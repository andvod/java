package com.chat;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.sql.*;


@WebServlet("/DatabaseOperations")
public class DatabaseOperations extends HttpServlet {
	private static final long serialVersionUID = 107L;
	
    public static ResultSet select(Statement stmt, String sql)
    {
    	ResultSet rs = null;
    	try{
    		rs = stmt.executeQuery(sql);
    	}
    	catch(SQLException se){
    		//Handle errors for JDBC
    		se.printStackTrace();
    	}
    	return rs;
    }
    
    public static int insert(Statement stmt, String sql)
    {
    	int rs = -1;
    	try{
    		rs = stmt.executeUpdate(sql);
    	}
    	catch(SQLException se){
    		//Handle errors for JDBC
    		se.printStackTrace();
    	}
    	return rs;
    }
    
}
