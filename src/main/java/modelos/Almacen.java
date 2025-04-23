package modelos;

import excepciones.DatoIncorrrectoException;

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

	// Constructor para el controller
	public Almacen(long locationId, String wareHouseName) throws DatoIncorrrectoException {
		if (wareHouseName == null) {
			throw new DatoIncorrrectoException("Los campos no pueden estar vacios");
		}

		this.locationId = locationId;
		this.wareHouseName = wareHouseName;
	}

	// Constructor para eliminar almacen
	public Almacen(long wareHouseId) throws DatoIncorrrectoException {
		if (wareHouseId <= 0) {
			throw new DatoIncorrrectoException("El ID del almacen debe ser mayor que 0");
		}

		this.wareHouseId = wareHouseId;
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
