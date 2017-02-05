package com.chat;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.sql.*;

@WebServlet("/DatabaseAccess")
public class DatabaseAccess extends HttpServlet {
	private static final long serialVersionUID = 106L;
	private static Statement stmt = null;
	private static Connection conn = null;
	
	public static Statement connect() throws IOException
	{
	try{
		  // Register JDBC driver
		  String JDBC_DRIVER="com.mysql.jdbc.Driver"; 
		  Class.forName(JDBC_DRIVER);
		
		  // Open a connection
		  String dbName = "test";
		  String dbUserName = "root";
		  String dbPassword = "vodvud10";
		  String connectionString = "jdbc:mysql://127.0.0.1/" + 
				  	dbName + "?user=" + 
				  	dbUserName + "&password=" + 
				  	dbPassword + "&useUnicode=true&characterEncoding=UTF-8";
		  conn = DriverManager.getConnection(connectionString);
		  
		  // Execute SQL query
		  stmt = conn.createStatement();
		}
		catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}
		catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}
		return stmt;
	}

		
	public static void disconnect()
	{
		try
		{
			stmt.close();
			conn.close();
		}
		catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}
		catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}
	}

} 