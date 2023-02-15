<!-- https://www.digitalocean.com/community/tutorials/java-web-application-tutorial-for-beginners -->

# Web Container
Tomcat is a web container, when a request is made from Client to web server, it passes the request to web container and itâ€™s web container job to find the correct resource to handle the request (servlet or JSP) and then use the response from the resource to generate the response and provide it to web server. Then the webserver sends the response back to the client. When web container gets the request and if itâ€™s for servlet then container creates two Objects HTTPServletRequest and HTTPServletResponse. Then it finds the correct servlet based on the URL and creates a thread for the request. Then it invokes the servlet service() method and based on the HTTP method service() method invokes doGet() or doPost() methods. Servlet methods generate the dynamic page and write it to the response. Once servlet thread is complete, the container converts the response to HTTP response and send it back to the client. Some of the important work done by web container are:

- __Communication Support__ - Container provides easy way of communication between web server and the servlets and JSPs. Because of the container, we donâ€™t need to build a server socket to listen for any request from the webserver, parse the request and generate a response. All these important and complex tasks are done by container and all we need to focus is on our business logic for our applications.
- __Lifecycle and Resource Management__ - Container takes care of managing the life cycle of servlet. The container takes care of loading the servlets into memory, initializing servlets, invoking servlet methods and destroying them. The container also provides utility like JNDI for resource pooling and management.
- __Multithreading Support__ - Container creates a new thread for every request to the servlet and when itâ€™s processed the thread dies. So servlets are not initialized for each request and save time and memory.
- __JSP Support__ - JSPs doesnâ€™t look like normal java classes and web container provides support for JSP. Every JSP in the application is compiled by container and converted to Servlet and then container manages them like other servlets.
- __Miscellaneous Task__ - Web container manages the resource pool, does memory optimizations, run garbage collector, provides security configurations, support for multiple applications, hot deployment and several other tasks behind the scene that makes our life easier.

# Web Application Directory Structure
Java Web Applications are packaged as Web Archive (WAR) and it has a defined structure.

#  Deployment Descriptor
__web.xml__ file is the deployment descriptor of the web application and contains a mapping for servlets (prior to 3.0), welcome pages, security configurations, session timeout settings, etc.


<!-- https://www.digitalocean.com/community/tutorials/servlet-jsp-tutorial -->
<!-- https://www.digitalocean.com/community/tutorials/java-session-management-servlet-httpsession-url-rewriting -->

# Session Management
## Session
HTTP protocol and Web Servers are stateless, what it means is that for web server every request is a new request to process and they canâ€™t identify if itâ€™s coming from client that has been sending request previously. But sometimes in web applications, we should know who the client is and process the request accordingly. </br>
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

declare servlet filter 
- in web.xml, or 
- by using `@WebFilter` annotation


```xml
<filter>
  <filter-name>RequestLoggingFilter</filter-name> <!-- mandatory -->
  <filter-class>com.hks.first.filters.RequestLoggingFilter</filter-class> <!-- mandatory -->
  <init-param> <!-- optional -->
  <param-name>test</param-name>
  <param-value>testValue</param-value>
  </init-param>
</filter>
```

map a filter to servlet classes or URL patterns

```xml
<filter-mapping>
  <filter-name>RequestLoggingFilter</filter-name> <!-- mandatory -->
  <url-pattern>/*</url-pattern> <!-- either url-pattern or servlet-name is mandatory -->
  <servlet-name>LoginServlet</servlet-name>
  <dispatcher>REQUEST</dispatcher>
</filter-mapping>
```

- While creating the filter chain for a servlet, container first processes the url-patterns and then servlet-names
- Servlet Filters are generally used for client requests but sometimes we want to apply filters with RequestDispatcher also, we can use dispatcher element in this case, the possible values are REQUEST, FORWARD, INCLUDE, ERROR and ASYNC. If no dispatcher is defined then it’s applied only to client requests.

## Servlet Filter Example for Logging and Session Validation

```java
package com.hks.first.filters;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class RequestLoggingFilter implements Filter{
	
	private ServletContext context;
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub
		this.context = config.getServletContext();
		this.context.log("RequestLogginFilter initialized");
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) request;
		Enumeration<String> params = req.getAttributeNames();
		
		while(params.hasMoreElements()) {
			String param = params.nextElement();
			String value = req.getParameter(param);
			this.context.log(req.getRemoteAddr() + "::Request params::{"+param+"="+value+"}");
			System.out.println(req.getRemoteAddr() + "::Request params::{"+param+"="+value+"}");
		}
		
		Cookie[] cookies = req.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				this.context.log(req.getRemoteAddr() + "::Cookie::{"+cookie.getName()+","+cookie.getValue()+"}");
			}
		}
		
		// pass the request along the filter chain
		chain.doFilter(req, response);
		
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
```

