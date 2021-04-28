package by.epam.web.service.exception;

import by.epam.web.service.ServiceException;

/**
 * This class represents an exception in the Service classes. 
 * */
public class PasswordsNotEqual extends ServiceException {

	private static final long serialVersionUID = 1L;

	public PasswordsNotEqual() {
		super();
	}

	public PasswordsNotEqual(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PasswordsNotEqual(String message, Throwable cause) {
		super(message, cause);
	}

	public PasswordsNotEqual(String message) {
		super(message);
	}

	public PasswordsNotEqual(Throwable cause) {
		super(cause);
	}
}
