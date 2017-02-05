package com.chat;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import javax.servlet.http.Cookie;
import java.net.InetAddress;

/*String ip = request.getRemoteAddr();
if (ip.equalsIgnoreCase("0:0:0:0:0:0:0:1")) {
    InetAddress inetAddress = InetAddress.getLocalHost();
    String ipAddress = inetAddress.getHostAddress();
    ip = ipAddress;
}*/


@WebServlet("/SetCookie")
public class SetCookie extends HttpServlet {
	private static final long serialVersionUID = 104L;
	private static int count = 0;
	

     
	public void doGet(HttpServletRequest request,
            HttpServletResponse response)
    throws ServletException, IOException
{
// Create cookies for first and last names.      
Cookie firstName = new Cookie("login",
              request.getParameter("login"));
Cookie lastName = new Cookie("passwd",
              request.getParameter("passwd"));

// Set expiry date after 24 Hrs for both the cookies.
firstName.setMaxAge(60); 
firstName.setComment("Ok");
lastName.setMaxAge(60*60*24); 
lastName.setComment("Oki");

// Add both the cookies in the response header.
response.addCookie( firstName );
response.addCookie( lastName );

// Set response content type
response.setContentType("text/html");

PrintWriter out = response.getWriter();
String title = "Setting Cookies Example";
String docType =
"<!doctype html public \"-//w3c//dtd html 4.0 " +
"transitional//en\">\n";
out.println(docType +
        "<html>\n" +
        "<head><title>" + title + "</title></head>\n" +
        "<body bgcolor=\"#f0f0f0\">\n" +
        "<h1 align=\"center\">" + title + "</h1>\n" +
        "<ul>\n" +
        "  <li><b>First Name</b>: "
        + request.getParameter("login") + "\n" +
        "  <li><b>Last Name</b>: "
        + request.getParameter("passwd") + "\n" +
        "</ul>\n" +
        "</body></html>");
}
    
}
