package edu.usach.apicommons.resource;

import edu.usach.apicommons.errorhandling.ApiException;
import edu.usach.apicommons.errorhandling.ErrorResponse;
import edu.usach.apicommons.service.IService;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractResource<T extends Serializable> implements IResource<T> {

	protected final Logger logger = LogManager.getLogger(getClass());

	protected abstract HttpServletRequest getHttpServletRequest();
	protected abstract IService<T> getService();

	protected void log(Exception e) {
		log(e,false);
	}

	protected void log(Exception e, boolean debug) {
		if (debug)
			logger.info(e.getLocalizedMessage(), e);
		else
			logger.error(e.getLocalizedMessage(), e);
	}

	protected ResponseEntity<ErrorResponse> errorEntity(ApiException e, HttpStatus httpStatus) {
		log(e);
		ErrorResponse errorResponse = new ErrorResponse(e, getHttpServletRequest());
		errorResponse.setStatus(httpStatus.value());
		return new ResponseEntity<>(
				new ErrorResponse(e, getHttpServletRequest()),
				httpStatus
		);
	}

	protected ResponseEntity<ErrorResponse> errorEntity(Exception e) {
		log(e);
		ErrorResponse errorResponse = new ErrorResponse(getHttpServletRequest());
		errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(
				new ErrorResponse(getHttpServletRequest()),
				HttpStatus.BAD_REQUEST
		);
	}

	@Override
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity getById(@PathVariable Long id) {
		try {
			return new ResponseEntity<>(getService().findOne(id), HttpStatus.OK);
		} catch (ApiException e) {
			return errorEntity(e, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return errorEntity(e);
		}
	}

	@Override
	@RequestMapping(method = RequestMethod.GET, value = "/{id}", params = { "show" })
	public ResponseEntity getByIdAndFilterOutput(@PathVariable Long id, @RequestParam("show") String filterString){
		try {
			return new ResponseEntity<Map<String, Object>>(getService().convertToMap(id, filterString), HttpStatus.OK);
		} catch (ApiException e) {
			return errorEntity(e, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return errorEntity(e);
		}
	}

	@Override
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity getAll() {
		try {
			return new ResponseEntity<java.util.List<T>>(getService().findAll(), HttpStatus.OK);
		} catch (ApiException e) {
			return errorEntity(e, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return errorEntity(e);
		}
	}

	@Override
	@RequestMapping(method = RequestMethod.GET, params = { "page", "size" })
	public ResponseEntity getAllPaginated(@RequestParam("page") int page, @RequestParam("size") int size) {
		try {
			return new ResponseEntity<org.springframework.data.domain.Page<T>>(getService().findPaginated(page, size), HttpStatus.OK);
		} catch (ApiException e) {
			return errorEntity(e, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return errorEntity(e);
		}
	}

	@Override
	@RequestMapping(method = RequestMethod.GET, params = { "size" })
	public ResponseEntity getAllPaginated(@RequestParam("size") int size) {
		try {
			return new ResponseEntity<>(getService().findPaginated(1, size), HttpStatus.OK);
		} catch (ApiException e) {
			return errorEntity(e, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return errorEntity(e);
		}
	}


	@Override
	public ResponseEntity create(T entity) {
		try {
			Map<String, Object> response = new HashMap<>();
			response.put("entity", getService().create(entity));
			response.put("success", true);
			response.put("message", "Entity successfully created");
			return new ResponseEntity<>(
					response,
					HttpStatus.OK
			);
		} catch (ApiException e) {
			return errorEntity(e, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return errorEntity(e);
		}
	}

	@Override
	public ResponseEntity update(T entity) {
		try {
			Map<String, Object> response = new HashMap<>();
			response.put("entity", getService().update(entity));
			response.put("success", true);
			response.put("message", "Entity successfully updated");
			return new ResponseEntity<>(
					response,
					HttpStatus.OK
			);
		} catch (ApiException e) {
			return errorEntity(e, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return errorEntity(e);
		}
	}

	@Override
	public ResponseEntity delete(T entity) {
		try {
			getService().delete(entity);
			Map<String, Object> response = new HashMap<>();
			response.put("success", true);
			response.put("message", "Entity successfully deleted");
			return new ResponseEntity<>(
					response,
					HttpStatus.OK
			);
		} catch (ApiException e) {
			return errorEntity(e, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return errorEntity(e);
		}
	}

	@Override
	public ResponseEntity deleteById(long id) {
		try {
			getService().deleteById(id);
			Map<String, Object> response = new HashMap<>();
			response.put("success", true);
			response.put("message", "Entity successfully deleted");
			return new ResponseEntity<>(
					response,
					HttpStatus.OK
			);
		} catch (ApiException e) {
			return errorEntity(e, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return errorEntity(e);
		}
	}

}
