package edu.usach.apimain.controller;

import edu.usach.apicommons.controller.EntityController;
import edu.usach.apicommons.errorhandling.ApiException;
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
  
  @Override
  @RequestMapping(method = RequestMethod.POST)
  public ResponseEntity<Object> create(@RequestBody Repository entity) {
    try {
      getService().create(entity);
      JSONObject data = new JSONObject();
      data.put("success", true);
      data.put("message", "Repository successfully created");
      return responseCreated(data);
    } catch (ApiException e) {
      log.error(e.getMessage(), e);
      return responseNotFound(OBJECT, new ErrorDTO(e, httpServletRequest));
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return responseInternalServerError(OBJECT, new ErrorDTO(httpServletRequest));
    }
  }

  @Override
  @RequestMapping(method = RequestMethod.PUT)
  public ResponseEntity<Object> update(@RequestBody Repository entity) {
    try {
      getService().update(entity);
      JSONObject data = new JSONObject();
      data.put("success", true);
      data.put("message", "Repository successfully updated");
      return response(data);
    } catch (ApiException e) {
      log.error(e.getMessage(), e);
      return responseNotFound(OBJECT, new ErrorDTO(e, httpServletRequest));
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return responseInternalServerError(OBJECT, new ErrorDTO(httpServletRequest));
    }
  }

  @RequestMapping(method = RequestMethod.POST, params = { "extract", "id" })
	public ResponseEntity<Object> extract(@RequestParam("id") long id, @RequestParam("extract") boolean extract) {
		try {
			if (extract) {
				return new ResponseEntity<Object>(service.extractFromRepository(id), HttpStatus.OK);
			}
			return responseUnauthorized(OBJECT, new ErrorDTO(httpServletRequest));
		} catch (ApiException e) {
			log.error(e.getMessage(), e);
			return responseNotFound(OBJECT, new ErrorDTO(e, httpServletRequest));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return responseInternalServerError(OBJECT, new ErrorDTO(httpServletRequest));
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/extractables")
	public ResponseEntity<Object> extractables() {
		try {
			return response(service.extractables());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return responseInternalServerError(OBJECT, new ErrorDTO(httpServletRequest));
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/extractableTypes")
	public ResponseEntity<Object> extractableTypes() {
		try {
			return response(service.extractableTypes());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return responseInternalServerError(OBJECT, new ErrorDTO(httpServletRequest));
		}
	}

}
