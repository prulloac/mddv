package edu.usach.apicommons.controller;

import edu.usach.apicommons.errorhandling.ErrorDTO;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@SuppressWarnings("unchecked")
@Slf4j
public abstract class AbstractController {

	@Autowired
	protected HttpServletRequest httpServletRequest;

	protected JSONObject responseObject(Object data, Object error) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("data", data);
		jsonObject.put("error", error);
		jsonObject.put("timestamp", ZonedDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
		return jsonObject;
	}

	protected ResponseEntity<Object> response(Object data) {
		return new ResponseEntity<>(responseObject(data, null), HttpStatus.OK);
	}

	protected ResponseEntity<Object> responseCreated(Object data) {
		return new ResponseEntity<>(responseObject(data, null), HttpStatus.CREATED);
	}

	protected ResponseEntity<Object> responseNotFound(Object data, Object error) {
		return new ResponseEntity<>(responseObject(data, error), HttpStatus.NOT_FOUND);
	}

	protected ResponseEntity<Object> responseUnauthorized(Object data, Object error) {
		return new ResponseEntity<>(responseObject(data, error), HttpStatus.UNAUTHORIZED);
	}

	protected ResponseEntity<Object> responseInternalServerError(Object data, Object error) {
		return new ResponseEntity<>(responseObject(data, error), HttpStatus.NOT_FOUND);
	}

	protected ResponseEntity<Object> responseBadRequest(Object data, ErrorDTO error) {
		return new ResponseEntity<>(responseObject(data, error), HttpStatus.BAD_REQUEST);
	}

}
