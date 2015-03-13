package com.db.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	private static Connection connection = null;
	
	private DBConnection () throws ClassNotFoundException, SQLException {
			String dbURL = "jdbc:mysql://localhost/jaspersoft";
			String driverName = "com.mysql.jdbc.Driver";
			String user = "root";
			String password = "";
			Class.forName(driverName);
			this.connection = DriverManager.getConnection(dbURL, user, password);
	}
	
	public static Connection getConnection () {
		if (connection == null) {
			try {
				DBConnection dbConnection =  new DBConnection();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return connection;
	}

}
