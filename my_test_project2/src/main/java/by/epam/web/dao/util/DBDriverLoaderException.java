package by.epam.web.dao.util;

/**
 * This class represents an exception that can be thrown on {@link MySQLDriverLoader}
 * */

public final class DBDriverLoaderException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public DBDriverLoaderException() {
		super();
	}

	public DBDriverLoaderException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DBDriverLoaderException(String message, Throwable cause) {
		super(message, cause);
	}

	public DBDriverLoaderException(String message) {
		super(message);
	}

	public DBDriverLoaderException(Throwable cause) {
		super(cause);
	}
}