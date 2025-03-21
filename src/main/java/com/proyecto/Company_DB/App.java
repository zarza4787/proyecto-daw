package com.proyecto.Company_DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {
	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@//localhost:1521/XE";
		String user = "company_db";
		String password = "company";

		
		try (Connection conn = DriverManager.getConnection(url, user, password);
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery("SELECT * FROM employees order by EMPLOYEE_ID asc")) {

			
			while (rs.next()) {
				int id = rs.getInt("EMPLOYEE_ID");
				String nombre = rs.getString("FIRST_NAME");
		

				System.out.println("ID: " + id + ", Nombre: " + nombre);
			}

		} catch (SQLException e) {
			System.out.println("Error de conexión: " + e.getMessage());
		}
	}
}
