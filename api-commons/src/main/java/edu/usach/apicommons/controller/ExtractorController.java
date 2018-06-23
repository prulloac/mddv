package edu.usach.apicommons.controller;

import edu.usach.apicommons.errorhandling.ApiException;
import edu.usach.apicommons.errorhandling.ErrorCode;
import edu.usach.apicommons.extractor.IExtractor;
import edu.usach.apicommons.service.IExtractorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@SuppressWarnings("unchecked")
@Slf4j
@Controller
public abstract class ExtractorController<T extends IExtractor> extends AbstractController implements IExtractorController {

	protected abstract IExtractorService<T> getService();

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> extract(@RequestBody Map<String, Object> connectionParamsDTO) {
		if (!isAuthenticated())
			return responseApiException(new ApiException(ErrorCode.UNAUTHORIZED), HttpStatus.UNAUTHORIZED);
		try {
			return response(getService().extract(connectionParamsDTO));
		} catch (ApiException e) {
			log.error(e.getMessage(), e);
			return responseApiException(e, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return responseException(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Object> getParameters() {
		if (!isAuthenticated())
			return responseApiException(new ApiException(ErrorCode.UNAUTHORIZED), HttpStatus.UNAUTHORIZED);
		try {
			return response(getService().getParameters());
		} catch (ApiException e) {
			log.error(e.getMessage(), e);
			return responseApiException(e, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return responseException(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/info")
	public ResponseEntity<Object> getExtractorInfo() {
		if (!isAuthenticated())
			return responseApiException(new ApiException(ErrorCode.UNAUTHORIZED), HttpStatus.UNAUTHORIZED);
		try {
			return response(getService().getExtractorInfo());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return responseException(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/test")
	public ResponseEntity<Object> testConnection(@RequestBody Map<String, Object> connectionParamsDTO) {
		if (!isAuthenticated())
			return responseApiException(new ApiException(ErrorCode.UNAUTHORIZED), HttpStatus.UNAUTHORIZED);
		try {
			return response(getService().testConnection(connectionParamsDTO));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return responseException(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
