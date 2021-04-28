package by.epam.web.service.exception;

import by.epam.web.service.ServiceException;

/**
 * This class represents an exception in the Service classes. 
 * */
public class OldPasswordNotEqual extends ServiceException {

	private static final long serialVersionUID = 1L;

	public OldPasswordNotEqual() {
		super();
	}

	public OldPasswordNotEqual(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public OldPasswordNotEqual(String message, Throwable cause) {
		super(message, cause);
	}

	public OldPasswordNotEqual(String message) {
		super(message);
	}

	public OldPasswordNotEqual(Throwable cause) {
		super(cause);
	}
}
