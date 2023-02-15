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
