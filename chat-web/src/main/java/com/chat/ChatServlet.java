package com.chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;

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
    public void onOpen(Session session)
    {
    	try{
    	session.getBasicRemote().sendText("Hello");
        sessionList.add(session);
    	}catch(IOException e){}
    	
    }
    
    @OnClose
    public void onClose(Session session){
        sessionList.remove(session);
    }
    
    @OnMessage
    public void onMessage(String msg){
        try{
            for(Session session : sessionList){
                session.getBasicRemote().sendText(msg);
            }
        }catch(IOException e){}
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      // Set response content type       

        String user = request.getParameter("login");
        String passwd = request.getParameter("password");
        
        Cookie[] cookie = request.getCookies();
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("Welcome user");
        //new GetCookie().doGet(request, response);

        //response.sendRedirect("//localhost:8080/wschat.htm");
   }
    
    public void addUser(Session session)
    {
    	sessionList.add(session);
    }
}
