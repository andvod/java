package com.chat;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.sql.*;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;


@WebServlet("/ChatServlet")
@ServerEndpoint("/chatservlet")
public class ChatServlet extends HttpServlet{
    //notice:not thread-safe
	static final long serialVersionUID = 42L;
    private static ArrayList<Session> sessionList = 
                    new ArrayList<Session>();

    @OnOpen
    public void open(Session session)
    {
    	try{	
    	session.getBasicRemote().sendText("Hello " + getUserName(session));
    	sessionList.add(session);    
    	}catch(IOException e){}
    }
    
    @OnClose
    public void onClose(Session session){
    	try{
    		sessionList.remove(session);
    		//this.setSessionMinusOne(session);
    	}
    	catch (Exception e){}
    }
    
    @OnMessage
    public void onMessage(String msg, Session sessionUser){
        try{
            for(Session session : sessionList){
                session.getBasicRemote().sendText(getUserName(sessionUser) + " : " + msg);
            }
        }catch(IOException e){}
    }
    
    private void setSessionMinusOne(Session session)
    {
    	SignIn.updateSession(getUserName(session), -1);
    }
    
    private String getUserName(Session session)
    {
    	String result = "NoName";
    	try
    	{
	    	Statement stmt = DatabaseAccess.connect();
	    	String sql = "select first from Employees where id =" + session.getId();
	    	ResultSet rs = DatabaseOperations.select(stmt, sql);
	    	if (rs.next())	result = rs.getString("first");
    	}
    	catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}
    	catch (IOException ex){
    		ex.printStackTrace();
    	}
    	return result;
    }
    
}