```java
package com.hks.first.filters;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter
public class AuthenticationFilter implements Filter{
	private ServletContext context;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		this.context = filterConfig.getServletContext();
		this.context.log("Authentication Filter initialized");
		
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		String uri = req.getRequestURI();
		this.context.log("Requested resource: "+uri);
		
		HttpSession session = req.getSession(false);
		if(null != session) {
			Enumeration<String> attrs = session.getAttributeNames();
			while(attrs.hasMoreElements()) {
				this.context.log(attrs.nextElement());
			}
		}
		this.context.log(String.valueOf(session == null && !(uri.endsWith(".html") || uri.endsWith("LoginServlet"))));
		
		if(session == null && !(uri.endsWith(".html") || uri.endsWith("LoginServlet"))) {
			this.context.log("Unauthorized access request");
			res.sendRedirect("login.html");
		}else {
			// pass the request along the filter chain
			chain.doFilter(request, response);
		}
		
	}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="https://www.w3.org/2001/XMLSchema-instance" xmlns="https://java.sun.com/xml/ns/javaee" xsi:schemaLocation="https://java.sun.com/xml/ns/javaee https://java.sun.com/xml/ns/javaee/web-app_3_0.xsd" id="WebApp_ID" version="3.0">
  <display-name>ServletProject</display-name>
  <welcome-file-list>
    <welcome-file>login.html</welcome-file>
  </welcome-file-list>
  
  
  <filter>
  <filter-name>RequestLoggingFilter</filter-name> <!-- mandatory -->
  <filter-class>com.hks.first.filters.RequestLoggingFilter</filter-class> <!-- mandatory -->
  <init-param> <!-- optional -->
  <param-name>test</param-name>
  <param-value>testValue</param-value>
  </init-param>
</filter>

<filter>
    <filter-name>AuthenticationFilter</filter-name>
    <filter-class>com.hks.first.filters.AuthenticationFilter</filter-class>
  </filter>
  
  <filter-mapping>
    <filter-name>RequestLoggingFilter</filter-name>
    <url-pattern>/*</url-pattern>
    <servlet-name>LoginServlet</servlet-name>
    <dispatcher>REQUEST</dispatcher>
  </filter-mapping>
  <filter-mapping>
    <filter-name>AuthenticationFilter</filter-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping>
  
</web-app>

```

__Note:__ Struts 2 uses Servlet Filter to intercept the client requests and forward them to apropriate action classes, these are called __Struts 2 Interceptors__.
<!-- https://www.digitalocean.com/community/tutorials/struts-tutorial-for-beginners -->

# Servlet Listener



- A Servlet listner is a servlet method that awaits the occurence of an event.
- Servlet Listeners are classes that monitors a specific type of event and activate functionality when that event happens.
- Servlet Listener works for listening to events in web containers like a session is created, an attribute is inserted into the session, or a container is activated.

- used for listening to events in web containers, such as
    - when you create a session,
    - insert an attribute,
    - passivate and activate in another container

- The servlet container generates events that trigger the action of event listener classes. To subscribe to these events, you configure listeners in web.xml, such as HttpSessionListener.
- In other words, the servlet container invokes the event listener class's functions.


- __User creates an event listener class that implements one of the listener interfaces.__


steps for using servlet listener
1. Prepare the Listener class
2. Use the Listener tag and classes in web.xml file

--

<!-- https://www.digitalocean.com/community/tutorials/servletcontextlistener-servlet-listener-example -->


- listeners
- servlet API listener interfaces
- Event objects


- ServletContext
- Session
- ServletRequest

Using `ServletContext`, we can create an attribute with application scope that all other servlet can access but we can initialize  __ServletContext init parameters as String only__  in deployment descriptor (web.xml).


servlet API provides Listener interfaces that we can implement and configure to listen to an event to do certain operations.jj

__Event__ examples - initialization of application, destroying an application, request from client, creating/destroying a session, attribute modification in session etc.

__Servlet API__ provides different types of Listener interfaces that we can implement and configure in web.xml to process something when a particular event occurs.

## Servlet Listener Interfaces and Event Objects

