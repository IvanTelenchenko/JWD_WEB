package by.epam.web.service.exception;

import by.epam.web.service.ServiceException;

/**
 * This class represents an exception in the Service classes. 
 * */
public class DuplicateEmailException extends ServiceException {

	private static final long serialVersionUID = 1L;

	public DuplicateEmailException() {
		super();
	}

	public DuplicateEmailException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DuplicateEmailException(String message, Throwable cause) {
		super(message, cause);
	}

	public DuplicateEmailException(String message) {
		super(message);
	}

	public DuplicateEmailException(Throwable cause) {
		super(cause);
	}
}
