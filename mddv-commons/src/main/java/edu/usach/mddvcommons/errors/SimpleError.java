package edu.usach.mddvcommons.errors;

public class SimpleError {

	private String errorMessage;
	private String errorCode;

	public SimpleError(ExceptionMessageCode errorMessage) {
		this.errorMessage = errorMessage.getMessage();
		this.errorCode = errorMessage.getException();
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}
