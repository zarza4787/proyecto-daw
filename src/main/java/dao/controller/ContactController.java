package dao.controller;

import java.util.List;

import dao.ContactsDAO;
import excepciones.DataAccessException;
import modelos.Contacts;
import modelos.Customers;

public class ContactController {

	private ContactsDAO contactsDAO;

	public ContactController() {
		this.contactsDAO = new ContactsDAO();
	}

	public void crearContacto(String firstName, String lastName, String email, String phone, long customerId)
			throws DataAccessException {
		try {
			Contacts nuevoContact = new Contacts(firstName, lastName, email, phone, customerId);
			contactsDAO.insertar(nuevoContact);
		} catch (DataAccessException e) {
			throw new DataAccessException("Hubo un error al crear el contacto", e);
		}

	}

	public void eliminarCustomer(long contactId) throws DataAccessException {
		try {
			Contacts contact = new Contacts(contactId);
			contactsDAO.eliminar(contact);
		} catch (DataAccessException e) {
			throw new DataAccessException("Hubo un error al eliminar al contacto", e);
		}
	}

	public void actualizarCustomer(long contactId, String firstName, String lastName, String email, String phone)
			throws DataAccessException {
		try {
			Contacts contactActualizar = new Contacts(firstName, lastName, email, phone);
			contactsDAO.actualizar(contactActualizar);
		} catch (Exception e) {
			throw new DataAccessException("Hubo un error al actualizar el empleado", e);
		}
	}

	public List<Contacts> obtenerTodosContactos() throws DataAccessException {
		try {
			return contactsDAO.obtenerTodos();
		} catch (Exception e) {
			throw new DataAccessException("Hubo un error al obtener a los contactos ", e);
		}
	}

}
