package com.hks.first.session;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutServlet() {
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
		response.setContentType("text/html");
		//Cookie loginCookie = null;
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals("JSESSIONID")) {
					System.out.println("JSESSIONID="+cookie.getValue());
					//break;
				}
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
		}
		Enumeration<String> attributes = request.getSession().getAttributeNames();
		while(attributes.hasMoreElements()) {
			String attribute = attributes.nextElement();
			System.out.println(attribute+": "+request.getSession().getAttribute(attribute));
		}
		// invalidate the session if exists
		HttpSession session = request.getSession(false);
		System.out.println("User="+session.getAttribute("user"));
		if(session != null) {
			session.invalidate();
		}
		response.sendRedirect("login.html");
		//doGet(request, response);
	}

}
