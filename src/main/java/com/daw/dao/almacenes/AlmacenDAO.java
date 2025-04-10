package com.daw.dao.almacenes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.daw.dao.Dao;
import com.daw.excepciones.DataAccessException;
import com.daw.excepciones.DatoIncorrrectoException;
import com.daw.modelos.Almacen;
import com.daw.utils.DBUtils;

public class AlmacenDAO implements Dao<Almacen> {

	private final String QUERY_INSERTAR_ALMACEN = "INSERT INTO WAREHOUSES (WAREHOUSE_ID, WAREHOUSE_NAME, LOCATION_ID) VALUES (?, ?, ?)";
	private final String QUERY_ELIMINAR_ALMACEN = "DELETE FROM WAREHOUSES WHERE WAREHOUSE_ID = ?";
	private final String QUERY_ACTULIZAR_ALMACEN = "UPDATE WAREHOUSES SET WAREHOUSE_ID = ?, WAREHOUSE_NAME = ?, LOCATION_ID = ?";
	private final String QUERY_OBTENER_TODOS = "SELECT * FROM WAREHOUSES";

	@Override
	public void insertar(Almacen e) throws DataAccessException {
		try (Connection conn = DBUtils.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(QUERY_INSERTAR_ALMACEN);

			ps.setLong(1, e.getWareHouseId());
			ps.setString(2, e.getNameWareHouse());
			ps.setLong(3, e.getLocationID());

			int columnas_afectadas = ps.executeUpdate();

			// Nos dice si se ha modificado alguna columna
			if (columnas_afectadas == 0) {
				System.out.println("No se ha insertado ningun almacen en el sistema");
			} else {
				System.out.println("Almacén eliminado con éxito.");
			}
		} catch (SQLException sql) {
			throw new DataAccessException("Hubo un error al insertar un nuevo almacen", sql);
		}
	}

	@Override
	public void eliminar(Almacen e) throws DataAccessException {
		try (Connection conn = DBUtils.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(QUERY_ELIMINAR_ALMACEN);

			ps.setLong(1, e.getWareHouseId());

			int columnas_afectadas = ps.executeUpdate();

			// Nos dice si se ha modificado alguna columna
			if (columnas_afectadas == 0) {
				System.out.println("No se encontró el almacén con el ID proporcionado");
			} else {
				System.out.println("Almacen eliminado con exito.");
			}
		} catch (SQLException sql) {
			throw new DataAccessException("Hubo un error al eliminar el almacén" + e.getNameWareHouse(), sql);
		}

	}

	@Override
	public void actualizar(Almacen e) throws DataAccessException {
		if (e == null) {
			throw new DataAccessException("El almacen no puede ser nulo");
		}

		try (Connection conn = DBUtils.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(QUERY_ACTULIZAR_ALMACEN);

			ps.setString(1, e.getNameWareHouse());
			ps.setLong(2, e.getLocationID());

			int columnas_afectadas = ps.executeUpdate();

			// Nos dice si se ha modificado alguna columna
			if (columnas_afectadas == 0) {
				System.out.println("No se ha encontrado el almacén con el ID proporcionado");
			} else {
				System.out.println("Almacen eliminado con exito.");
			}

		} catch (SQLException sql) {
			throw new DataAccessException("Hubo un error al actualizar el almacen" + e.getNameWareHouse(), sql);
		}
	}

	@Override
	public List<Almacen> obtenerTodos() throws DataAccessException {
		List<Almacen> almacenesList = new ArrayList<>();

		try (Connection conn = DBUtils.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(QUERY_OBTENER_TODOS);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				long wareHouseId = rs.getLong("WAREHOUSE_ID");
				String wareHouseName = rs.getString("WAREHOUSE_NAME");
				long locationId = rs.getLong("LOCATION_ID");

				Almacen almacen = new Almacen(wareHouseId, locationId, wareHouseName);
				almacenesList.add(almacen);
			}

		} catch (SQLException sql) {
			throw new DataAccessException("Error de base de datos", sql);
		} catch (DatoIncorrrectoException e) {
			throw new DataAccessException("Datos incorrectos", e);
		}

		return almacenesList;
	}

}