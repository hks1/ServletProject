<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profile Page</title>
</head>
<body>
<a href="LoginSuccess.jsp">Home</a>
<h3>on profile</h3>
<%
String userName = null;
if(session.getAttribute("user") == null){
	response.sendRedirect("login.html");
}else userName = (String) session.getAttribute("user");

String sessionID = null;
String userID = null;
Cookie[] cookies = request.getCookies();
System.out.println("Cookies::");
if(cookies != null){
	for(Cookie cookie: cookies){
		System.out.println(cookie.getName() + ":" + cookie.getValue());
		if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
		if(cookie.getName().equals("user")) userID = cookie.getValue();
	}
}
System.out.println("Session::");
Enumeration<String> sessionAttributes = session.getAttributeNames();
String user = null;
while(sessionAttributes.hasMoreElements()){
	String attr = sessionAttributes.nextElement();
	System.out.println(attr + ": " + session.getAttribute(attr));
	if(attr.equals("user")) user = (String) session.getAttribute(attr);
	
}
%>
<table>
<thead>
<tr>
<td><h3>Cookies</h3></td>
<td><h3>Session</h3></td>
</tr>
</thead>
<tbody>
<tr>
<td>user=<%=userID %></td>
<td>user=<%=user %></td>
</tr>
<tr>
<td>JSESSIONID=<%=sessionID %></td>
<td></td>
</tr>
</tbody>
</table>
</body>
</html>