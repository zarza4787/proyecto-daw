package dao.controller;

import java.util.List;

import dao.RegionDAO;
import excepciones.DataAccessException;
import modelos.Countries;
import modelos.Locations;
import modelos.Region;

public class RegionController {

	private RegionDAO regionDAO;

	public RegionController() {
		this.regionDAO = new RegionDAO();
	}

	public List<Region> obtenerTodasRegiones() throws DataAccessException {
		try {
			return regionDAO.obtenerRegiones();
		} catch (Exception e) {
			throw new DataAccessException("Error al obtener las regiones", e);
		}
	}

	public List<Countries> obtenerPaisesPorRegion(int regionId) throws DataAccessException {

		try {
			return regionDAO.obtenerPaisesPorRegion(regionId);
		} catch (Exception e) {
			throw new DataAccessException("Error al obtener los paises por region", e);
		}
	}

	public List<Locations> obtenerUbicacionesPorPais(String countryId) throws DataAccessException {
		try {
			return regionDAO.obtenerUbicacionesPorPais(countryId);
		} catch (Exception e) {
			throw new DataAccessException("Error al obtener las ubicaciones por paises", e);
		}
	}

}
