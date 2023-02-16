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
String user = null;
/* 
if(session.getAttribute("user") == null){
	response.sendRedirect("login.html");
} else user = (String) session.getAttribute("user");
 */
String userName = null;
String sessionID = null;

Cookie[] cookies = request.getCookies();
if(cookies != null){
	for(Cookie cookie : cookies){
		if(cookie.getName().equals("user")) userName = cookie.getValue();
		if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
	}
}
//if(userName == null) response.sendRedirect("login.html");
%>
<h3>Hi <%=userName %>, Login successful. Your session ID=<%=sessionID %>></h3>
<br>
User=<%=user %>
<br>
<!-- need to encode all the URLs where we want session information to be passed -->
<a href="<%=response.encodeUrl("CheckoutPage.jsp") %>">Checkout Page</a>
<a href="<%=response.encodeUrl("index.html") %>">File Upload</a>
<a href="<%=response.encodeUrl("fileupload.html") %>">Servlet 3 File Upload</a>
<br>
<a href="<%=response.encodeUrl("/JDBCDataSourceExampleServlet") %>">DataSource</a>
<form action="<%=response.encodeUrl("JDBCDataSourceExampleServlet") %>" method="get">
	<input type="submit" value="Show DataSource">
</form>
<form action="<%=response.encodeUrl("LogoutServlet") %>" method="post">
<input type="submit" value="logout">
</form>

</body>
</html>