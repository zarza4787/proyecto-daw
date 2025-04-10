package com.daw.dao.controller;

import java.util.List;

import com.daw.dao.empleados.EmpleadoDAO;
import com.daw.dao.empleados.EmpleadoDAOImpl;
import com.daw.excepciones.DataAccessException;
import com.daw.excepciones.DatoIncorrrectoException;
import com.daw.modelos.Empleado;

public class EmpleadoController {

	private EmpleadoDAO empleadoDAO;

	public EmpleadoController() {
		this.empleadoDAO = new EmpleadoDAOImpl();
	}

	public void crearEmpleado(String firstName, String lastName, String email, String phone, String hireDate,
			String jobTitle) throws DataAccessException {
		try {
			Empleado nuevoEmpleado = new Empleado(0, firstName, lastName, email, phone, hireDate, 0, jobTitle);
			empleadoDAO.insertar(nuevoEmpleado);
		} catch (DatoIncorrrectoException e) {
			throw new DataAccessException("Datos incorrectos para el nuevo empleado", e);
		} catch (DataAccessException e) {
			throw new DataAccessException("Hubo un error al crear el nuevo empleado", e);
		}
	}

	public void eliminarEmpleado(long employeeId) throws DataAccessException {
		try {
			Empleado empleado = new Empleado(employeeId);
			empleadoDAO.eliminar(empleado);
		} catch (Exception e) {
			throw new DataAccessException("Hubo un error al eliminar al empleado", e);
		}
	}

	public void actualizarEmpleado(long employeeId, String firstName, String lastName, String email, String phone,
			String hireDate, String jobTitle) throws DatoIncorrrectoException, DataAccessException {
		try {
			Empleado empleado = new Empleado(employeeId, firstName, lastName, email, phone, hireDate, 0, jobTitle);
			empleadoDAO.actualizar(empleado);
		} catch (DatoIncorrrectoException e) {
			throw new DataAccessException("Error en los datos introducidos", e);
		} catch (DataAccessException e) {
			throw new DataAccessException("Hubo un error al actualizar el empleado", e);
		}
	}

	public List<Empleado> obtenerTodosEmpleados() throws DataAccessException {
		try {
			return empleadoDAO.obtenerTodos();
		} catch (Exception e) {
			throw new DataAccessException("Hubo un error al obtener a los empleados " + e);
		}
	}
}
