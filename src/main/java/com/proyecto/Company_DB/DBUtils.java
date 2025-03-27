package com.proyecto.Company_DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
	public static Connection getConnection() throws SQLException {
		final String USER = "company_db";
		final String PASS = "company";
		final String CONN_URL = "jdbc:oracle:thin:@//localhost:1521/XE";
		Connection conn = null;

		conn = DriverManager.getConnection(CONN_URL, USER, PASS);
		return conn;

	}
}
