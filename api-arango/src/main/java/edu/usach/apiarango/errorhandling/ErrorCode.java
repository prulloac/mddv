package edu.usach.apiarango.errorhandling;

import edu.usach.apicommons.errorhandling.IErrorCode;

public enum ErrorCode implements IErrorCode {
	CONNECTION_ERROR("Unable to connect to repository"),
	NOT_CONNECTED("Not connected to repository, must connect first");

	String message;

	ErrorCode(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public String getMessage(String detail) {
		return message.replace("'{0}'", detail);
	}

	public Integer getCode() {
		return ordinal();
	}

	public String getCodeName() {
		return name();
	}

}
