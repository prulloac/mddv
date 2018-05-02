package edu.usach.apimain.controller;

import edu.usach.apicommons.controller.EntityController;
import edu.usach.apicommons.errorhandling.ApiException;
import edu.usach.apicommons.errorhandling.ErrorDTO;
import edu.usach.apicommons.service.IEntityService;
import edu.usach.apimain.model.Repository;
import edu.usach.apimain.service.IRepositoryService;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static edu.usach.apicommons.util.Constants.OBJECT;

@CrossOrigin(maxAge = 7200)
@RestController
@RequestMapping("/repositories")
@SuppressWarnings("unchecked")
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
      logger.error(e.getMessage(), e);
      return responseNotFound(OBJECT, new ErrorDTO(e, httpServletRequest));
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
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
      logger.error(e.getMessage(), e);
      return responseNotFound(OBJECT, new ErrorDTO(e, httpServletRequest));
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      return responseInternalServerError(OBJECT, new ErrorDTO(httpServletRequest));
    }
  }

  @Override
  @RequestMapping(method = RequestMethod.DELETE, params = {"id"})
  public ResponseEntity<Object> deleteById(@RequestParam("id") long id) {
    try {
      getService().deleteById(id);
      JSONObject data = new JSONObject();
      data.put("success", true);
      data.put("message", "Repository successfully deleted");
      return response(data);
    } catch (ApiException e) {
      logger.error(e.getMessage(), e);
      return responseNotFound(OBJECT, new ErrorDTO(e, httpServletRequest));
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      return responseInternalServerError(OBJECT, new ErrorDTO(httpServletRequest));
    }
  }

}
