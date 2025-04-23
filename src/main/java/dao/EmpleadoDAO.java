package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import excepciones.DataAccessException;
import excepciones.DatoIncorrrectoException;
import modelos.Empleado;
import utils.DBUtils;

public class EmpleadoDAO implements Dao<Empleado> {

	private final String QUERY_INSERTAR_EMPLEADO = "INSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE, HIRE_DATE, MANAGER_ID, JOB_TITLE) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private final String QUERY_ELIMINAR_EMPLEADO = "DELETE FROM EMPLOYEES WHERE EMPLOYEE_ID = ?";
	private final String QUERY_ACTUALIZAR_EMPLEADO = "UPDATE EMPLOYEES SET FIRST_NAME = ?, LAST_NAME = ?, EMAIL = ?, PHONE = ?, JOB_TITLE = ? WHERE EMPLOYEE_ID = ?";
	private final String QUERY_OBTENER_TODOS = "SELECT * FROM EMPLOYEES";

	@Override
	public void insertar(Empleado e) throws DataAccessException {

		if (e == null || e.getName() == null || e.getEmail() == null || e.getHireDate() == null) {
			throw new DataAccessException("Datos del empleado inválidos");
		}

		try (Connection conn = DBUtils.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(QUERY_INSERTAR_EMPLEADO);

			ps.setString(1, e.getName());
			ps.setString(2, e.getLastName());
			ps.setString(3, e.getEmail());
			ps.setString(4, e.getPhone());
			Date hireDateSQL = Date.valueOf(e.getHireDate());
			ps.setDate(5, hireDateSQL);
			ps.setLong(6, e.getManagerId());
			ps.setString(7, e.getJobTitle());

			ps.executeUpdate();

		} catch (SQLException sql) {
			throw new DataAccessException("Error al insertar el empleado", sql);
		}
	}

	@Override
	public void eliminar(Empleado e) throws DataAccessException {
		try (Connection conn = DBUtils.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(QUERY_ELIMINAR_EMPLEADO);

			ps.setLong(1, e.getEmployeeID());

			ps.executeUpdate();

		} catch (SQLException sql) {
			throw new DataAccessException("Hubo un error al eliminar el empleado", sql);
		}

	}

	@Override
	public void actualizar(Empleado e) throws DataAccessException {
		try (Connection conn = DBUtils.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(QUERY_ACTUALIZAR_EMPLEADO);

			ps.setString(1, e.getName());
			ps.setString(2, e.getLastName());
			ps.setString(3, e.getEmail());
			ps.setString(4, e.getPhone());
			ps.setString(5, e.getJobTitle());
			ps.setLong(6, e.getEmployeeID());

			ps.executeUpdate();

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
				long managerId = rs.getLong("MANAGER_ID");
				String jobTitle = rs.getString("JOB_TITLE");

				Empleado empleado = new Empleado(employeeId, firstName, lastName, email, phone, hireDate, managerId,
						jobTitle);
				empleadosList.add(empleado);
			}

		} catch (SQLException | DatoIncorrrectoException ex) {
			throw new DataAccessException("Error al obtener empleados", ex);
		}

		return empleadosList;
	}

}
