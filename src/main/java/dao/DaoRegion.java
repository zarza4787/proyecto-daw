package dao;

import java.util.List;

import excepciones.DataAccessException;
import modelos.Countries;
import modelos.Locations;
import modelos.Region;

public interface DaoRegion {

	List<Region> obtenerRegiones() throws DataAccessException;

	List<Countries> obtenerPaisesPorRegion(int regionId) throws DataAccessException;

	List<Locations> obtenerUbicacionesPorPais(String countryId) throws DataAccessException;

}
