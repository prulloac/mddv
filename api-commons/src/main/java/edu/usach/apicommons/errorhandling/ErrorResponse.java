package edu.usach.apicommons.errorhandling;

import javax.servlet.http.HttpServletRequest;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

public class ErrorResponse {
	private Integer status;
	private Integer errorCode;
	private String errorMessage;
	private Map<String, Map<String, Object>> errorData;
	private ZonedDateTime errorTimestamp;
	private String requestedURL;
	private String httpVerb;

	public ErrorResponse(HttpServletRequest httpServletRequest) {
		this.errorData = new HashMap<>();
		this.requestedURL = httpServletRequest.getRequestURL().toString();
		this.httpVerb = httpServletRequest.getMethod();
		this.errorData.put("requestedParams", new HashMap<>());
		setErrorTimestamp();
	}

	public ErrorResponse(ApiException exception, HttpServletRequest httpServletRequest) {
		this.errorData = new HashMap<>();
		this.requestedURL = httpServletRequest.getRequestURL().toString();
		this.httpVerb = httpServletRequest.getMethod();
		this.errorData.put("requestedParams", new HashMap<>());
		this.errorCode = exception.getErrorCode().getCode();
		this.errorMessage = exception.getMessage();
		setErrorTimestamp();
	}

	public void setErrorMessageCode(ErrorCode errorCode) {
		this.errorCode = errorCode.ordinal();
		this.errorMessage = errorCode.toString();
	}


	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Map<String, Map<String, Object>> getErrorData() {
		return errorData;
	}

	public void setErrorData(Map<String, Map<String, Object>> errorData) {
		this.errorData = errorData;
	}

	public ZonedDateTime getErrorTimestamp() {
		return errorTimestamp;
	}

	private void setErrorTimestamp() {
		this.errorTimestamp = ZonedDateTime.now();
	}

	public void setErrorTimestamp(ZonedDateTime errorTimestamp) {
		this.errorTimestamp = errorTimestamp;
	}

	public String getRequestedURL() {
		return requestedURL;
	}

	public void setRequestedURL(String requestedURL) {
		this.requestedURL = requestedURL;
	}

	public String getHttpVerb() {
		return httpVerb;
	}

	public void setHttpVerb(String httpVerb) {
		this.httpVerb = httpVerb;
	}
}
