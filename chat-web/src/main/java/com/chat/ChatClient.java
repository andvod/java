package com.chat;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.websocket.OnOpen;
import javax.websocket.OnMessage;
import javax.websocket.ClientEndpoint;
import javax.websocket.Session;
import java.net.URI;
import javax.websocket.DeploymentException;
import javax.websocket.WebSocketContainer;
import javax.websocket.ContainerProvider;


@ClientEndpoint
@WebServlet("/ChatClient")
public class ChatClient extends HttpServlet {
	private static Object waitLock = new Object();
	@OnMessage
	    public void onMessage(String message) {
	//the new USD rate arrives from the websocket server side.
	       System.out.println("Received msg: "+message);        
	    }
	 private static void  wait4TerminateSignal()
	 {
	  synchronized(waitLock)
	  {try {
	    waitLock.wait();
	   } catch (InterruptedException e) {    
	   }}}
	public static void main(String[] args) {
	WebSocketContainer container=null;//
	     Session session=null;
	  try{
	   //Tyrus is plugged via ServiceLoader API. See notes above
	   container = ContainerProvider.getWebSocketContainer(); 
	//WS1 is the context-root of my web.app 
	//ratesrv is the  path given in the ServerEndPoint annotation on server implementation
	session=container.connectToServer(ChatClient.class, URI.create("ws://localhost:8080/chat-web/chatservlet")); 
	   wait4TerminateSignal();
	  } catch (Exception e) {
	   e.printStackTrace();
	  }
	  finally{
	   if(session!=null){
	    try {
	 session.close();
	    } catch (Exception e) {     
	     e.printStackTrace();
	    }
	   }         
	  } 
	 }
	}