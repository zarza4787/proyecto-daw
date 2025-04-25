package modelos;

public class Contacts {

	private long contactId;
	private String firstName, lastName, email, phone;
	private long customerId;

	public Contacts(long contactId, String firstName, String lastName, String email, String phone, long customerId) {
		this.contactId = contactId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.customerId = customerId;
	}

	// Constructor para insertar
	public Contacts(String firstName, String lastName, String email, String phone, long customerId) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.customerId = customerId;
	}

	// Constructor para actualizar
	public Contacts(String firstName, String lastName, String email, String phone) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
	}

	// Constructor para eliminar
	public Contacts(long contactId) {
		this.contactId = contactId;
	}

	public long getContactId() {
		return contactId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public long getCustomerId() {
		return customerId;
	}

}
