package modelos;

public class Customers {

	private long customerId;
	private String name, address, website;
	private double creditLimit;

	public Customers(long customerId, String name, String address, String website, double creditLimit) {
		this.customerId = customerId;
		this.name = name;
		this.address = address;
		this.website = website;
		this.creditLimit = creditLimit;
	}

	// Constructor para crear customer sin customerID
	public Customers(String name, String address, String website, double creditLimit) {
		this.name = name;
		this.address = address;
		this.website = website;
		this.creditLimit = creditLimit;
	}

	// Constructor para eliminar customer
	public Customers(long customerId) {
		this.customerId = customerId;
	}

	public long getCustomerId() {
		return customerId;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getWebsite() {
		return website;
	}

	public double getCreditLimit() {
		return creditLimit;
	}

}
