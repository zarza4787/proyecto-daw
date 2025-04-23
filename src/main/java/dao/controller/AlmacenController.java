package dao.controller;

import java.util.List;

import dao.AlmacenDAO;
import excepciones.DataAccessException;
import excepciones.DatoIncorrrectoException;
import modelos.Almacen;
import modelos.Region;

public class AlmacenController {

	private AlmacenDAO almacenDAO;

	public AlmacenController() {
		this.almacenDAO = new AlmacenDAO();
	}

	public void crearAlmacen(long locationId, String wareHouseName) throws DataAccessException {
		try {
			Almacen almacen = new Almacen(locationId, wareHouseName);
			almacenDAO.insertar(almacen);
		} catch (DatoIncorrrectoException e) {
			throw new DataAccessException("Datos incorrectos", e);
		} 
	}

	public void eliminarAlmacen(long wareHouseId) throws DataAccessException, DatoIncorrrectoException {
		try {
			Almacen almacen = new Almacen(wareHouseId);
			almacenDAO.eliminar(almacen);
		} catch (DataAccessException e) {
			throw new DataAccessException("Hubo un error al eliminar el almacen", e);
		}
	}

	public void actualizarAlmacen(long wareHouseId, long locationId, String wareHouseName)
			throws DatoIncorrrectoException, DataAccessException {
		try {
			Almacen almacen = new Almacen(wareHouseId, locationId, wareHouseName);
			almacenDAO.actualizar(almacen);
		} catch (DataAccessException e) {
			throw new DataAccessException("Hubo un error al actualizar el almacen" + e);
		}
	}

	public List<Almacen> obtenerTodosAlmacenes() throws DataAccessException {
		try {
			return almacenDAO.obtenerTodos();
		} catch (Exception e) {
			throw new DataAccessException("Error al obtener los almacenes", e);
		}
	}

	public List<Region> obtenerTodasRegiones() throws DataAccessException {

		try {
			return almacenDAO.obtenerRegiones();
		} catch (Exception e) {
			throw new DataAccessException("Error al obtener las regiones", e);
		}

	}
}
