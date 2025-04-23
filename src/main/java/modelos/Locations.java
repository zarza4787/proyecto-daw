package modelos;

public class Locations {

	private long locationId;
	private String address, postalCode, city, state, countryId;

	public Locations(long locationId, String address, String postalCode, String city, String state, String countryId) {
		this.locationId = locationId;
		this.address = address;
		this.postalCode = postalCode;
		this.city = city;
		this.state = state;
		this.countryId = countryId;
	}

	public long getLocationId() {
		return locationId;
	}

	public String getAddress() {
		return address;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getCountryId() {
		return countryId;
	}

	@Override
	public String toString() {
		return "Locations [locationId=" + locationId + ", address=" + address + ", postalCode=" + postalCode + ", city="
				+ city + ", state=" + state + ", countryId=" + countryId + "]";
	}

}
