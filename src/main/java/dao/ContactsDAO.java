package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import excepciones.DataAccessException;
import modelos.Contacts;
import modelos.Countries;
import modelos.Customers;
import utils.DBUtils;

public class ContactsDAO implements Dao<Contacts> {

	private final String QUERY_INSERTAR_CONTACTO = "INSERT INTO CONTACTS (FIRST_NAME, LAST_NAME, EMAIL, PHONE)";
	private final String QUERY_ELIMINAR_CONTACTO = "DELETE FROM CONTACTS WHERE CONTACT_ID = ?";
	private final String QUERY_ACTUALIZAR_CONTACTO = "UPDATE CONTACTS SET FIRTS_NAME = ?, LAST_NAME = ?, EMIAL = ?, PHONE = ? WHERE CONTACT_ID = ?";
	private final String QUERY_OBTENER_TODO = "SELECT * FROM CONTACTS";
	private final String QUERY_OBTENER_CUSTOMER_POR_CONTACTO = "SELECT * FROM CONTACTS WHERE CUSTOMER_ID = ?";

	@Override
	public void insertar(Contacts c) throws DataAccessException {

		try (Connection conn = DBUtils.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(QUERY_INSERTAR_CONTACTO);

			ps.setString(1, c.getFirstName());
			ps.setString(2, c.getLastName());
			ps.setString(3, c.getEmail());
			ps.setString(4, c.getPhone());

			ps.executeUpdate();

		} catch (SQLException sql) {
			throw new DataAccessException("Error al insertar un contacto", sql);
		}
	}

	@Override
	public void eliminar(Contacts c) throws DataAccessException {

		try (Connection conn = DBUtils.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(QUERY_ELIMINAR_CONTACTO);

			ps.setLong(1, c.getContactId());

			int columnasModificadas = ps.executeUpdate();

			if (columnasModificadas == 0) {
				throw new DataAccessException("No se pudo eliminar el contacto, ID no encontrado");
			}

		} catch (SQLException sql) {
			throw new DataAccessException("Hubo un error al eliminar el contacto", sql);
		}
	}

	@Override
	public void actualizar(Contacts c) throws DataAccessException {

		try (Connection conn = DBUtils.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(QUERY_ACTUALIZAR_CONTACTO);

			ps.setString(1, c.getFirstName());
			ps.setString(2, c.getLastName());
			ps.setString(3, c.getEmail());
			ps.setString(4, c.getPhone());
			ps.setLong(5, c.getContactId());

			ps.executeUpdate();

		} catch (SQLException sql) {
			throw new DataAccessException("Hubo un error al actualizar el empleado", sql);
		}

	}

	@Override
	public List<Contacts> obtenerTodos() throws DataAccessException {
		List<Contacts> contactosList = new ArrayList<>();

		try (Connection conn = DBUtils.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(QUERY_OBTENER_TODO);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				long contactId = rs.getLong("CONTACT_ID");
				String firstName = rs.getString("FIRST_NAME");
				String lastName = rs.getString("LAST_NAME");
				String email = rs.getString("EMAIL");
				String phone = rs.getString("PHONE");
				long customerId = rs.getLong("CUSTOMER_ID");

				Contacts contacto = new Contacts(contactId, firstName, lastName, email, phone, customerId);
				contactosList.add(contacto);
			}

		} catch (SQLException ex) {
			throw new DataAccessException("Error al obtener empleados", ex);
		}

		return contactosList;
	}

	public List<Customers> obtenerCustomerPorContacto(long customerId) throws DataAccessException {
		List<Customers> customersList = new ArrayList<Customers>();

		try (Connection conn = DBUtils.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(QUERY_OBTENER_CUSTOMER_POR_CONTACTO);

			ps.setLong(1, customerId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				long customerID = rs.getLong("CUSTOMER_ID");
				String name = rs.getString("NAME");
				String address = rs.getString("ADDRESS");
				String website = rs.getString("WEBSITE");
				double creditLimit = rs.getDouble("CREDIT_LIMIT");

				Customers customer = new Customers(customerID, name, address, website, creditLimit);
				customersList.add(customer);
			}

		} catch (SQLException sql) {
			throw new DataAccessException("Error de base de datos", sql);
		}

		return customersList;
	}

}
