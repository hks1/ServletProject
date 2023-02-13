<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Success Page</title>
</head>
<body>
<a href="LoginSuccess.jsp">Home</a>
<a href="profile.jsp">Profile</a>
<%
String userName = null;
// allow access only if session exists
if(session.getAttribute("user") == null){
	response.sendRedirect("login.html");
} else userName = (String) session.getAttribute("user");

String sessionID = null;
Cookie[] cookies = request.getCookies();
if(cookies != null){
	for(Cookie cookie : cookies){
		if(cookie.getName().equals("user")) userName = cookie.getValue();
		if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
	}
}
%>
<h3>Hi <%=userName %>, do the Checkout.</h3>
session id=<%=sessionID %>
<br>
<form action="<%=response.encodeURL("LogoutServlet") %>" method="post">
<input type="submit" value="Logout">
</form>
</body>
</html>