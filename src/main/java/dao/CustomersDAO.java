package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import excepciones.DataAccessException;
import modelos.Customers;
import utils.DBUtils;

public class CustomersDAO implements Dao<Customers> {

	private final String QUERY_INSERTAR_CUSTOMER = "INSERT INTO CUSTOMERS (NAME, ADDRESS, WEBSITE, CREDIT_LIMIT) VALUES (?, ?, ?, ?)";
	private final String QUERY_ACTUALIZAR_CUSTOMER = "UPDATE CUSTOMERS SET NAME = ?, ADDRESS = ?, WEBSITE = ?, CREDIT_LIMIT = ? WHERE customer_id = ?";
	private final String QUERY_ELIMINAR_CUSTOMER = "DELETE FROM CUSTOMERS WHERE CUSTOMER_ID = ?";
	private final String QUERY_OBTENER_TODOS_CUSTOMERS = "SELECT * FROM CUSTOMERS";

	@Override
	public void insertar(Customers c) throws DataAccessException {

		if (c == null || c.getName() == null || c.getAddress() == null || c.getWebsite() == null
				|| c.getCreditLimit() <= 0) {
			throw new DataAccessException("Datos del cliente no validos");
		}

		try (Connection conn = DBUtils.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(QUERY_INSERTAR_CUSTOMER);

			ps.setString(1, c.getName());
			ps.setString(2, c.getAddress());
			ps.setString(3, c.getWebsite());
			ps.setDouble(4, c.getCreditLimit());

			ps.executeUpdate();

		} catch (SQLException sql) {
			throw new DataAccessException("Error al actualizar a un cliente", sql);
		}
	}

	@Override
	public void eliminar(Customers c) throws DataAccessException {
		try (Connection conn = DBUtils.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(QUERY_ELIMINAR_CUSTOMER);

			ps.setLong(1, c.getCustomerId());

			int columnasModificadas = ps.executeUpdate();

			if (columnasModificadas == 0) {
				throw new DataAccessException("No se pudo eliminar el empleado, ID no encontrado");
			}

		} catch (SQLException sql) {
			throw new DataAccessException("Error al actualizar a un cliente", sql);
		}

	}

	@Override
	public void actualizar(Customers c) throws DataAccessException {
		try (Connection conn = DBUtils.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(QUERY_ACTUALIZAR_CUSTOMER);

			ps.setString(1, c.getName());
			ps.setString(2, c.getAddress());
			ps.setString(3, c.getWebsite());
			ps.setDouble(4, c.getCreditLimit());
			ps.setLong(5, c.getCustomerId());

			ps.executeUpdate();

		} catch (SQLException sql) {
			throw new DataAccessException("Error al actualizar a un cliente", sql);
		}

	}

	@Override
	public List<Customers> obtenerTodos() throws DataAccessException {
		List<Customers> customersList = new ArrayList<Customers>();

		try (Connection conn = DBUtils.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(QUERY_OBTENER_TODOS_CUSTOMERS);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				long customerId = rs.getLong("CUSTOMER_ID");
				String name = rs.getString("NAME");
				String address = rs.getString("ADDRESS");
				String website = rs.getString("WEBSITE");
				double creditLimit = rs.getDouble("CREDIT_LIMIT");

				Customers customer = new Customers(customerId, name, address, website, creditLimit);
				customersList.add(customer);
			}

		} catch (SQLException ex) {
			throw new DataAccessException("Error al obtener a los clientes", ex);
		}

		return customersList;
	}

	public Customers obtenerPorId(long customerId) throws DataAccessException {
		String QUERY_OBTENER_POR_ID = "SELECT * FROM CUSTOMERS WHERE customer_id = " + customerId;

		try (Connection conn = DBUtils.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(QUERY_OBTENER_POR_ID);
			ps.setLong(1, customerId);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				long customerID = rs.getLong("CUSTOMER_ID");
				String name = rs.getString("NAME");
				String address = rs.getString("ADDRESS");
				String website = rs.getString("WEBSITE");
				double creditLimit = rs.getDouble("CREDIT_LIMIT");

				return new Customers(customerID, name, address, website, creditLimit);

			} else {
				return null;
			}

		} catch (SQLException ex) {
			throw new DataAccessException("Error al obtener a los clientes", ex);
		}

	}

}
