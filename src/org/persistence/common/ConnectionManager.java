package org.persistence.common;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {
	private static Connection conn = null;
	
	public static Connection getConnection() throws Exception{
		if(conn!=null){
			return conn;
		} else {
			try {
				String userName = "root";
				String password = "root";
				String url = "jdbc:mysql://localhost/persistence";
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				conn = DriverManager.getConnection(url, userName, password);
				return conn;
			} catch (Exception e) {
				System.err.println("Cannot connect to database server");
				throw new Exception("Could not connect to database server!");
			}
		}			
	}
}
