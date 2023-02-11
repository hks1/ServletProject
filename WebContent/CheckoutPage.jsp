<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Success Page</title>
</head>
<body>
<%
// allow access only if session exists
if(session.getAttribute("user") == null){
	response.sendRedirect("login.html");
}
String userName = null;
String sessionID = null;
Cookie[] cookies = request.getCookies();
if(cookies != null){
	for(Cookie cookie : cookies){
		if(cookie.getName().equals("user")) userName = cookie.getValue();
	}
}
%>
<h3>Hi <%=userName %>, do the Checkout.</h3>
<br>
<form action="LogoutServlet" method="post">
<input type="submit" value="Logout">
</form>
</body>
</html>