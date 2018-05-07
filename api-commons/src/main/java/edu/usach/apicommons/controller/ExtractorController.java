package edu.usach.apicommons.controller;

import edu.usach.apicommons.errorhandling.ApiException;
import edu.usach.apicommons.errorhandling.ErrorDTO;
import edu.usach.apicommons.extractor.IExtractor;
import edu.usach.apicommons.service.IExtractorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

import static edu.usach.apicommons.util.Constants.OBJECT;

public abstract class ExtractorController<T extends IExtractor> extends AbstractController implements IExtractorController {

	protected abstract IExtractorService<T> getService();

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> extract(@RequestBody Map<String, Object> connectionParamsDTO) {
		try {
			return response(getService().extract(connectionParamsDTO));
		} catch (ApiException e) {
			logger.error(e.getMessage(), e);
			return responseNotFound(OBJECT, new ErrorDTO(e, httpServletRequest));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return responseInternalServerError(OBJECT, new ErrorDTO(httpServletRequest));
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Object> getParameters() {
		try {
			return response(getService().getParameters());
		} catch (ApiException e) {
			logger.error(e.getMessage(), e);
			return responseNotFound(OBJECT, new ErrorDTO(e, httpServletRequest));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return responseInternalServerError(OBJECT, new ErrorDTO(httpServletRequest));
		}
	}

}
