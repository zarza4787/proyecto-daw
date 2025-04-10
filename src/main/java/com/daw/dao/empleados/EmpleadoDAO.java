package com.daw.dao.empleados;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.daw.dao.Dao;
import com.daw.excepciones.DataAccessException;
import com.daw.excepciones.DatoIncorrrectoException;
import com.daw.modelos.*;
import com.daw.utils.DBUtils;

public class EmpleadoDAO implements Dao<Empleado> {

	private final String QUERY_INSERTAR_EMPLEADO = "INSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE, HIRE_DATE, JOB_TITLE) VALUES (?, ?, ?, ?, ?, ?)";
	private final String QUERY_ELIMINAR_EMPLEADO = "DELETE FROM EMPLOYEES WHERE EMPLOYEE_ID = ?";
	private final String QUERY_ACTULIZAR_EMPLEADO = "UPDATE EMPLOYEES SET FIRST_NAME = ?, LAST_NAME = ?, EMAIL = ?, PHONE = ?, JOB_TITLE = ? WHERE EMPLOYEE_ID = ?";
	private final String QUERY_OBTENER_TODOS = "SELECT * FROM EMPLOYEES";

	@Override
	public void insertar(Empleado e) throws DataAccessException {
		try (Connection conn = DBUtils.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(QUERY_INSERTAR_EMPLEADO);

			ps.setString(1, e.getName());
			ps.setString(2, e.getLastName());
			ps.setString(3, e.getEmail());
			ps.setString(4, e.getPhone());
			Date hireDateSQL = Date.valueOf(e.getHireDate());
			ps.setDate(5, hireDateSQL);
			ps.setString(6, e.getJobTitle());

			int columnas_afectadas = ps.executeUpdate();

			// Nos dice si se ha modificado alguna columna
			if (columnas_afectadas == 0) {
				System.out.println("No se a insertado ningun empleado en el sistema");
			} else {
				System.out.println("Empleado creado con éxito.");
				System.out.println(columnas_afectadas);
			}

		} catch (SQLException sql) {
			throw new DataAccessException("Error al insertar el empleado", sql);
		}
	}

	@Override
	public void eliminar(Empleado e) throws DataAccessException {
		try (Connection conn = DBUtils.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(QUERY_ELIMINAR_EMPLEADO);

			ps.setLong(1, e.getEmployeeID());

			int columnas_afectadas = ps.executeUpdate();

			// Nos dice si se ha modificado alguna columna
			if (columnas_afectadas == 0) {
				System.out.println("No se encontró el empleado con el ID proporcionado");
			} else {
				System.out.println("Empleado eliminado con éxito.");
				System.out.println(columnas_afectadas);
			}
		} catch (SQLException sql) {
			throw new DataAccessException("Hubo un error al eliminar el empleado", sql);
		}

	}

	@Override
	public void actualizar(Empleado e) throws DataAccessException {
		try (Connection conn = DBUtils.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(QUERY_ACTULIZAR_EMPLEADO);

			ps.setLong(1, e.getEmployeeID());
			ps.setString(2, e.getName());
			ps.setString(3, e.getLastName());
			ps.setString(4, e.getEmail());
			ps.setString(5, e.getPhone());
			ps.setString(6, e.getJobTitle());

			int columnas_afectadas = ps.executeUpdate();

			// Nos dice si se ha modificado alguna columna
			if (columnas_afectadas == 0) {
				System.out.println("No se actualizado ningun empleado");
			} else {
				System.out.println("Empleado actualizado con éxito.");
			}

		} catch (SQLException sql) {
			throw new DataAccessException("Hubo un error al actualizar el empleado", sql);
		}
	}

	@Override
	public List<Empleado> obtenerTodos() throws DataAccessException {
		List<Empleado> empleadosList = new ArrayList<>();

		try (Connection conn = DBUtils.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(QUERY_OBTENER_TODOS);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				long employeeId = rs.getLong("EMPLOYEE_ID");
				String firstName = rs.getString("FIRST_NAME");
				String lastName = rs.getString("LAST_NAME");
				String email = rs.getString("EMAIL");
				String phone = rs.getString("PHONE");
				String hireDate = rs.getString("HIRE_DATE");
				String jobTitle = rs.getString("JOB_TITLE");

				Empleado empleado = new Empleado(employeeId, firstName, lastName, email, phone, hireDate, 0, jobTitle);
				empleadosList.add(empleado);
			}

		} catch (SQLException sql) {
			System.out.println(sql.getMessage());
		} catch (DatoIncorrrectoException e) {
			System.out.println(e.getMessage());
		}

		return empleadosList;
	}

}
