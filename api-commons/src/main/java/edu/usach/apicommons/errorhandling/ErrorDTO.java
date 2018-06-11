package edu.usach.apicommons.errorhandling;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.json.simple.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@SuppressWarnings("unchecked")
@Getter
@Setter
@ToString
public class ErrorDTO {
	private Integer errorCode;
	private String errorCodeName;
	private String errorMessage;
	private JSONObject errorData;
	private String errorTimestamp = ZonedDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
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
	}

	public ErrorDTO(ApiException exception, HttpServletRequest httpServletRequest) {
		this.errorData = new JSONObject();
		this.requestedURL = httpServletRequest.getRequestURL().toString();
		this.httpVerb = httpServletRequest.getMethod();
		this.errorData.put("requestedParams", new JSONObject());
		this.errorCode = exception.getErrorCode().getCode();
		this.errorCodeName = exception.getErrorCode().getCodeName();
		this.errorMessage = exception.getMessage();
	}

	public void setErrorMessageCode(ErrorCode errorCode) {
		this.errorCode = errorCode.ordinal();
		this.errorMessage = errorCode.toString();
	}
}