- Servlet API provides different kind of listeners for different types of Events.
- Listener interfaces declare methods to work with a group of similar events, e.g, ServletContextListener listens to startup and shutdown event of the context.
- Every method in listener interface takes Event object as input. Event object works as a wrapper to provide specific objects to the listener.
- Servlet API provides following event objects:
    - __javax.servlet.AsyncEvent__ - 
    - __javax.servlet.http.HttpSessionBindingEvent__ - 
    - __javax.servlet.http.HttpSessionEvent__ - 
    - __javax.servlet.ServletContextAttributeEvent__ - 
    - __javax.servlet.ServletContextEvent__ - 
    - __javax.servlet.ServletRequestEvent__ - 
    - __javax.servlet.ServletRequestAttributeEvent__ - 


- Servlet API provides following Listener interfaces:
    - __javax.servlet.AsyncListener__ - 
    - __javax.servlet.ServletContextListener__ - 
    - __javax.servlet.ServletContextAttributeListener__ - 
    - __javax.servlet.ServletRequestListener__ - 
    - __javax.servlet.ServletRequestAttributeListener__ -
    - __javax.servlet.http.HttpSessionLostener__ -
    - __javax.servlet.http.HttpSessionBindingListener__ -
    - __javax.servlet.http.HttpSessionAttributeListener__ - 
    - __javax.servlet.http.HttpSessionActivationListener__ -


## Servlet Listener Configuration

- @WebListener annotation - to declare class as Listener, however the class should implement one or more of the listener interfaces.

- define listener in web.xml as

```
<listener>
    <listener-class>
    com.journaldev.listener.AppContextListener
    </listener-class>
</listener>
```

## Servlet Listener Example

__web.xml__ - context init parameters and listener cofiguration

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="https://www.w3.org/2001/XMLSchema-instance" xmlns="https://java.sun.com/xml/ns/javaee" xsi:schemaLocation="https://java.sun.com/xml/ns/javaee https://java.sun.com/xml/ns/javaee/web-app_3_0.xsd" id="WebApp_ID" version="3.0">
  <display-name>ServletListenerExample</display-name>
  
  <context-param>
    <param-name>DBUSER</param-name>
    <param-value>hks</param-value>
  </context-param>
  <context-param>
    <param-name>DBPWD</param-name>
    <param-value>password</param-value>
  </context-param>
  <context-param>
    <param-name>DBURL</param-name>
    <param-value>jdbc:mysql://localhost/mysql_db</param-value>
  </context-param>
  
  <listener>
    <listener-class>com.hks.first.listener.AppContextListener</listener-class>
  </listener>
  <listener>
    <listener-class>com.hks.first.listener.AppContextAttributeListener</listener-class>
  </listener>
  <listener>
    <listener-class>com.hks.first.listener.MySessionListener</listener-class>
  </listener>
  <listener>
    <listener-class>com.hks.first.listener.MyServletRequestListener</listener-class>
  </listener>
</web-app>
```

DBConnectionManager

```java
package com.hks.first.db;

import java.sql.Connection;

public class DBConnectionManager {
	private String dbURL;
	private String user;
	private String password;
	private Connection con;
	
	public DBConnectionManager(String url, String user, String password) {
		// TODO Auto-generated constructor stub
		this.dbURL = url;
		this.user = user;
		this.password = password;
		System.out.println("DBURL:"+dbURL+", user:"+user);
		//create DB connection now
	}
	
	public Connection getConnection() {
		return this.con;
	}
	public void closeConnection() {
		// close connection
	}

}
```

```java
package com.hks.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		ServletContext ctx = request.getServletContext();
		ctx.setAttribute("user", "hks");
		
		String user = (String) ctx.getAttribute("user");
		ctx.removeAttribute("user");
		
		HttpSession session = request.getSession();
		session.invalidate();
		
		PrintWriter out = response.getWriter();
		out.write("Hi " + user);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

```

### ServletContextListener

Ex. read servlet context init parameters to create the DBConnectionManager object and set it as attribute to the ServletContext object.

```java
package com.hks.first.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.hks.first.db.DBConnectionManager;

@WebListener
public class AppContextListener implements ServletContextListener{
	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		// TODO Auto-generated method stub
		ServletContext ctx = servletContextEvent.getServletContext();
		
		String url = ctx.getInitParameter("DBURL");
		String user = ctx.getInitParameter("DBUSER");
		String password = ctx.getInitParameter("DBPASS");
		
		// create DB connection from init parameters and set it to the context
		DBConnectionManager dbManage = new DBConnectionManager(url, user, password);
		ctx.setAttribute("DBManager", dbManage);
		System.out.println("DB conneciton initialized for Application");
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		// TODO Auto-generated method stub
		ServletContext ctx = servletContextEvent.getServletContext();
		
