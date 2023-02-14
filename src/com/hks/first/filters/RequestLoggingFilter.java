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
