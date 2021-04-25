package by.epam.web.dao.util;



/**
 * This class represents an exception that can be thrown on {@link ConnectionPool}
 * */

public final class ConnectionPoolException extends RuntimeException{


	private static final long serialVersionUID = 1L;

	public ConnectionPoolException() {
		super();
	}

	public ConnectionPoolException(String message, Throwable cause) {
		super(message, cause);
	}

	public ConnectionPoolException(String message) {
		super(message);
	}

	public ConnectionPoolException(Throwable cause) {
		super(cause);
	}
}