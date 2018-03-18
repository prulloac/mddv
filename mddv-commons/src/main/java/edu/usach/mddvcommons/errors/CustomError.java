package edu.usach.mddvcommons.errors;

import java.util.HashMap;
import java.util.Map;

public class CustomError extends SimpleError{

	private Map<String, Object> params;

	public CustomError(ExceptionMessageCode errorMessage, String requestedURL) {
		super(errorMessage, requestedURL);
		this.params = new HashMap<>();
	}

	public CustomError(ExceptionMessageCode errorMessage, String requestedURL, Map<String, Object> params) {
		super(errorMessage, requestedURL);
		this.params = params;
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
