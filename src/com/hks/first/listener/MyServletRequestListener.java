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
