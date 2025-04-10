package com.daw.excepciones;

public class DataAccessException extends Exception {

	private static final long serialVersionUID = 1L;

	public DataAccessException() {
	}

	public DataAccessException(String msg) {
		super(msg);
	}

	public DataAccessException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public DataAccessException(Throwable cause) {
		super(cause);
	}
}
