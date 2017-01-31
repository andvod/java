package com.chat;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;


@WebServlet(name="GetIp",urlPatterns={"/GetIp"})
public class GetIp extends HttpServlet {
	static final long serialVersionUID = 1L;
	private InetAddress ip;
 
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		      throws ServletException, IOException {
		
        String hostname;
        try {
            ip = InetAddress.getLocalHost();
            hostname = ip.getHostName();
            PrintWriter out = response.getWriter();
            
            out.println(ip);
            out.println(hostname);
 
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
	}
	

}