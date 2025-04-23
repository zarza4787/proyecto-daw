package modelos;

public class Countries {

	private String countryID;
	private String countryName;
	private long regionId;

	public Countries(String countryID, String countryName, long regionId) {
		this.countryID = countryID;
		this.countryName = countryName;
		this.regionId = regionId;
	}
	
	

	public Countries(long regionId) {
		super();
		this.regionId = regionId;
	}



	public String getCountryID() {
		return countryID;
	}

	public String getCountryName() {
		return countryName;
	}

	public long getRegionId() {
		return regionId;
	}

}
