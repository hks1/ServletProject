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

#

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


#### steps for using servlet listener
1. Prepare the Listener class
2. Use the Listener tag and classes in web.xml file

Note: Except for HttpSessionBindingListener and HttpSessionActivationListener, all Listeners require the setup described above.

#### Event Servlet Listener

- Servlet API offers many types of listeners for various types of Events. 
- Listener interfaces describe methods for interacting with a group of related events; for instance, the ServletContext Listener interface listens for context startup and shutdown events.

- Each method in the listener interface accepts an Event object as an argument. 
- Event object functions as a container to deliver specified objects to listeners.

- The Servlet API provides the subsequent event objects:
    - __servlet.AsynEvent__ - 
    - __servlet.http.HttpSessionBindingEvent__ - 
    - __servlet.http.HttpSessionEvent__ - 
    - __servlet.ServletContextAttributeEvent__ - 
    - __servlet.ServletContextEvent__ - 
    - __servlet.ServletRequestEvent__ - 
    - __servlet.ServletRequestAttributeEvent__ - 

#### Servlet Listener Parameters

Servlet Listener interfaces are offered by the Servlet API.
- __servlet.AsyncListener interface__ -
- __servlet.ServletContextListener interface__ - 
- __servlet.ServletContextAttributeListener interface__ - 
- __servlet.ServletRequestListener interface__ - 
- __servlet.ServletRequestAttributeListener interface__ - 
- __servlet.http.HttpSessionListener interface__ - 
- __servlet.http.HttpSessionAttributeListener interface__ - 
- __servlet.http.HttpSessionActivationListener interface__ - 


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

#

# Servlet Cookies

#

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


#

# Servlet File upload / download

### html page for uploading file to server

- post request to servlet
- __enctype__  of form should be  __multipart/form-data__.
- To select a file from user file system,  __input__  element with  __type__  as  __file__. 

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>File Upload</title>
	</head>
	<body>
		<form action="UploadDownloadFileServlet" method="post" enctype="multipart/form-data">
			Select file to upload: <input type="file" name="fileName">
			<br>
			<input type="submit" value="Upload">
		</form>
	</body>
</html>
```

### Server file location for file upload

- configurable in deployment descriptor context params.

```web.xml
  <context-param>
  	<param-name>tempfile.dir</param-name>
  	<param-value>tmpfiles</param-value>
  </context-param>
```

### ServletContextListener for file upload location

```java
package com.hks.first.listener;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class FileLocationContextListener
 *
 */
@WebListener
public class FileLocationContextListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public FileLocationContextListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent servletContextEvent)  { 
         // TODO Auto-generated method stub
    	String rootPath = System.getProperty("catlina.home");
    	ServletContext ctx = servletContextEvent.getServletContext();
    	String relativePath = ctx.getInitParameter("tempfile.dir");
    	
    	File file = new File(rootPath + File.separator + relativePath);
    	if(file.exists()) file.mkdirs();
    	
    	System.out.println("File Directory created to be used for storing files.");
    	
    	ctx.setAttribute("FILES_DIR_FILE", file);
    	ctx.setAttribute("FILES_DIR", rootPath + File.separator + relativePath);
    }
	
}
```

### File upload download servlet

__Update__: Servlet Specs 3 added support to upload files on server in the API, so we won’t need to use any third party API.


- For file upload, we'll use  __Apache Commons FileUpload__  utility and it's dependency  __Apache Commons IO__  jar.

- __DiskFileItemFactory__  factory that provides a method to parse the HttpServletRequest object and return list of  __FileItem__.
- FileItem provides useful method to get the file name, field name in form, size and content type details of the file that needs to be uploaded.
- To write file to a directory, create a File object and pass it as argument to FileItem `write()` method.
- Since the whole purpose of the servlet is to upload file, we will override init() method to initialise the `DiskFileItemFactory` object instance of the servlet. We will use this object in the doPost() method implementation to upload file to server directory.
- Once the file gets uploaded successfully, we will send response to client with URL to download the file, since HTML links use GET method,we will append the parameter for file name in the URL and we can utilise the same servlet doGet() method to implement file download process.
- For implementing download file servlet, 
    - first we will open the InputStream for the file and use  __ServletContext.getMimeType()__  method to get the MIME type of the file and set it as response content type.
    - We will also need to set the response content length as length of the file.
    - To make sure that client understand that we are sending file in response, we need to set  __“Content-Disposition”__  header with value as  __"attachment; filename=“fileName”__ .
    - Once we are done with setting response configuration, we can read file content from InputStream and write it to ServletOutputStream and the flush the output to client.

<!--
Apache Commons IO jar and Apache Commons FileUpload jar from below URLs. https://commons.apache.org/proper/commons-fileupload/download_fileupload.cgi https://commons.apache.org/proper/commons-io/download_io.cgi
-->


```java
	package com.hks.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.tomcat.util.http.fileupload.FileItem;
