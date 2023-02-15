package com.hks.first.listener;

import java.io.File;
import java.util.Properties;

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
    	String rootPath = System.getProperty("catalina.home");
//    	Properties sysProps = System.getProperties();
//    	sysProps.entrySet().forEach(kv -> System.out.println(kv.getKey() + " :: " + kv.getValue()));
    	System.out.println("root path ::"+rootPath);
    	ServletContext ctx = servletContextEvent.getServletContext();
    	String relativePath = ctx.getInitParameter("tempfile.dir");
    	
    	File file = new File(rootPath + File.separator + relativePath);
    	if(!file.exists()) file.mkdirs();
    	
    	System.out.println("File Directory created to be used for storing files.");
    	
    	ctx.setAttribute("FILES_DIR_FILE", file);
    	ctx.setAttribute("FILES_DIR", rootPath + File.separator + relativePath);
    }
	
}
