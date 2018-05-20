package edu.usach.apicommons.controller;

import edu.usach.apicommons.errorhandling.ApiException;
import edu.usach.apicommons.errorhandling.ErrorDTO;
import edu.usach.apicommons.model.IEntity;
import edu.usach.apicommons.model.ISecureEntity;
import edu.usach.apicommons.service.IEntityService;
import edu.usach.apicommons.util.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.ParameterizedType;

import static edu.usach.apicommons.util.Constants.ARRAY;
import static edu.usach.apicommons.util.Constants.OBJECT;
import static edu.usach.apicommons.util.SecurityUtils.HEADER_STRING;

@SuppressWarnings("unchecked")
@Slf4j
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
	public ResponseEntity<Object> getById(@PathVariable Long id, @RequestParam(value = "show", required = false) String filterString) {
		try {
			if (null == filterString)
				return response(getService().findOne(id));
			return response(getService().findAndFilter(id, filterString));
		} catch (ApiException e) {
			log.error(e.getMessage(), e);
			return responseNotFound(OBJECT, new ErrorDTO(e, httpServletRequest));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return responseInternalServerError(OBJECT, new ErrorDTO(httpServletRequest));
		}
	}

	@Override
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Object> getAll(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size,
			@RequestParam(value = "showAll", required = false) String showAll) {
		try {
			if (null!=showAll && showAll.matches("(?i)^(yes|true|ok|1|show|showall)$"))
				return response(getService().findAll());
			return response(getService().findPaginated(page, size));
		} catch (ApiException e) {
			log.error(e.getMessage(), e);
			return responseNotFound(ARRAY, new ErrorDTO(e, httpServletRequest));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return responseInternalServerError(ARRAY, new ErrorDTO(httpServletRequest));
		}
	}

	@Override
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> create(@RequestBody T entity) {
		try {
			getService().create(entity);
			JSONObject data = new JSONObject();
			data.put("success", true);
			data.put("message", tClass.getName() + " successfully created");
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
	public ResponseEntity<Object> update(@RequestBody T entity) {
		try {
			getService().update(entity);
			JSONObject data = new JSONObject();
			data.put("success", true);
			data.put("message", tClass.getName() + " successfully updated");
			return response(data);
		} catch (ApiException e) {
			log.error(e.getMessage(), e);
			return responseNotFound(OBJECT, new ErrorDTO(e, httpServletRequest));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return responseInternalServerError(OBJECT, new ErrorDTO(httpServletRequest));
		}
	}

	@Override
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@RequestBody(required = false) T entity, @RequestParam(value = "id", required = false) Long id) {
		try {
			if (null!=id && null==entity) {
				getService().deleteById(id);
			}else if (null!=entity && null==id) {
				getService().delete(entity);
			} else {
				return responseBadRequest(OBJECT, new ErrorDTO(httpServletRequest));
			}
			JSONObject data = new JSONObject();
			data.put("success", true);
			data.put("message", tClass.getName() + " successfully deleted");
			return response(data);
		} catch (ApiException e) {
			log.error(e.getMessage(), e);
			return responseNotFound(OBJECT, new ErrorDTO(e, httpServletRequest));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return responseInternalServerError(OBJECT, new ErrorDTO(httpServletRequest));
		}
	}

}