//import org.apache.tomcat.util.http.fileupload.FileUploadException;
//import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
//import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class UploadDownloadFileServlet
 */
@WebServlet("/UploadDownloadFileServlet")
public class UploadDownloadFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletFileUpload uploader = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadDownloadFileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init() throws ServletException {
    	// TODO Auto-generated method stub
    	//super.init();
    	DiskFileItemFactory fileFactory = new DiskFileItemFactory();
    	File filesDir = (File) getServletContext().getAttribute("FILES_DIR_FILE");
    	fileFactory.setRepository(filesDir);
    	this.uploader = new ServletFileUpload(fileFactory);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String fileName = request.getParameter("fileName");
		if(fileName == null || fileName.equals("")) {
			throw new ServletException("File name can't be null or empty");
		}
		File file = new File(request.getServletContext().getAttribute("FILES_DIR")+File.separator+fileName);
		if(!file.exists()) {
			throw new ServletException("File doesn't exist on the server");
		}
		System.out.println("File location on the server:"+file.getAbsolutePath());
		ServletContext ctx = getServletContext();
		InputStream fis = new FileInputStream(file);
		String mimeType = ctx.getMimeType(file.getAbsolutePath());
		response.setContentType(mimeType != null ? mimeType : "application/octet-stream");
		response.setContentLength((int) file.length());
		response.setHeader("Contect-Disposition", "attachment; filename=\"" +fileName+ "\"");
		
		ServletOutputStream os = response.getOutputStream();
		byte[] buferData = new byte[1024];
		int read = 0;
		while((read = fis.read(buferData)) != -1) {
			os.write(buferData, 0, read);
		}
		os.flush();
		os.close();
		fis.close();
		System.out.println("File downloaded at client successfully");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		if(!ServletFileUpload.isMultipartContent(request)) {
			throw new ServletException("Content type is not multipart/form-data");
		}
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.write("<html><head></head><body>");
		try {
			List<FileItem> fileItemsList = uploader.parseRequest(request);
			Iterator<FileItem> fileItemsIterator = fileItemsList.iterator();
			while(fileItemsIterator.hasNext()) {
				FileItem fileItem = fileItemsIterator.next();
				System.out.println("FieldName="+fileItem.getFieldName());
				System.out.println("FileName="+fileItem.getName());
				System.out.println("ContentType="+fileItem.getContentType());
				System.out.println("Size in bytes="+fileItem.getSize());
				
				File file = new File(request.getServletContext().getAttribute("FILES_DIR")+File.separator+fileItem.getName());
				System.out.println("Absolute path at the server: "+file.getAbsolutePath());
				fileItem.write(file);
				out.write("File "+ fileItem.getName()+" uploaded successfully");
				out.write("<br>");
				out.write("<a href=\"UploadDownloadFileServlet?fileName="+fileItem.getName()+"\">Download "+fileItem.getName()+"</a>");
			}
		} catch(FileUploadException e) {
			out.write("Exception in uploading file.");
		} catch(Exception e) {
			e.printStackTrace();
			out.write("Exception in uploading file....");
		}
		out.write("</body></html>");
	}

}
```

#

# Servlet 3 FIle Upload

<!-- https://www.digitalocean.com/community/tutorials/servlet-3-file-upload-multipartconfig-part -->

- @MultipartConfig annotation
- javax.servlet.http.Part

### MultipartConfig

- to handle multipart/form-data (used for uploading file to server) requests 
- attributes for @MultipartConfig annotation
    - __fileSizeThreshold__ : after which the file will be written to disk. size value is in bytes, so 1024*1024*10 is 10MB.
    - __location__ : Directory where files will be stored by default, default value is "".
    - __maxFileSize__ : Maximum size allowed to upload a file, in bytes, default -1L (unlimited).
    - __maxRequestSize__ : Maximum size allowed for multipart/form-data request. Default is -1L (unlimited).
    
### Part interface

- represents a part or form item that was received within a multipart/form-data POST request.
- some methods are - `getInputStream()`, `write(String fileName)` to read and write file.

## HttpServletRequest Changes

- new methods got added in `HttpServletRequest` to get all the parts in multipart/form-data request through `getParts()` method.
- get a specific part using `getPart(String partName)`

## File Upload Example with Servlet 3 APIs

### HTML form

```fileupload.html
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Servlet 3 File Upload</title>
</head>
<body>
<form action="FileUploadServlet" method="post" enctype="multipart/form-data">
	Select file to upload: <input type="file" name="fileName">
	<br>
	<input type="submit" value="Upload">
