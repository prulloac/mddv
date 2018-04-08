package edu.usach.apicommons.errorhandling;

public enum ErrorCode implements IErrorCode {
	UNEXPECTED_ERROR("An unexpected error happened"),
	OBJECT_NOT_FOUND("Object of type '{0}' not found"),
	NO_OBJECTS_FOUND("No objects of type '{0}' found"),
	INVALID_FILTERS("Invalid filters for object of type '{0}'"),
	CREATION_ERROR("Couldn't create '{0}' object"),
	UPDATE_ERROR("Error updating '{0}' object"),
	DELETE_ERROR("Couldn't delete '{0}' object"),
	UNAUTHORIZED("Unauthorized access");

	private String message;

	ErrorCode(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	};

	@Override
	public String getMessage(String detail) {
		return message.replace("'{0}'", detail);
	}

	@Override
	public Integer getCode() {
		return ordinal();
	}

	@Override
	public String getCodeName() {
		return name();
	}
}