		DBConnectionManager dbManager = (DBConnectionManager) ctx.getAttribute("DBManager");
		dbManager.closeConnection();
		System.out.println("Database connection closed for Application.");
		
	}

}
```

### ServletContextAttributeListener

Ex. An implementation to log the event when attribute is added, removed or replaced in servlet context.

```java
package com.hks.first.listener;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class AppContextAttributeListener
 *
 */
@WebListener
public class AppContextAttributeListener implements ServletRequestAttributeListener {

    /**
     * Default constructor. 
     */
    public AppContextAttributeListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletRequestAttributeListener#attributeRemoved(ServletRequestAttributeEvent)
     */
    public void attributeRemoved(ServletRequestAttributeEvent servletRequestAttributeEvent)  { 
         // TODO Auto-generated method stub
    	System.out.println("ServletContext attribute removed::{"+servletRequestAttributeEvent.getName()+","+servletRequestAttributeEvent.getValue()+"}");
    }

	/**
     * @see ServletRequestAttributeListener#attributeAdded(ServletRequestAttributeEvent)
     */
    public void attributeAdded(ServletRequestAttributeEvent servletRequestAttributeEvent)  { 
         // TODO Auto-generated method stub
    	System.out.println("ServletContext attribute added::{"+servletRequestAttributeEvent.getName()+","+servletRequestAttributeEvent.getValue()+"}");
    }

	/**
     * @see ServletRequestAttributeListener#attributeReplaced(ServletRequestAttributeEvent)
     */
    public void attributeReplaced(ServletRequestAttributeEvent servletRequestAttributeEvent)  { 
         // TODO Auto-generated method stub
    	System.out.println("ServletContext attribute replaced::{"+servletRequestAttributeEvent.getName()+","+servletRequestAttributeEvent.getValue()+"}");
    }
	
}

```

### HttpSessionListener

Ex. an implementation to log the event when session is created or destroyed

```java
package com.hks.first.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class MySessionListener
 *
 */
@WebListener
public class MySessionListener implements HttpSessionListener {

    /**
     * Default constructor. 
     */
    public MySessionListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent sessionEvent)  { 
         // TODO Auto-generated method stub
    	System.out.println("Session created:: ID="+sessionEvent.getSession().getId());
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent sessionEvent)  { 
         // TODO Auto-generated method stub
    	System.out.println("Session destroyed:: ID+"+sessionEvent.getSession().getId());
    }
	
}

```

### ServletRequestListener

Ex. an implementation of ServletRequestListener interface to log the ServletRequest IP address when request is initialized and destroyed.

```java
package com.hks.first.listener;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class MyServletRequestListener
 *
 */
@WebListener
public class MyServletRequestListener implements ServletRequestListener {

    /**
     * Default constructor. 
     */
    public MyServletRequestListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletRequestListener#requestDestroyed(ServletRequestEvent)
     */
    public void requestDestroyed(ServletRequestEvent servletRequestEvent)  { 
         // TODO Auto-generated method stub
    	ServletRequest servletRequest = servletRequestEvent.getServletRequest();
    	System.out.println("Servlet request destroyed. Remote IP="+servletRequest.getRemoteAddr());
    }

	/**
     * @see ServletRequestListener#requestInitialized(ServletRequestEvent)
     */
    public void requestInitialized(ServletRequestEvent servletRequestEvent)  { 
         // TODO Auto-generated method stub
    	ServletRequest servletRequest = servletRequestEvent.getServletRequest();
    	System.out.println("Servlet request created. Remote IP="+servletRequest.getRemoteAddr());
    }
	
}

```

# Servlet Cookies

# Servlet Exception and Error Handling

<!-- https://www.digitalocean.com/community/tutorials/servlet-exception-and-error-handling-example-tutorial -->


## Servlet Exception

MyExceptionServlet - throws `javax.servlet.ServletException`

```java
package com.hks.first.exception;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyExceptionServlet
 */
@WebServlet("/MyExceptionServlet")
public class MyExceptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyExceptionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		throw new ServletException("GET method is not supported");
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

```

http://localhost:8080/ServletProject/MyExceptionServlet

```
HTTP Status 500 – Internal Server Error
Type Exception Report

Message GET method is not supported

Description The server encountered an unexpected condition that prevented it from fulfilling the request.

Exception

