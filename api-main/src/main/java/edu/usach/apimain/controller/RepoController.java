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
  
  @RequestMapping(method = RequestMethod.POST, params = { "extract", "id" })
	public ResponseEntity<Object> extract(@RequestParam("id") long id, @RequestParam("extract") boolean extract) throws ApiException {
		return response(service.extractFromRepository(id));
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
	public ResponseEntity<Object> getConnectionParams(@RequestParam("id") Long id) throws ApiException {
		return response(service.getConnectionParams(id));
	}

}
