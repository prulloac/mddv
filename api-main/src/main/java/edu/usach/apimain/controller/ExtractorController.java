package edu.usach.apimain.controller;

import edu.usach.apicommons.controller.AbstractController;
import edu.usach.apicommons.errorhandling.ErrorDTO;
import edu.usach.apimain.service.IExtractorService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static edu.usach.apicommons.util.Constants.ARRAY;
import static edu.usach.apicommons.util.Constants.OBJECT;

@CrossOrigin(maxAge = 7200)
@RestController
@RequestMapping("/extractors")
public class ExtractorController extends AbstractController {

	@Autowired
	private IExtractorService service;

	@RequestMapping(value = "/extractors", method = RequestMethod.GET)
	public ResponseEntity<Object> getExtractors(){
		try {
			return response(service.getSupportedEngines());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return responseInternalServerError(ARRAY, new ErrorDTO(httpServletRequest));
		}
	}

	@RequestMapping(value = "/extract", method = RequestMethod.POST)
	public ResponseEntity<Object> callExtractor(
			@RequestParam("engine") String engine,
			@RequestParam("version") String version,
			@RequestBody JSONObject connectionParams
	) {
		try {
			return response(service.callExtractor(engine, version, connectionParams));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return responseInternalServerError(OBJECT, new ErrorDTO(httpServletRequest));
		}
	}

}
