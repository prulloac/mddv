package edu.usach.apicommons.errorhandling;

public final class ApiException extends Exception {

	private IErrorCode errorCode;

	public ApiException(IErrorCode errorCode) {
		super(errorCode.getMessage(""));
		this.errorCode = errorCode;
	}
	public ApiException(IErrorCode errorCode, String detail) {
		super(errorCode.getMessage(detail));
		this.errorCode = errorCode;
	}

	public ApiException(IErrorCode errorCode, Exception e) {
		super(errorCode.getMessage(""));
		this.initCause(e);
		this.errorCode = errorCode;
	}

	public ApiException(IErrorCode errorCode, String detail, Exception e) {
		super(errorCode.getMessage(detail));
		this.initCause(e);
		this.errorCode = errorCode;
	}

	public IErrorCode getErrorCode() {
		return errorCode;
	}

}