javax.servlet.ServletException: GET method is not supported
	com.hks.first.exception.MyExceptionServlet.doGet(MyExceptionServlet.java:30)
	javax.servlet.http.HttpServlet.service(HttpServlet.java:656)
	javax.servlet.http.HttpServlet.service(HttpServlet.java:765)
	org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52)
	com.hks.first.filters.AuthenticationFilter.doFilter(AuthenticationFilter.java:52)
	com.hks.first.filters.RequestLoggingFilter.doFilter(RequestLoggingFilter.java:50)
Note The full stack trace of the root cause is available in the server logs.

Apache Tomcat/8.5.85
```

Since browser understand only HTML, when our application throw exception, servlet container processes the exception and generate a HTML response.

## Servlet Error

Ex. 404 for invalid URLs

http://localhost:8080/ServletProject/invalidurl

```
HTTP Status 404 – Not Found
Type Status Report

Message The requested resource [/ServletProject/invalidurl] is not available

Description The origin server did not find a current representation for the target resource or is not willing to disclose that one exists.

Apache Tomcat/8.5.85
```

## Servlet Exception and Error Handling

- Servlet API provides support for custom Exception and Error Handler servlets that we can configure in deployment descriptor.


Ex. Create a custom Exception and Error handler servlet

```java
package com.hks.first.exception;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AppExceptionHandler
 */
@WebServlet("/AppExceptionHandler")
public class AppExceptionHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AppExceptionHandler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		processError(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response); // this is also eventually calling processError() method
	}
	
	private void processError(HttpServletRequest request, HttpServletResponse response) throws IOException{
		// Analyze the servlet exception
		Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");
		if(servletName == null) {
			servletName = "Unknown";
		}
		String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
		if(requestUri == null) {
			requestUri = "Unknown";
		}
		
		// set response content type
		PrintWriter out = response.getWriter();
		out.write("<html><head><title>Exception/Error Details<title></head><body>");
		if(statusCode != 500) {
			out.write("<h3>Error Details</h3>");
			out.write("<strong>Status Code</strong>:"+statusCode+"<br>");
			out.write("<strong>Requested URI</strong>:"+requestUri);
		}else {
			out.write("<h3>Exception Details</h3>");
			out.write("<ul><li>Servlet Name:"+servletName+"</li>");
			out.write("<li>Exception Name:"+throwable.getClass().getName()+"</li>");
			out.write("<li>Exception Message:"+throwable.getMessage()+"</li>");
			out.write("</ul>");
		}
		out.write("<br><br>");
		out.write("<a href=\"login.html\">Home Page</a>");
		out.write("</body></html>");
	}

}
```

- configure it in Deplyment Descriptor

```xml
  <error-page>
	  <error-code>404</error-code>
	  <location>/AppExceptionHandler</location>
  </error-page>
  
  <error-page>
  	<exception-type>javax.servlet.ServletException</exception-type>
  	<location>/AppExceptionHandler</location>
  </error-page>
```

- Each error-page element should have either error-code or exception-type element.
- define the exception handler servlet in location element.
- in above example, if the application throw 404 error or ServletException, it will be handled by AppExceptionHandler servlet.
- When such exception and error scenario appears, servlet container will invoke the corresponding HTTP method of the Exception Handler servlet and pass the request and response object. 
- Notice the implementation of both doGet() and doPost() methods so that it can handle GET and POST requests and using a common method to process them.
- Before servlet container invokes the servlet to handle the exception, it sets some attributes in the request to get useful information about the exception, some of them are 
    - javax.servlet.error.exception, 
    - javax.servlet.error.status_code, 
    - javax.servlet.error.servlet_name and 
    - javax.servlet.error.request_uri.

Note: For exception, status code is always 500 that corresponds to the “Internal Server Error”, for other types of error we get different error codes such as 404, 403 etc.


- to handle runtime exceptions and all other exceptions in a single exception handler, provide exception-type as `Throwable`

```xml
<error-page>
  <exception-type>java.lang.Throwable</exception-type>
  <location>/AppExceptionHandler</location>
</error-page>
```

Note: If there are multiple error-page entries, let’s say one for Throwable and one for IOException and application throws FileNotFoundException then it will be handled by error handler of IOException.

- You can also use  __JSP page as exception handler__ , just provide the location of jsp file rather than servlet mapping.

 
<!--
References:
chain of responsibility -
https://www.digitalocean.com/community/tutorials/chain-of-responsibility-design-pattern-in-java
servlet listener -
https://www.educba.com/servlet-listener/
-->

<!--
TBD
https://www.digitalocean.com/community/tutorials/struts-tutorial-for-beginners
-->
