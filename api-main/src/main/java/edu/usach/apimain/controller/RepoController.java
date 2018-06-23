package edu.usach.apimain.controller;

import edu.usach.apicommons.controller.EntityController;
import edu.usach.apicommons.errorhandling.ApiException;
import edu.usach.apicommons.errorhandling.ErrorCode;
import edu.usach.apicommons.errorhandling.ErrorDTO;
import edu.usach.apicommons.service.IEntityService;
import edu.usach.apimain.model.Repository;
import edu.usach.apimain.service.IRepositoryService;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static edu.usach.apicommons.util.Constants.OBJECT;

@CrossOrigin(maxAge = 7200)
@RestController
@RequestMapping("/repositories")
@SuppressWarnings("unchecked")
@Slf4j
public class RepoController extends EntityController<Repository> {

	@Autowired
	private IRepositoryService service;

	@Override
	protected IEntityService<Repository> getService() {
		return service;
  }
  
  @RequestMapping(value = "extract", method = RequestMethod.POST, params = { "id" })
	public ResponseEntity<Object> extract(@RequestParam("id") long id) {
		String token = this.servletRequest.getHeader("Authorization");
		return response(service.extractFromRepository(id, token));
	}

	@RequestMapping(method = RequestMethod.GET, value = "/extractables")
	public ResponseEntity<Object> extractables() {
		return response(service.extractables());
	}

	@RequestMapping(method = RequestMethod.GET, value = "/extractableTypes")
	public ResponseEntity<Object> extractableTypes() {
		return response(service.extractableTypes());
	}

	@RequestMapping(method = RequestMethod.GET, value = "/connectionParams")
	public ResponseEntity<Object> getConnectionParams(@RequestParam("id") Long id) {
		String token = this.servletRequest.getHeader("Authorization");
		return response(service.getConnectionParams(id, token));
	}

	@RequestMapping(method = RequestMethod.POST, value = "/connectionParams")
	public ResponseEntity<Object> putConnectionParams(@RequestParam("id") Long id, @RequestBody Map<String, Object> params) {
		if (!this.isAuthenticated()) {
			return this.responseApiException(new ApiException(ErrorCode.UNAUTHORIZED), HttpStatus.UNAUTHORIZED);
		}
		return response(service.putConnectionParams(id, params));
	}

	@Override
	@RequestMapping(
			method = {RequestMethod.GET},
			name = "Get all"
	)
	public ResponseEntity<Object> getAll(@RequestParam(value = "page",required = false) Integer page, @RequestParam(value = "size",required = false) Integer size, @RequestParam(value = "showAll",required = false) String showAll) {
		if (!this.isAuthenticated()) {
			return this.responseApiException(new ApiException(ErrorCode.UNAUTHORIZED), HttpStatus.UNAUTHORIZED);
		} else {
			try {
				return null != showAll && showAll.matches("(?i)^(yes|true|ok|1|show|showall)$") ?
						this.response(service.findAllDTO()) :
						this.response(service.findPaginatedDTO(page, size));
			} catch (ApiException var5) {
				log.error(var5.getMessage(), var5);
				return this.responseApiException(var5, HttpStatus.NOT_FOUND);
			} catch (Exception var6) {
				log.error(var6.getMessage(), var6);
				return this.responseException(var6, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}

	@Override
	@RequestMapping(
			method = {RequestMethod.GET},
			value = {"/{id}"}
	)
	public ResponseEntity<Object> getById(@PathVariable Long id, @RequestParam(value = "show",required = false) String filterString) {
		if (!this.isAuthenticated()) {
			return this.responseApiException(new ApiException(ErrorCode.UNAUTHORIZED), HttpStatus.UNAUTHORIZED);
		} else {
			try {
				return response(service.findOneDTO(id));
			} catch (ApiException var4) {
				log.error(var4.getMessage(), var4);
				return this.responseApiException(var4, HttpStatus.NOT_FOUND);
			} catch (Exception var5) {
				log.error(var5.getMessage(), var5);
				return this.responseException(var5, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/testConnection")
	public ResponseEntity<Object> testConnection(@RequestParam("id") Long id) {
		if (!this.isAuthenticated()) {
			return this.responseApiException(new ApiException(ErrorCode.UNAUTHORIZED), HttpStatus.UNAUTHORIZED);
		} else {
			try {
				String token = this.servletRequest.getHeader("Authorization");
				return response(service.testConnection(id, token));
			} catch (ApiException e) {
				log.error(e.getMessage(), e);
				return this.responseApiException(e, HttpStatus.BAD_REQUEST);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				return this.responseException(e, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}

}
