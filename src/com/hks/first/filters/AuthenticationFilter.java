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
