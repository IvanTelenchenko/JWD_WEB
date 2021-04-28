package by.epam.web.service.exception;

import by.epam.web.service.ServiceException;

/**
 * This class represents an exception in the Service classes. 
 * */
public class ImpossibleExecuteException extends ServiceException {

	private static final long serialVersionUID = 1L;

	public ImpossibleExecuteException() {
		super();
	}

	public ImpossibleExecuteException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ImpossibleExecuteException(String message, Throwable cause) {
		super(message, cause);
	}

	public ImpossibleExecuteException(String message) {
		super(message);
	}

	public ImpossibleExecuteException(Throwable cause) {
		super(cause);
	}
}
