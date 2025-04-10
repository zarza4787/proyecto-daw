package com.daw.dao.controller;

import java.util.List;

import com.daw.dao.almacenes.AlmacenDAO;
import com.daw.dao.almacenes.AlmacenDAOImpl;
import com.daw.excepciones.DataAccessException;
import com.daw.excepciones.DatoIncorrrectoException;
import com.daw.modelos.Almacen;

public class AlmacenController {

	private AlmacenDAO almacenDAO;

	public AlmacenController() {
		this.almacenDAO = new AlmacenDAOImpl();
	}

	public void crearAlmacen(long locationId, String wareHouseName) throws DataAccessException {
		try {
			Almacen almacen = new Almacen(0, locationId, wareHouseName);
			almacenDAO.insertar(almacen);
		} catch (DatoIncorrrectoException e) {
			throw new DataAccessException("Datos incorrectos" + e);
		} catch (DataAccessException e) {
			throw new DataAccessException("Hubo un error al crear el almacen" + e);
		}
	}

	public void eliminarAlmacen(long wareHouseId) throws DataAccessException, DatoIncorrrectoException {
		try {
			Almacen almacen = new Almacen(wareHouseId, 0, "");
			almacenDAO.eliminar(almacen);
		} catch (DataAccessException e) {
			throw new DataAccessException("Hubo un error al crear el almacen" + e);
		}
	}

	public void actualizarAlmacen(long WareHouseId, long locationId, String wareHouseName)
			throws DatoIncorrrectoException, DataAccessException {
		try {
			Almacen almacen = new Almacen(WareHouseId, locationId, wareHouseName);
			almacenDAO.actualizar(almacen);
		} catch (DataAccessException e) {
			throw new DataAccessException("Hubo un error al crear el almacen" + e);
		}
	}

	public List<Almacen> obtenerTodosAlmacenes() throws DataAccessException {
		try {
			return almacenDAO.obtenerTodos();
		} catch (Exception e) {
			throw new DataAccessException("Error al obtener los almacenes", e);
		}
	}
}
