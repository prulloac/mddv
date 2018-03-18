package edu.usach.mddvcommons.errors;

public class SimpleError {

	private String errorMessage;
	private String errorCode;
	private String requestedURL;

	public SimpleError(ExceptionMessageCode exception, String requestedURL) {
		this.errorMessage = exception.getMessage();
		this.errorCode = exception.getExceptionCode();
		this.requestedURL = requestedURL;
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

	public String getRequestedURL() {
		return requestedURL;
	}

	public void setRequestedURL(String requestedURL) {
		this.requestedURL = requestedURL;
	}
}