</form>
</body>
</html>
```

### File Upload Servlet

```java
package com.hks.servlet;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class FileUploadServlet
 */
@WebServlet("/FileUploadServlet")
@MultipartConfig(fileSizeThreshold = 1024*1024*10,	// 10MB
				maxFileSize = 1024*1024*50,			// 50MB
				maxRequestSize = 1024*1024*100)		// 100MB
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/*
	 * Directory where uploaded files will be saved,
	 * it's relative to the application directory.
	 */
	private static final String UPLOAD_DIR = "uploads";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		// gets absolute path of the web application
		String applicationPath = request.getServletContext().getRealPath("");
		// constructs path of the directory to save the uploaded files
		String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
		
		// creates the save directory if it doesn't exist
		File fileSaveDir = new File(uploadFilePath);
		if(!fileSaveDir.exists()) {
			fileSaveDir.mkdirs();
		}
		System.out.println("Upload File Directory: " + fileSaveDir.getAbsolutePath());
		
		String fileName = null;
		// Get all the parts from request and write it to the file on the server
		for(Part part : request.getParts()) {
			fileName = getFileName(part);
			part.write(uploadFilePath + File.separator + fileName);
		}
		
		request.setAttribute("message", fileName + " File uploaded successfully!");
		getServletContext().getRequestDispatcher("/response.jsp").forward(request, response);
	}
	
	/*
	 * Utility method to get file name from HTTP header content-disposition 
	 */
	private String getFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		System.out.println("contect-disposition header="+contentDisp);
		String[] tokens = contentDisp.split(";");
		for(String token : tokens) {
			if(token.trim().startsWith("fileName")) {
				return token.substring(token.indexOf("=") + 2, token.length()-1);
			}
		}
		return "";
	}

}

