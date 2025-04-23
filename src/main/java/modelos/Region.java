package modelos;

public class Region {

	private long regionId;
	private String regionName;

	public Region(long regionId, String regionName) {
		this.regionId = regionId;
		this.regionName = regionName;
	}

	public long getRegionId() {
		return regionId;
	}

	public String getRegionName() {
		return regionName;
	}

	@Override
	public String toString() {
		return "Region [regionId=" + regionId + ", regionName=" + regionName + "]";
	}

	

}
