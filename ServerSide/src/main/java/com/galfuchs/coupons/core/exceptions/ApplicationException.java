package com.galfuchs.coupons.core.exceptions;

import com.galfuchs.coupons.core.enums.ErrorType;

@SuppressWarnings("serial")
public class ApplicationException extends Exception {

	private ErrorType errorType;

	// This contructor is used when you wrap a 3rd party exception
	public ApplicationException(Throwable e, String message, ErrorType errorType) {
		super(message, e);
		this.errorType = errorType;
	}

	// This constructor is used when your code throws the exception
	public ApplicationException(ErrorType errorType) {
		super(errorType.getInternalMessage());
		this.errorType = errorType;
	}
	
	// This constructor is used for general application errors
	public ApplicationException(ErrorType errorType, String message) {
		super(errorType + ": " + message);
		this.errorType = errorType;
	}

	public ErrorType getErrorType() {
		return errorType;
	}

}
