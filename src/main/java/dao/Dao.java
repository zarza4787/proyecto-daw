package dao;

import java.util.List;

import excepciones.DataAccessException;

public interface Dao<E> {

	void insertar(E obj) throws DataAccessException;

	void eliminar(E obj) throws DataAccessException;

	void actualizar(E obj) throws DataAccessException;

	List<E> obtenerTodos() throws DataAccessException;

}
