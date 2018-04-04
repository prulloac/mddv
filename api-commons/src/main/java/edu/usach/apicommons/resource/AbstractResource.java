package edu.usach.apicommons.resource;

import edu.usach.apicommons.errorhandling.ApiException;
import edu.usach.apicommons.errorhandling.ErrorDTO;
import edu.usach.apicommons.model.IEntity;
import edu.usach.apicommons.service.IService;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractResource<T extends IEntity> implements IResource<T> {

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

	protected JSONObject responseEntity(Object data) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("data", data);
		jsonObject.put("error", null);
		jsonObject.put("timestamp", ZonedDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
		return jsonObject;
	}

	protected JSONObject responseEntity(Object data, Object error) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("data", data);
		jsonObject.put("error", error);
		jsonObject.put("timestamp", ZonedDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
		return jsonObject;
	}

	protected ErrorDTO handleError(ApiException e) {
		log(e);
		return new ErrorDTO(e, getHttpServletRequest());
	}

	protected ErrorDTO handleError(Exception e) {
		log(e);
		return new ErrorDTO(getHttpServletRequest());
	}

	@Override
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity getById(@PathVariable Long id) {
		try {
			return new ResponseEntity<>(responseEntity(getService().findOne(id)), HttpStatus.OK);
		} catch (ApiException e) {
			return new ResponseEntity<>(responseEntity(new JSONObject(),handleError(e)), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(responseEntity(new JSONObject(),handleError(e)), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	@RequestMapping(method = RequestMethod.GET, value = "/{id}", params = { "show" })
	public ResponseEntity getByIdAndFilterOutput(@PathVariable Long id, @RequestParam("show") String filterString){
		try {
			return new ResponseEntity<>(responseEntity(getService().findAndFilter(id, filterString)), HttpStatus.OK);
		} catch (ApiException e) {
			return new ResponseEntity<>(responseEntity(new JSONObject(),handleError(e)), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(responseEntity(new JSONObject(),handleError(e)), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity getAll() {
		try {
			return new ResponseEntity<>(responseEntity(getService().findAll()), HttpStatus.OK);
		} catch (ApiException e) {
			return new ResponseEntity<>(responseEntity(new JSONObject(),handleError(e)), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(responseEntity(new JSONObject(),handleError(e)), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	@RequestMapping(method = RequestMethod.GET, params = { "page", "size" })
	public ResponseEntity getAllPaginated(@RequestParam("page") int page, @RequestParam("size") int size) {
		try {
			return new ResponseEntity<>(responseEntity(getService().findPaginated(page, size)), HttpStatus.OK);
		} catch (ApiException e) {
			return new ResponseEntity<>(responseEntity(new JSONObject(),handleError(e)), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(responseEntity(new JSONObject(),handleError(e)), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	@RequestMapping(method = RequestMethod.GET, params = { "size" })
	public ResponseEntity getAllPaginated(@RequestParam("size") int size) {
		try {
			return new ResponseEntity<>(responseEntity(getService().findPaginated(1, size)), HttpStatus.OK);
		} catch (ApiException e) {
			return new ResponseEntity<>(responseEntity(new JSONObject(),handleError(e)), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(responseEntity(new JSONObject(),handleError(e)), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@Override
	public ResponseEntity create(T entity) {
		try {
			getService().create(entity);
			JSONObject data = new JSONObject();
			data.put("success", true);
			data.put("message", "Entity successfully created");
			return new ResponseEntity<>(responseEntity(data), HttpStatus.OK);
		} catch (ApiException e) {
			return new ResponseEntity<>(responseEntity(new JSONObject(),handleError(e)), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(responseEntity(new JSONObject(),handleError(e)), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity update(T entity) {
		try {
			getService().update(entity);
			JSONObject data = new JSONObject();
			data.put("success", true);
			data.put("message", "Entity successfully updated");
			return new ResponseEntity<>(responseEntity(data), HttpStatus.OK);
		} catch (ApiException e) {
			return new ResponseEntity<>(responseEntity(new JSONObject(),handleError(e)), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(responseEntity(new JSONObject(),handleError(e)), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity delete(T entity) {
		try {
			getService().delete(entity);
			JSONObject data = new JSONObject();
			data.put("success", true);
			data.put("message", "Entity successfully deleted");
			return new ResponseEntity<>(responseEntity(data), HttpStatus.OK);
		} catch (ApiException e) {
			return new ResponseEntity<>(responseEntity(new JSONObject(),handleError(e)), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(responseEntity(new JSONObject(),handleError(e)), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity deleteById(long id) {
		try {
			getService().deleteById(id);
			JSONObject data = new JSONObject();
			data.put("success", true);
			data.put("message", "Entity successfully deleted");
			return new ResponseEntity<>(responseEntity(data), HttpStatus.OK);
		} catch (ApiException e) {
			return new ResponseEntity<>(responseEntity(new JSONObject(),handleError(e)), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(responseEntity(new JSONObject(),handleError(e)), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