```

- use @MultipartConfig annotation to specify different size parameters for upload file.
- use request header “content-disposition” attribute to get the file name sent by client.<!-- we are saving the file with same name in this example -->
- in this example, directory location to save the file is relative to web application. 


### Response JSP

```response.jsp
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
```

### Deployment Descriptor


<!-- # Servlet Database Connection and Log4j integration -->

#

# Java / JDBC DataSource

<!-- https://www.digitalocean.com/community/tutorials/java-datasource-jdbc-datasource-example -->

### Java Datasource

Java DataSource interface is present in `javax.sql` package and it only declare two overloaded methods `getConnection()` and `getConnection(String str1,String str2)`.

### JDBC DataSource

- preferred approach if you are looking for loose coupling for connectivity so that we can switch databases easily, connection pooling for transaction management and distributed systems support.

It is the responsibility of different Database vendors to provide different kinds of implementation of DataSource interface. For example MySQL JDBC Driver provides basic implementation of DataSource interface with `com.mysql.jdbc.jdbc2.optional.MysqlDataSource` class and Oracle database driver implements it with `oracle.jdbc.pool.OracleDataSource` class.

These implementation classes provide methods through which we can provide database server details with user credentials. Some of the other common features provided by these JDBC DataSource implementation classes are;
- Caching of PreparedStatement for faster processing
- Connection timeout settings
- Logging features
- ResultSet maximum size threshold


#


# Tomcat DataSource JNDI

- JNDI Context
- Most of the popular servlet containers provide built-in support for DataSource through Resource configuration and JNDI context. 
- Apache Tomcat provide three ways to configure DataSource in JNDI context.
    - __Application context.xml__ - context.xml in MET-INF directory. define Resource element in the context file and container will take care of loading and configuring it. drawbacks:
        - Since the context file is bundled with the WAR file, we need to build and deploy new WAR for every small configuration change. 
        - The datasource is created by container for the application usage only, so it can’t be used globally. We can’t share the datasource across multiple applications.
        - If there is a global datasource (server.xml) defined with same name, the application datasource is ignored.
    - __Server context.xml__ - If there are multiple applications in the server and you want to share DataSource across them, we can define that in the server context.xml file.
       - located in `apache-tomcat/conf`
       - scope of server context.xml file is application, so if you define a DataSource connection pool of 100 connections and there are 20 applications then the datasource will be created for each of the application. This will result in 2000 connections that will obviously consume all the database server resources and hurt application performance.
    - __server.xml and context.xml__ - We can define DataSource at global level by defining them in the server.xml `GlobalNamingResources` element. If we use this approach, then we need to define a `ResourceLink` from context.xml file of server or application specific. This is the preferred way when you are looking to share a common resource pool across multiple applications running on the server. Regarding resource link, whether to define it at server level context xml file or application level depends on your requirement.

## Tomcat DataSource JNDI Configuration Example

### server.xml

- add in tomcat server.xml, in `GlobalNamingResources` element
- Also make sure that database driver is present in the tomcat lib directory [mysql-connector-j-8.0.32.jar]

```server.xml
<GlobalNamingResources>
    <!-- Editable user database that can also be used by
         UserDatabaseRealm to authenticate users
    -->
    <Resource name="jdbc/MyDB" global="jdbc/MyDB"
				auth="Container" type="javax.sql.DataSource"
				driverClassName="com.mysql.jdbc.Driver"
				url="jdbc:mysql://localhost:3306/demo"
				username="demo" password="demo"
				maxActive="100" maxIdle="20"
				minIdle="5" maxWait="10000"/>

  </GlobalNamingResources>
```

### Resource Link Configuration - context.xml

```context.xml
<Context>

...
<ResourceLink name="jdbc/MyLocalDB"
				global="jdbc/MyDB"
				auth="container"
				type="javax.sql.DataSource" />
</Context>
```

Notice that resource link name is different than global link, we have to use this name in our java program to get the DataSource.


### servlet

```java
package com.hks.jdbc.datasource;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class JDBCDataSourceExampleServlet
 */
@WebServlet("/JDBCDataSourceExampleServlet")
public class JDBCDataSourceExampleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JDBCDataSourceExampleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		Context ctx = null;
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/MyLocalDB");
			
			con = ds.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery("select empid, name from Employee");
			
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.print("<html><body><h2>Employee Details</h2>");
			out.print("<table border=\"1\" cellspacing=10 cellpadding=5>");
			out.print("<th>Employee ID</th>");
            out.print("<th>Employee Name</th>");
            
            while(rs.next()) {
            	out.print("<tr>");
            	out.print("<td>" + rs.getInt("empid") + "</td>");
            	out.print("<td>" + rs.getString("name") + "</td>");
            	out.print("</tr>");
            }
            out.print("</table></body><br/>");
            
            // print database information
            out.print("<h3>Database Details</h3>");
            out.print("Database Product: "+con.getMetaData().getDatabaseProductName() + "<br/>");
            out.print("Database Driver: " + con.getMetaData().getDriverName() + "<br/>");
            out.print("</html>");
		}catch(NamingException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				stmt.close();
				con.close();
				ctx.close();
			}catch(SQLException e) {
				System.out.println("Exception in closing DB resources.");
			}catch(NamingException e) {
				System.out.println("Exception in closing context.");
			}
		}
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


The parts of the servlet code

```java
ctx = new InitialContext();
DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/MyLocalDB");
```

This is the way to get the JNDI resources defined to be used by the application. We could have written it in this way too;

```java
ctx = new InitialContext();
Context intitCtx = (Context) ctx.lookup("java:/comp/env");
DataSource ds = (DataSource) intitCtx.lookup("jdbc/MyLocalDB");

```


 
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
# Java / JDBC DataSource
https://www.digitalocean.com/community/tutorials/java-datasource-jdbc-datasource-example
-->
