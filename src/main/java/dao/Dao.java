package dao;

import java.util.List;

import excepciones.DataAccessException;

public interface Dao<K> {

	void insertar(K obj) throws DataAccessException;

	void eliminar(K obj) throws DataAccessException;

	void actualizar(K obj) throws DataAccessException;

	List<K> obtenerTodos() throws DataAccessException;

}
