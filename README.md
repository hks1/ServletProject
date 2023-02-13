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

// http://localhost:8080/FirstServletProject/login.html

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


Important methods of HttpSession:
- __String getId()__
- __Object getAttribute(String name)__ - Some other methods are `getAttributeNames()`, `removeAttribute(String name)` and `setAttribute(String name, Object value)`.
- __long getCreationTime()__ - in ms since midnight January 1, 1970 GMT. Other method `getLastAccessedTime()`.
- __setMaxInactiveInterval(int interval)__ - Specifies the time, in seconds, between client requests before the servlet container will invalidate this session. We can get session timeout value from `getMaxInactiveInterval()` method.
- __ServletContext getServletContext()__ 
- __boolean isNew()__ - Returns true if the client does not yet know about the session or if the client chooses not to join the session.
- __void invalidate()__ 

### JSESSIONID Cookie
When we use HttpServletRequest getSession() method and it creates a new request, it creates the new HttpSession object and also add a Cookie to the response object with name JSESSIONID and value as session id. This cookie is used to identify the HttpSession object in further requests from client. If the cookies are disabled at client side and we are using URL rewriting then this method uses the jsessionid value from the request URL to find the corresponding session. JSESSIONID cookie is used for session tracking, so we should not use it for our application purposes to avoid any session related issues. 

## Session Management in Java Servlet - URL Rewriting

Servlet API provides support for URL rewriting that we can use to manage session if cookies are disable din the browser.

- encoding the URL
- Servlet URL encoding is a fallback approach and it kicks in only if browser cookies are disabled.
- method to encode URL HTTPServletResponse `encodeURL()`
- to redirect the request to another resource and provide session information, use `encodeRedirectURL()` method.

<!-- https://www.digitalocean.com/community/tutorials/java-servlet-filter-example-tutorial -->

# Java Servlet Filter

- used to intercept the client request and do some pre-processing.
- can also intercept the response and do post-processing before sending to the client in web application.


- Servlet Filters are  __pluggable__  java components that can be used to intercept and process 
    - requests  _before_  they are sent to servlets and  
    - response  _after_ servlet code is finished and before container sends the response back to the client.
- Servet Filters can be used for:
    - Logging request parameters to log files
    - Authentication and authorization of request for resources
    - Formatting of request body or header before sending it to servlet
    - Compressing the response data sent to the client
    - Alter response by adding some cookies, header information, etc.

- __Servlet filters are pluggable__  and configured in deployment descriptor (web.xml) file.
- We can have multiple filters for a single resource and we can create a chain of filters for a single resource in web.xml.
- Servlet Filter can be created by implementing `javax.servlet.Filter` interface.

## Servlet Filter Interface

- contains lifecycle methods of a Filter
    - __void init(FilterConfig filterConfig)__ - invoked when container initializes the Filter.  __FilterConfig__  is used by container to provide init parameters and servlet context object to the Filter. can throw ServletException in this method.
    - __doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)__  - invoked everytime by container when it has to apply filter to a resource. Container provides request and response object references to filter as arguments. This is an example of  _Chain of Responsibility_  pattern.
    - __void destroy()__ - invoked when container offloads the filter instance.
- managed by servlet container

## Servlet WebFilter annotation

- `javax.servlet.annotation.WebFilter`, introduced in servlet 3.0. used to declare a servlet filter.
- define init parameters, filter name and description, servlets, url patterns and dispatcher types to apply the filter.
- in case of frequent changes to the filter configurations, using web.xml is better option because that'll not require recompilation of filter class.

## Servlet Filter configuration in web.xml



<!--
References:
https://www.digitalocean.com/community/tutorials/chain-of-responsibility-design-pattern-in-java
-->
