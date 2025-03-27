package com.proyecto.Company_DB;

import java.sql.*;

import java.util.Scanner;

public class InsertarEmpleado {

	public static void main(String[] args) {

		Connection conn = null;
		Statement st = null;

		Scanner sc = new Scanner(System.in);

		System.out.println("Alta Nuevo Cliente");
		System.out.println("Dime el nombre del empleado");
		String nombre = sc.nextLine();
		System.out.println("Dime el apellido del empleado");
		String apellido = sc.nextLine();
		System.out.println("Dime el email del empleado");
		String email = sc.nextLine();
		System.out.println("Dime el telefono del empleado");
		String telefono = sc.nextLine();
		System.out.println("Dime la fecha de alta del empleado");
		String fecha_alta = sc.nextLine();
		System.out.println("Dime el titulo del empleado");
		String job_title = sc.nextLine();

		try {
			conn = DBUtils.getConnection();
			st = conn.createStatement();
			int numFilas = st.executeUpdate(
					"INSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE, HIRE_DATE, JOB_TITLE) VALUES (?, ?, ?, ?, ?, ?)");

			System.out.println("Se han insertado " + numFilas + " filas");
		} catch (SQLException e) {
			System.out.println("Error de conexión: " + e.getMessage());
		}

		sc.close();
	}
}