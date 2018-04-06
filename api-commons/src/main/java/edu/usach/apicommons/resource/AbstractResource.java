package edu.usach.apicommons.resource;

import edu.usach.apicommons.errorhandling.ApiException;
import edu.usach.apicommons.errorhandling.ErrorDTO;
import edu.usach.apicommons.model.IEntity;
import edu.usach.apicommons.service.IService;
import edu.usach.apicommons.util.Constants;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.ParameterizedType;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public abstract class AbstractResource<T extends IEntity> implements IResource<T> {

	protected final Logger logger = LogManager.getLogger(getClass());

	private Class<T> tClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

	@Autowired
	protected HttpServletRequest httpServletRequest;

	protected abstract IService<T> getService();

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

	protected ResponseEntity responseNotFound(Object data, Object error) {
		return new ResponseEntity<>(responseObject(data, error), HttpStatus.NOT_FOUND);
	}

	protected ResponseEntity responseUnauthorized(Object data, Object error) {
		return new ResponseEntity<>(responseObject(data, error), HttpStatus.UNAUTHORIZED);
	}

	protected ResponseEntity responseInternalServerError(Object data, Object error) {
		return new ResponseEntity<>(responseObject(data, error), HttpStatus.NOT_FOUND);
	}

	@Override
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity getById(@PathVariable Long id) {
		try {
			return response(getService().findOne(id));
		} catch (ApiException e) {
			logger.error(e.getMessage(), e);
			return responseNotFound(Constants.OBJECT, new ErrorDTO(e, httpServletRequest));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return responseInternalServerError(Constants.OBJECT, new ErrorDTO(httpServletRequest));
		}
	}

	@Override
	@RequestMapping(method = RequestMethod.GET, value = "/{id}", params = { "show" })
	public ResponseEntity getByIdAndFilterOutput(@PathVariable Long id, @RequestParam("show") String filterString){
		try {
			return response(getService().findAndFilter(id, filterString));
		} catch (ApiException e) {
			logger.error(e.getMessage(), e);
			return responseNotFound(Constants.OBJECT, new ErrorDTO(e, httpServletRequest));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return responseInternalServerError(Constants.OBJECT, new ErrorDTO(httpServletRequest));
		}
	}

	@Override
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity getAll() {
		try {
			return response(getService().findAll());
		} catch (ApiException e) {
			logger.error(e.getMessage(), e);
			return responseNotFound(Constants.ARRAY, new ErrorDTO(e, httpServletRequest));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return responseInternalServerError(Constants.ARRAY, new ErrorDTO(httpServletRequest));
		}
	}

	@Override
	@RequestMapping(method = RequestMethod.GET, params = { "page", "size" })
	public ResponseEntity getAllPaginated(@RequestParam("page") int page, @RequestParam("size") int size) {
		try {
			return response(getService().findPaginated(page, size));
		} catch (ApiException e) {
			logger.error(e.getMessage(), e);
			return responseNotFound(Constants.ARRAY, new ErrorDTO(e, httpServletRequest));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return responseInternalServerError(Constants.ARRAY, new ErrorDTO(httpServletRequest));
		}
	}

	@Override
	@RequestMapping(method = RequestMethod.GET, params = { "page" })
	public ResponseEntity getAllPaginated(@RequestParam("page") int page) {
		try {
			return response(getService().findPaginated(page, 10));
		} catch (ApiException e) {
			logger.error(e.getMessage(), e);
			return responseNotFound(Constants.ARRAY, new ErrorDTO(e, httpServletRequest));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return responseInternalServerError(Constants.ARRAY, new ErrorDTO(httpServletRequest));
		}
	}


	@Override
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity create(@RequestBody T entity) {
		try {
			getService().create(entity);
			JSONObject data = new JSONObject();
			data.put("success", true);
			data.put("message", tClass.getName() + " successfully created");
			return response(data);
		} catch (ApiException e) {
			logger.error(e.getMessage(), e);
			return responseNotFound(Constants.OBJECT, new ErrorDTO(e, httpServletRequest));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return responseInternalServerError(Constants.OBJECT, new ErrorDTO(httpServletRequest));
		}
	}

	@Override
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity update(@RequestBody T entity) {
		try {
			getService().update(entity);
			JSONObject data = new JSONObject();
			data.put("success", true);
			data.put("message", tClass.getName() + " successfully updated");
			return response(data);
		} catch (ApiException e) {
			logger.error(e.getMessage(), e);
			return responseNotFound(Constants.OBJECT, new ErrorDTO(e, httpServletRequest));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return responseInternalServerError(Constants.OBJECT, new ErrorDTO(httpServletRequest));
		}
	}

	@Override
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity delete(@RequestBody T entity) {
		try {
			getService().delete(entity);
			JSONObject data = new JSONObject();
			data.put("success", true);
			data.put("message", tClass.getName() + " successfully deleted");
			return response(data);
		} catch (ApiException e) {
			logger.error(e.getMessage(), e);
			return responseNotFound(Constants.OBJECT, new ErrorDTO(e, httpServletRequest));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return responseInternalServerError(Constants.OBJECT, new ErrorDTO(httpServletRequest));
		}
	}

	@Override
	@RequestMapping(method = RequestMethod.DELETE, params = { "id"} )
	public ResponseEntity deleteById(@RequestParam("id") long id) {
		try {
			getService().deleteById(id);
			JSONObject data = new JSONObject();
			data.put("success", true);
			data.put("message", tClass.getName() + " successfully deleted");
			return response(data);
		} catch (ApiException e) {
			logger.error(e.getMessage(), e);
			return responseNotFound(Constants.OBJECT, new ErrorDTO(e, httpServletRequest));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return responseInternalServerError(Constants.OBJECT, new ErrorDTO(httpServletRequest));
		}
	}

}