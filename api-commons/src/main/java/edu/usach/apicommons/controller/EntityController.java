package edu.usach.apicommons.controller;

import edu.usach.apicommons.errorhandling.ApiException;
import edu.usach.apicommons.errorhandling.ErrorCode;
import edu.usach.apicommons.errorhandling.ErrorDTO;
import edu.usach.apicommons.model.IEntity;
import edu.usach.apicommons.model.ISecureEntity;
import edu.usach.apicommons.service.IEntityService;
import edu.usach.apicommons.util.SecurityUtils;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.ParameterizedType;

import static edu.usach.apicommons.util.Constants.ARRAY;
import static edu.usach.apicommons.util.Constants.OBJECT;
import static edu.usach.apicommons.util.SecurityUtils.HEADER_STRING;

@SuppressWarnings("unchecked")
public abstract class EntityController<T extends IEntity> extends AbstractController implements IEntityController<T> {

	private Class<T> tClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

	protected boolean isAuthenticated() {
		return SecurityUtils.isAuthenticated(httpServletRequest.getHeader(HEADER_STRING));
	}

	protected boolean isAuthorized(String... roles) {
		return SecurityUtils.isAuthorized(httpServletRequest.getHeader(HEADER_STRING), roles);
	}

	protected boolean hasAccess(ISecureEntity entity) {
		return SecurityUtils.hasAccess(httpServletRequest.getHeader(HEADER_STRING), entity);
	}

	protected abstract IEntityService<T> getService();

	@Override
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<Object> getById(@PathVariable Long id) {
		try {
			return response(getService().findOne(id));
		} catch (ApiException e) {
			logger.error(e.getMessage(), e);
			return responseNotFound(OBJECT, new ErrorDTO(e, httpServletRequest));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return responseInternalServerError(OBJECT, new ErrorDTO(httpServletRequest));
		}
	}

	@Override
	@RequestMapping(method = RequestMethod.GET, value = "/{id}", params = { "show" })
	public ResponseEntity<Object> getByIdAndFilterOutput(@PathVariable Long id, @RequestParam("show") String filterString){
		try {
			return response(getService().findAndFilter(id, filterString));
		} catch (ApiException e) {
			logger.error(e.getMessage(), e);
			return responseNotFound(OBJECT, new ErrorDTO(e, httpServletRequest));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return responseInternalServerError(OBJECT, new ErrorDTO(httpServletRequest));
		}
	}

	@Override
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Object> getAll() {
		try {
			return response(getService().findAll());
		} catch (ApiException e) {
			logger.error(e.getMessage(), e);
			return responseNotFound(ARRAY, new ErrorDTO(e, httpServletRequest));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return responseInternalServerError(ARRAY, new ErrorDTO(httpServletRequest));
		}
	}

	@Override
	@RequestMapping(method = RequestMethod.GET, params = { "page", "size" })
	public ResponseEntity<Object> getAllPaginated(@RequestParam("page") int page, @RequestParam("size") int size) {
		try {
			return response(getService().findPaginated(page, size));
		} catch (ApiException e) {
			logger.error(e.getMessage(), e);
			return responseNotFound(ARRAY, new ErrorDTO(e, httpServletRequest));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return responseInternalServerError(ARRAY, new ErrorDTO(httpServletRequest));
		}
	}

	@Override
	@RequestMapping(method = RequestMethod.GET, params = { "page" })
	public ResponseEntity<Object> getAllPaginated(@RequestParam("page") int page) {
		try {
			return response(getService().findPaginated(page, 10));
		} catch (ApiException e) {
			logger.error(e.getMessage(), e);
			return responseNotFound(ARRAY, new ErrorDTO(e, httpServletRequest));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return responseInternalServerError(ARRAY, new ErrorDTO(httpServletRequest));
		}
	}


	@Override
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> create(@RequestBody T entity) {
		try {
			if (!isAuthorized("sadmin", "admin"))
				return responseUnauthorized(OBJECT, new ErrorDTO(new ApiException(ErrorCode.UNAUTHORIZED), httpServletRequest));
			getService().create(entity);
			JSONObject data = new JSONObject();
			data.put("success", true);
			data.put("message", tClass.getName() + " successfully created");
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
	public ResponseEntity<Object> update(@RequestBody T entity) {
		try {
			if (!isAuthorized("sadmin", "admin"))
				return responseUnauthorized(OBJECT, new ErrorDTO(new ApiException(ErrorCode.UNAUTHORIZED), httpServletRequest));
			getService().update(entity);
			JSONObject data = new JSONObject();
			data.put("success", true);
			data.put("message", tClass.getName() + " successfully updated");
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
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@RequestBody T entity) {
		try {
			if (!isAuthorized("sadmin", "admin"))
				return responseUnauthorized(OBJECT, new ErrorDTO(new ApiException(ErrorCode.UNAUTHORIZED), httpServletRequest));
			getService().delete(entity);
			JSONObject data = new JSONObject();
			data.put("success", true);
			data.put("message", tClass.getName() + " successfully deleted");
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
	@RequestMapping(method = RequestMethod.DELETE, params = { "id"} )
	public ResponseEntity<Object> deleteById(@RequestParam("id") long id) {
		try {
			if (!isAuthorized("sadmin", "admin"))
				return responseUnauthorized(OBJECT, new ErrorDTO(new ApiException(ErrorCode.UNAUTHORIZED), httpServletRequest));
			getService().deleteById(id);
			JSONObject data = new JSONObject();
			data.put("success", true);
			data.put("message", tClass.getName() + " successfully deleted");
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
