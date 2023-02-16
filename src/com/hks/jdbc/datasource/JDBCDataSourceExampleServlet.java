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
