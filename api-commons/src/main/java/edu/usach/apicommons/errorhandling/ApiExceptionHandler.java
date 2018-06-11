package edu.usach.apicommons.errorhandling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static edu.usach.apicommons.errorhandling.ErrorCode.NO_OBJECTS_FOUND;
import static edu.usach.apicommons.errorhandling.ErrorCode.UNAUTHORIZED;

@ControllerAdvice
@Slf4j
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ ApiException.class })
	public ResponseEntity<Object> handleApiException(ApiException ex, WebRequest req) {
		log.error(ex.getMessage(), ex);
		if (UNAUTHORIZED.equals(ex.getErrorCode()))
			return handleExceptionInternal(ex, UNAUTHORIZED.getMessage(), new HttpHeaders(), HttpStatus.UNAUTHORIZED, req);
		if (NO_OBJECTS_FOUND.equals(ex.getErrorCode()) || ErrorCode.OBJECT_NOT_FOUND.equals(ex.getErrorCode()))
			return handleExceptionInternal(ex, ex.getErrorCode().getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, req);
		return handleExceptionInternal(ex, "Internal Error", new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, req);
	}

}
