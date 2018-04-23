package edu.usach.apicommons.errorhandling;

import org.json.simple.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@SuppressWarnings("unchecked")
public class ErrorDTO {
	private Integer errorCode;
	private String errorCodeName;
	private String errorMessage;
	private JSONObject errorData;
	private String errorTimestamp;
	private String requestedURL;
	private String httpVerb;

	public ErrorDTO(HttpServletRequest httpServletRequest) {
		this.errorData = new JSONObject();
		this.requestedURL = httpServletRequest.getRequestURL().toString();
		this.httpVerb = httpServletRequest.getMethod();
		this.errorData.put("requestedParams", new JSONObject());
		this.errorCode = ErrorCode.UNEXPECTED_ERROR.getCode();
		this.errorCodeName = ErrorCode.UNEXPECTED_ERROR.getCodeName();
		this.errorMessage = ErrorCode.UNEXPECTED_ERROR.getMessage();
		setErrorTimestamp();
	}

	public ErrorDTO(ApiException exception, HttpServletRequest httpServletRequest) {
		this.errorData = new JSONObject();
		this.requestedURL = httpServletRequest.getRequestURL().toString();
		this.httpVerb = httpServletRequest.getMethod();
		this.errorData.put("requestedParams", new JSONObject());
		this.errorCode = exception.getErrorCode().getCode();
		this.errorCodeName = exception.getErrorCode().getCodeName();
		this.errorMessage = exception.getMessage();
		setErrorTimestamp();
	}

	public void setErrorMessageCode(ErrorCode errorCode) {
		this.errorCode = errorCode.ordinal();
		this.errorMessage = errorCode.toString();
	}


	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorCodeName() {
		return errorCodeName;
	}

	public void setErrorCodeName(String errorCodeName) {
		this.errorCodeName = errorCodeName;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public JSONObject getErrorData() {
		return errorData;
	}

	public void setErrorData(JSONObject errorData) {
		this.errorData = errorData;
	}

	public String getErrorTimestamp() {
		return errorTimestamp;
	}

	private void setErrorTimestamp() {
		this.errorTimestamp = ZonedDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
	}

	public void setErrorTimestamp(String errorTimestamp) {
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
