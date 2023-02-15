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
