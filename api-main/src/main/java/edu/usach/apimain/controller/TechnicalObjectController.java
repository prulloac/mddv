package edu.usach.apimain.controller;

import edu.usach.apicommons.controller.EntityController;
import edu.usach.apicommons.errorhandling.ApiException;
import edu.usach.apicommons.errorhandling.ErrorCode;
import edu.usach.apicommons.service.IEntityService;
import edu.usach.apicommons.util.TechnicalTypes;
import edu.usach.apimain.model.TechnicalObject;
import edu.usach.apimain.service.ITechnicalObjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin(maxAge = 7200)
@RestController
@RequestMapping("/technical-objects")
@Slf4j
public class TechnicalObjectController extends EntityController<TechnicalObject> {

	@Autowired
	private ITechnicalObjectService service;

	@Override
	protected IEntityService<TechnicalObject> getService() {
		return service;
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public ResponseEntity<Object> create(@RequestBody TechnicalObject entity, @RequestParam(value = "parent", required = true) Long parent) {
		if (!this.isAuthenticated()) {
			return this.responseApiException(new ApiException(ErrorCode.UNAUTHORIZED), HttpStatus.UNAUTHORIZED);
		} else {
			try {
				String token = this.servletRequest.getHeader("Authorization");
				return response(service.create(entity, parent));
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				return this.responseException(e, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}

	@RequestMapping(value = "/types", method = RequestMethod.GET)
	public ResponseEntity<Object> getTypes(
	        @RequestParam(value = "parentId", required = false) Long parentId) {
		return response(this.service.getTechnicalObjectTypes(parentId));
	}

	@RequestMapping(value = "/repositories", method = RequestMethod.GET)
	public ResponseEntity<Object> getRepositories() {
		if (!this.isAuthenticated()) {
			return this.responseApiException(new ApiException(ErrorCode.UNAUTHORIZED), HttpStatus.UNAUTHORIZED);
		} else {
			try {
				String token = this.servletRequest.getHeader("Authorization");
				return response(service.getRepositories(token));
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				return this.responseException(e, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}

	@RequestMapping(value = "/children", method = RequestMethod.GET)
	public ResponseEntity<Object> getChildrenObjects(@RequestParam("parentId") Long id) {
		if (!this.isAuthenticated()) {
			return this.responseApiException(new ApiException(ErrorCode.UNAUTHORIZED), HttpStatus.UNAUTHORIZED);
		} else {
			try {
				String token = this.servletRequest.getHeader("Authorization");
				return response(service.getChildrenObjects(id, token));
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				return this.responseException(e, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}

	@RequestMapping(value = "/repository", method = RequestMethod.GET)
	public ResponseEntity<Object> getFromRepository(@RequestParam("id") Long id) {
		if (!this.isAuthenticated()) {
			return this.responseApiException(new ApiException(ErrorCode.UNAUTHORIZED), HttpStatus.UNAUTHORIZED);
		} else {
			try {
				return response(service.getObjectsFromRepository(id));
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				return this.responseException(e, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}
}
