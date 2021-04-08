/**
 * 
 */
package com.locnv.jbdc;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author vanloc
 *
 */
public class JDBCConnection {

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/electricstoredb";

//  Database credentials
	static final String USER = "root";
	static final String PASS = "123456";

	public static Connection getJDBCConnection() {
		Connection connection = null;
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Connect DB succsess");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return connection;
	}

}
