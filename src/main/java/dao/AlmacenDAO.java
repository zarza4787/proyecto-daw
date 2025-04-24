package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import excepciones.DataAccessException;
import excepciones.DatoIncorrrectoException;
import modelos.Almacen;
import utils.DBUtils;

public class AlmacenDAO implements Dao<Almacen> {

	private final String QUERY_INSERTAR_ALMACEN = "INSERT INTO WAREHOUSES (WAREHOUSE_NAME, LOCATION_ID) VALUES (?, ?)";
	private final String QUERY_ELIMINAR_ALMACEN = "DELETE FROM WAREHOUSES WHERE WAREHOUSE_ID = ?";
	private final String QUERY_ACTULIZAR_ALMACEN = "UPDATE WAREHOUSES SET WAREHOUSE_NAME = ?, LOCATION_ID = ? WHERE WAREHOUSE_ID = ?";
	private final String QUERY_OBTENER_TODOS = "SELECT * FROM WAREHOUSES";

	@Override
	public void insertar(Almacen e) throws DataAccessException {
		try (Connection conn = DBUtils.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(QUERY_INSERTAR_ALMACEN);

			ps.setString(1, e.getNameWareHouse());
			ps.setLong(2, e.getLocationID());

			int columnas_afectadas = ps.executeUpdate();

			// Nos dice si se ha modificado alguna columna
			if (columnas_afectadas == 0) {
				System.out.println("No se ha insertado ningun almacen en el sistema");
			} else {
				System.out.println("Almacén insertado con exito.");
			}
		} catch (SQLException sql) {
			throw new DataAccessException("Hubo un error al insertar el almacen: " + e.getNameWareHouse(), sql);
		}
	}

	@Override
	public void eliminar(Almacen e) throws DataAccessException {
		if (e == null || e.getWareHouseId() <= 0) {
			throw new DataAccessException("La ID del almacen que has proporcionado no es valida");
		}

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
		if (e == null || e.getWareHouseId() <= 0) {
			throw new DataAccessException("La ID del almacen que has propocionado no es valida");
		}

		try (Connection conn = DBUtils.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(QUERY_ACTULIZAR_ALMACEN);

			ps.setString(1, e.getNameWareHouse());
			ps.setLong(2, e.getLocationID());
			ps.setLong(3, e.getWareHouseId());

			int columnas_afectadas = ps.executeUpdate();

			// Nos dice si se ha modificado alguna columna
			if (columnas_afectadas == 0) {
				System.out.println("No se ha encontrado el almacén con el ID proporcionado");
			} else {
				System.out.println("Almacen actualizado con exito.");
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

		} catch (SQLException | DatoIncorrrectoException ex) {
			throw new DataAccessException("Error al obtener almacenes", ex);
		}
		return almacenesList;
	}

}