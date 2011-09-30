package org.persistence.common;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQLManager {
	
	public static Integer getNewId(String name) throws Exception {
		String query = "SHOW TABLE STATUS LIKE '" + name + "'";
		Connection con = ConnectionManager.getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			return rs.getInt("Auto_increment");
		}
		return 1;
	}
}
