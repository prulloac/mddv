package edu.usach.mddvcommons.errors;

import java.lang.reflect.Method;

public class CustomException extends Exception {

	private ExceptionMessageCode exception;

	public CustomException(ExceptionMessageCode exceptionMessageCode) {
		super(exceptionMessageCode.getMessage());
		this.exception = exceptionMessageCode;
	}

	public ExceptionMessageCode getException() {
		return exception;
	}

}
