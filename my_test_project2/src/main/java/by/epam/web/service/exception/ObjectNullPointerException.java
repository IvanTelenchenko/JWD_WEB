package by.epam.web.service.exception;

import by.epam.web.service.ServiceException;

public class ObjectNullPointerException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ObjectNullPointerException() {
		super();
	}

	public ObjectNullPointerException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ObjectNullPointerException(String message, Throwable cause) {
		super(message, cause);
	}

	public ObjectNullPointerException(String message) {
		super(message);
	}

	public ObjectNullPointerException(Throwable cause) {
		super(cause);
	}
}
