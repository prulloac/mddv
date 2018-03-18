package edu.usach.mddvcommons.errors;

import java.util.HashMap;
import java.util.Map;

public class NotFoundError extends SimpleError{

	private String identifier;
	private Map<String, Object> params;

	public NotFoundError(ExceptionMessageCode errorMessage) {
		super(errorMessage);
		this.identifier = "undefined";
		this.params = new HashMap<>();
	}

	public NotFoundError(ExceptionMessageCode errorMessage, String identifier) {
		super(errorMessage);
		this.identifier = identifier;
		this.params = new HashMap<>();
	}

	public NotFoundError(ExceptionMessageCode errorMessage, String identifier, Map<String, Object> params) {
		super(errorMessage);
		this.identifier = identifier;
		this.params = params;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	public void addParams(String key, Object value) {
		this.params.put(key, value);
	}
}
