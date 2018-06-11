package edu.usach.apicommons.controller;

import edu.usach.apicommons.errorhandling.ApiException;
import edu.usach.apicommons.errorhandling.ErrorCode;
import edu.usach.apicommons.extractor.IExtractor;
import edu.usach.apicommons.service.IExtractorService;
import lombok.extern.slf4j.Slf4j;
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

	@Override
	@RequestMapping(method = RequestMethod.POST, name = "Extract")
	public ResponseEntity<Object> extract(@RequestBody Map<String, Object> connectionParamsDTO) throws ApiException {
		if (!isAuthenticated())
			throw new ApiException(ErrorCode.UNAUTHORIZED);
		return response(getService().extract(connectionParamsDTO));
	}

	@Override
	@RequestMapping(method = RequestMethod.GET, name = "Get extractor connection parameters")
	public ResponseEntity<Object> getParameters() throws ApiException {
		if (!isAuthenticated())
			throw new ApiException(ErrorCode.UNAUTHORIZED);
		return response(getService().getParameters());
	}

	@Override
	@RequestMapping(method = RequestMethod.GET, value = "/info", name = "Get extractor info")
	public ResponseEntity<Object> getExtractorInfo() throws ApiException {
		if (!isAuthenticated())
			throw new ApiException(ErrorCode.UNAUTHORIZED);
		return response(getService().getExtractorInfo());
	}

}
