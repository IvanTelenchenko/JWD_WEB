package by.epam.web.service.exception;

import by.epam.web.service.ServiceException;

public class NotValidDataException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotValidDataException() {
		super();
	}

	public NotValidDataException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NotValidDataException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotValidDataException(String message) {
		super(message);
	}

	public NotValidDataException(Throwable cause) {
		super(cause);
	}
}
