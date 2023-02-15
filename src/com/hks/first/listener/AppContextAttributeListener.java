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
