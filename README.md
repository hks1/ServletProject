<!-- https://www.digitalocean.com/community/tutorials/java-web-application-tutorial-for-beginners -->

# Web Container
Tomcat is a web container, when a request is made from Client to web server, it passes the request to web container and it’s web container job to find the correct resource to handle the request (servlet or JSP) and then use the response from the resource to generate the response and provide it to web server. Then the webserver sends the response back to the client. When web container gets the request and if it’s for servlet then container creates two Objects HTTPServletRequest and HTTPServletResponse. Then it finds the correct servlet based on the URL and creates a thread for the request. Then it invokes the servlet service() method and based on the HTTP method service() method invokes doGet() or doPost() methods. Servlet methods generate the dynamic page and write it to the response. Once servlet thread is complete, the container converts the response to HTTP response and send it back to the client. Some of the important work done by web container are:

- __Communication Support__ - Container provides easy way of communication between web server and the servlets and JSPs. Because of the container, we don’t need to build a server socket to listen for any request from the webserver, parse the request and generate a response. All these important and complex tasks are done by container and all we need to focus is on our business logic for our applications.
- __Lifecycle and Resource Management__ - Container takes care of managing the life cycle of servlet. The container takes care of loading the servlets into memory, initializing servlets, invoking servlet methods and destroying them. The container also provides utility like JNDI for resource pooling and management.
- __Multithreading Support__ - Container creates a new thread for every request to the servlet and when it’s processed the thread dies. So servlets are not initialized for each request and save time and memory.
- __JSP Support__ - JSPs doesn’t look like normal java classes and web container provides support for JSP. Every JSP in the application is compiled by container and converted to Servlet and then container manages them like other servlets.
- __Miscellaneous Task__ - Web container manages the resource pool, does memory optimizations, run garbage collector, provides security configurations, support for multiple applications, hot deployment and several other tasks behind the scene that makes our life easier.

# Web Application Directory Structure
Java Web Applications are packaged as Web Archive (WAR) and it has a defined structure.

#  Deployment Descriptor
__web.xml__ file is the deployment descriptor of the web application and contains a mapping for servlets (prior to 3.0), welcome pages, security configurations, session timeout settings, etc.


<!-- https://www.digitalocean.com/community/tutorials/servlet-jsp-tutorial -->
<!-- https://www.digitalocean.com/community/tutorials/java-session-management-servlet-httpsession-url-rewriting -->

# Session Management
## Session
HTTP protocol and Web Servers are stateless, what it means is that for web server every request is a new request to process and they can’t identify if it’s coming from client that has been sending request previously. But sometimes in web applications, we should know who the client is and process the request accordingly. </br>
__Session__ is a conversional state between client and server and it can consists of multiple request and response between client and server. </br>
Since HTTP and Web Server both are stateless, the only way to maintain a session is when some unique information about the session (session id) is passed between server and client in every request and response. There are several ways through which we can provide unique identifier in request and response.

1. __User Authentication__ - 
2. __HTML Hidden Field__ -
3. __URL Rewriting__ -
4. __Cookies__ - Cookies are small piece of information that is sent by web server in response header and gets stored in the browser cookies. When client make further request, it adds the cookie to the request header and we can utilize it to keep track of the session.
5. __Session Management API__ -

## Session management in Java - Cookies
Deployment descriptor web.xml of the web application

```xml
<?xml version="1.0" encoding="UTF-8"?> -->
<!--  xsi:schemaLocation="{namespace} {location}" -->
<web-app xmlns:xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="https://java.sun.com/xml/ns/javaee" 
xsi:schemaLocation="https://java.sun.com/xml/ns/javaee https://java.sun.com/xml/ns/javaee/web-app_3_0.xsd" id="WebApp_ID" version="3.0">
<display-name>ServletCookieExample</display-name>
<Welcome-file-list>
<Welcom-filel>login.html</Welcom-filel>
</Welcome-file-list>
</web-app>
```

login.html

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
</head>
<body>

<form action="LoginServlet" method="post">
Username: <input type="text" name="user">
<br>
Password: <input type="password" name="pwd">
<br>
<input type="submit" value="Login">
</form>
</body>
</html>
```

## Session in Java Servlet - HttpSession

- Servlet API provides Session management through `HttpSession` interface.
- We can get session from HttpServletRequest object using following methods.
    1. HttpSession getSession() - 
    2. HttpSession getSession(boolean flag) - 
