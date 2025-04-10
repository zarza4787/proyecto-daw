package com.daw.modelos;

import com.daw.excepciones.DatoIncorrrectoException;

public class Almacen {

	private long wareHouseId;
	private long locationId;
	private String wareHouseName;

	public Almacen(long wareHouseId, long locationId, String wareHouseName) throws DatoIncorrrectoException {
		if (wareHouseName == null) {
			throw new DatoIncorrrectoException("Los campos no pueden estar vacios");
		}

		this.wareHouseId = wareHouseId;
		this.locationId = locationId;
		this.wareHouseName = wareHouseName;
	}

	public long getWareHouseId() {
		return wareHouseId;
	}

	public long getLocationID() {
		return locationId;
	}

	public String getNameWareHouse() {
		return wareHouseName;
	}

	@Override
	public String toString() {
		return "Almacen [WAREHOUSE_ID=" + wareHouseId + ", LOCATION_ID=" + locationId + ", WAREHOUSE_NAME="
				+ wareHouseName + "]";
	}

}
