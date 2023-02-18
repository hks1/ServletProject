package com.hks.jdbc.datasource;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

public class DBCPDataSourceFactory {
	
	public static DataSource getDataSource(String dbType) {
		Properties props = new Properties();
		FileInputStream fis = null;
		BasicDataSource ds = new BasicDataSource();
		try {
			fis = new FileInputStream("db.properties");
			props.load(fis);
		}catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		if("mysql".equals(dbType)) {
			ds.setDriverClassName(props.getProperty("MYSQL_DB_DRIVER_CLASS"));
			ds.setUrl(props.getProperty("MYSQL_DB_URL"));
			ds.setUsername("MYSQL_DB_USERNAME");
			ds.setPassword("MYSQL_DB_PASSWORD");
		}else if("oracle".equals(dbType)) {
			ds.setDriverClassName(props.getProperty("ORACLE_DB_DRIVER_CLASS"));
			ds.setUrl("ORACLE_DB_URL");
			ds.setUsername("ORACLE_DB_USERNAME");
			ds.setPassword("ORACLE_DB_PASSWORD");
		}else {
			return null;
		}
		return ds;
	}

}
