package edu.usach.apicommons.controller;

import edu.usach.apicommons.dto.ConnectionParamsDTO;
import edu.usach.apicommons.errorhandling.ApiException;
import edu.usach.apicommons.errorhandling.ErrorDTO;
import edu.usach.apicommons.extractor.IExtractor;
import edu.usach.apicommons.service.IExtractorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static edu.usach.apicommons.util.Constants.OBJECT;

public abstract class ExtractorController<T extends IExtractor> extends AbstractController implements IExtractorController {

	protected abstract IExtractorService<T> getService();

	@RequestMapping(method = RequestMethod.POST, value = "/extract")
	public ResponseEntity extract(@RequestBody ConnectionParamsDTO connectionParamsDTO) {
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


}
