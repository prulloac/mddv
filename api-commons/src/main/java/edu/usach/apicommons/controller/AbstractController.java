package edu.usach.apicommons.controller;

import edu.usach.apicommons.model.ISecureEntity;
import edu.usach.apicommons.util.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static edu.usach.apicommons.util.SecurityUtils.HEADER_STRING;

@SuppressWarnings("unchecked")
@Slf4j
@Controller
public abstract class AbstractController {

	@Autowired
	protected HttpServletRequest httpServletRequest;

	protected boolean isAuthenticated() {
		return SecurityUtils.isAuthenticated(httpServletRequest.getHeader(HEADER_STRING));
	}

	protected boolean isAuthorized(String... roles) {
		return SecurityUtils.isAuthorized(httpServletRequest.getHeader(HEADER_STRING), roles);
	}

	protected boolean hasAccess(ISecureEntity entity) {
		return SecurityUtils.hasAccess(httpServletRequest.getHeader(HEADER_STRING), entity);
	}

	protected JSONObject responseObject(Object data) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("data", data);
		jsonObject.put("timestamp", ZonedDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
		return jsonObject;
	}

	protected ResponseEntity<Object> response(Object data) {
		return response(data, HttpStatus.OK);
	}

	protected ResponseEntity<Object> response(Object data, HttpStatus status) {
		return new ResponseEntity<>(responseObject(data), status);
	}

}
