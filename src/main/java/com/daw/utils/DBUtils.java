package com.daw.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
	public static Connection getConnection() throws SQLException {
		final String USER = "company_db";
		final String PASS = "company";
		final String CONN_URL = "jdbc:oracle:thin:@//localhost:1521/XE";
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(CONN_URL, USER, PASS);
			return conn;
		} catch (SQLException e) {
			System.out.println("Error al conectar a la base de datos: " + e.getMessage());
			throw e;
		}

	}
}
