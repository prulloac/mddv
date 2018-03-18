package edu.usach.mddvcommons.errors;

public class CustomException extends Exception {

	private String exception;

	public CustomException(ExceptionMessageCode exceptionMessageCode) {
		super(exceptionMessageCode.getMessage());
		this.exception = exceptionMessageCode.getException();
	}

}
