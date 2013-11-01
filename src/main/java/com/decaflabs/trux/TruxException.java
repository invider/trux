package com.decaflabs.trux;

public class TruxException extends RuntimeException {

	public TruxException() {
	}

	public TruxException(String message) {
		super(message);
	}

	public TruxException(Throwable cause) {
		super(cause);
	}

	public TruxException(String message, Throwable cause) {
		super(message, cause);
	}

	public TruxException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
