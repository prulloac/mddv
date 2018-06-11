package edu.usach.apicommons.controller;

import edu.usach.apicommons.errorhandling.ApiException;
import edu.usach.apicommons.errorhandling.ErrorCode;
import edu.usach.apicommons.model.IEntity;
import edu.usach.apicommons.service.IEntityService;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.ParameterizedType;

@SuppressWarnings("unchecked")
@Slf4j
@Controller
public abstract class EntityController<T extends IEntity> extends AbstractController implements IEntityController<T> {

	private Class<T> tClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

	protected abstract IEntityService<T> getService();

	@Override
	@RequestMapping(method = RequestMethod.GET, value = "/{id}", name = "Get by id")
	public ResponseEntity<Object> getById(@PathVariable Long id, @RequestParam(value = "show", required = false) String filterString) throws ApiException {
		if (!isAuthenticated())
			throw new ApiException(ErrorCode.UNAUTHORIZED);
		if (null == filterString)
			return response(getService().findOne(id));
		return response(getService().findAndFilter(id, filterString));
	}

	@Override
	@RequestMapping(method = RequestMethod.GET, name = "Get all")
	public ResponseEntity<Object> getAll(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size,
			@RequestParam(value = "showAll", required = false) String showAll) throws ApiException {
		if (!isAuthenticated())
			throw new ApiException(ErrorCode.UNAUTHORIZED);
		if (null!=showAll && showAll.matches("(?i)^(yes|true|ok|1|show|showall)$"))
			return response(getService().findAll());
		return response(getService().findPaginated(page, size));
	}

	@Override
	@RequestMapping(method = RequestMethod.POST, name = "Create")
	public ResponseEntity<Object> create(@RequestBody T entity) throws ApiException {
		if (!isAuthenticated())
			throw new ApiException(ErrorCode.UNAUTHORIZED);
		getService().create(entity);
		JSONObject data = new JSONObject();
		data.put("success", true);
		data.put("message", tClass.getName() + " successfully created");
		return response(data, HttpStatus.CREATED);
	}

	@Override
	@RequestMapping(method = RequestMethod.PUT, name = "Update")
	public ResponseEntity<Object> update(@RequestBody T entity) throws ApiException {
		if (!isAuthenticated())
			throw new ApiException(ErrorCode.UNAUTHORIZED);
		getService().update(entity);
		JSONObject data = new JSONObject();
		data.put("success", true);
		data.put("message", tClass.getName() + " successfully updated");
		return response(data);
	}

	@Override
	@RequestMapping(method = RequestMethod.DELETE, name = "Delete")
	public ResponseEntity<Object> delete(@RequestBody(required = false) T entity, @RequestParam(value = "id", required = false) Long id) throws ApiException {
		if (!isAuthenticated())
			throw new ApiException(ErrorCode.UNAUTHORIZED);
		if (null!=id && null==entity) {
			getService().deleteById(id);
		}else if (null!=entity && null==id) {
			getService().delete(entity);
		} else {
			throw new ApiException(ErrorCode.BAD_REQUEST);
		}
		JSONObject data = new JSONObject();
		data.put("success", true);
		data.put("message", tClass.getName() + " successfully deleted");
		return response(data);
	}

}
