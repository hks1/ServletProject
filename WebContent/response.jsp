<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Upload File Response</title>
<a href="LoginSuccess.jsp">Home</a>
</head>
<body>
	<%--Using JSP EL to get message attribute value from the request scope --%>
	<h2>${requestScope.message }</h2>
</body>
</html>