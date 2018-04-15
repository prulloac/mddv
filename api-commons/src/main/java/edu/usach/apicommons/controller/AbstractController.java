package edu.usach.apicommons.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public abstract class AbstractController {
	protected final Logger logger = LogManager.getLogger(getClass());

	@Autowired
	protected HttpServletRequest httpServletRequest;

	protected JSONObject responseObject(Object data, Object error) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("data", data);
		jsonObject.put("error", error);
		jsonObject.put("timestamp", ZonedDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
		return jsonObject;
	}

	protected ResponseEntity response(Object data) {
		return new ResponseEntity<>(responseObject(data, null), HttpStatus.OK);
	}

	protected ResponseEntity responseCreated(Object data) {
		return new ResponseEntity<>(responseObject(data, null), HttpStatus.CREATED);
	}

	protected ResponseEntity responseNotFound(Object data, Object error) {
		return new ResponseEntity<>(responseObject(data, error), HttpStatus.NOT_FOUND);
	}

	protected ResponseEntity responseUnauthorized(Object data, Object error) {
		return new ResponseEntity<>(responseObject(data, error), HttpStatus.UNAUTHORIZED);
	}

	protected ResponseEntity responseInternalServerError(Object data, Object error) {
		return new ResponseEntity<>(responseObject(data, error), HttpStatus.NOT_FOUND);
	}

}
