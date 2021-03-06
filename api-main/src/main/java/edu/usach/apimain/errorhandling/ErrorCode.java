package edu.usach.apimain.errorhandling;

import edu.usach.apicommons.errorhandling.IErrorCode;

public enum ErrorCode implements IErrorCode {
	INVALID_REQUEST("Invalid API request"),
	UNAUTHORIZED("Unable to access: unauthorized request"),
	NO_USERS_FOUND("No users found in database"),
	ERROR_FINDING_USER("Error finding a user with the given criteria"),
	ERROR_CREATING_USER("Error creating user"),
	INVALID_CREDENTIALS("Invalid credentials"),
	ERROR_CONNECTING_EXTRACTOR("Error connecting with extractor");

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
