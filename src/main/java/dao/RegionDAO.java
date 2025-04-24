package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import excepciones.DataAccessException;
import modelos.Countries;
import modelos.Locations;
import modelos.Region;
import utils.DBUtils;

public class RegionDAO implements DaoRegion {

	private final String QUERY_OBTENER_REGIONES = "SELECT * FROM REGIONS";
	private final String QUERY_OBTENER_PAIS_POR_REGION = "SELECT * FROM COUNTRIES WHERE REGION_ID = ?";
	private final String QUERY_OBTENER_UBICACIONES_POR_PAIS = "SELECT * FROM LOCATIONS WHERE COUNTRY_ID = ?";

	@Override
	public List<Region> obtenerRegiones() throws DataAccessException {
		List<Region> regionesList = new ArrayList<>();

		try (Connection conn = DBUtils.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(QUERY_OBTENER_REGIONES);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				long regionId = rs.getLong("REGION_ID");
				String regionName = rs.getString("REGION_NAME");

				Region region = new Region(regionId, regionName);
				regionesList.add(region);
			}

		} catch (SQLException sql) {
			throw new DataAccessException("Error de base de datos", sql);
		}
		return regionesList;

	}

	@Override
	public List<Countries> obtenerPaisesPorRegion(int regionId) throws DataAccessException {
		List<Countries> paisesList = new ArrayList<Countries>();

		try (Connection conn = DBUtils.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(QUERY_OBTENER_PAIS_POR_REGION);

			ps.setInt(1, regionId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				String countryId = rs.getString("COUNTRY_ID");
				String countryName = rs.getString("COUNTRY_NAME");
				long regionID = rs.getLong("REGION_ID");

				Countries countries = new Countries(countryId, countryName, regionID);
				paisesList.add(countries);
			}

		} catch (SQLException sql) {
			throw new DataAccessException("Error de base de datos", sql);
		}

		return paisesList;
	}

	@Override
	public List<Locations> obtenerUbicacionesPorPais(String countryId) throws DataAccessException {
		List<Locations> locationsList = new ArrayList<Locations>();

		try (Connection conn = DBUtils.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(QUERY_OBTENER_UBICACIONES_POR_PAIS);

			ps.setString(1, countryId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				long locationId = rs.getLong("LOCATION_ID");
				String address = rs.getString("ADDRESS");
				String postalCode = rs.getString("POSTAL_CODE");
				String city = rs.getString("CITY");
				String state = rs.getString("STATE");
				String regionId = rs.getString("COUNTRY_ID");

				Locations locations = new Locations(locationId, address, postalCode, city, state, regionId);
				locationsList.add(locations);
			}

		} catch (SQLException sql) {
			throw new DataAccessException("Error de base de datos", sql);
		}

		return locationsList;
	}

}
