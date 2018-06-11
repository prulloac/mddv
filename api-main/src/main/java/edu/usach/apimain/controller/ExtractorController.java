package edu.usach.apimain.controller;

import edu.usach.apicommons.controller.AbstractController;
import edu.usach.apicommons.errorhandling.ApiException;
import edu.usach.apicommons.errorhandling.ErrorDTO;
import edu.usach.apimain.service.IExtractorService;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static edu.usach.apicommons.util.Constants.ARRAY;
import static edu.usach.apicommons.util.Constants.OBJECT;

@CrossOrigin(maxAge = 7200)
@RestController
@RequestMapping("/extractors")
@Slf4j
public class ExtractorController extends AbstractController {

	@Autowired
	private IExtractorService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Object> getExtractors(
			@RequestParam(value = "engine", required = false) String engine,
			@RequestParam(value = "version", required = false) String version
	) throws ApiException {
		return response(service.getExtractors(engine, version));
	}

	@RequestMapping(value = "/extract", method = RequestMethod.POST)
	public ResponseEntity<Object> callExtractor(
			@RequestParam("engine") String engine,
			@RequestParam("version") String version,
			@RequestBody JSONObject connectionParams
	) throws ApiException {
		return new ResponseEntity<>(service.callExtractor(engine, version, connectionParams), HttpStatus.OK);
	}

	@RequestMapping(value = "/params", method = RequestMethod.GET)
	public ResponseEntity<Object> getExtractorParams(
			@RequestParam("engine") String engine,
			@RequestParam("version") String version
	) throws ApiException {
		return new ResponseEntity<>(service.getExtractorParams(engine, version), HttpStatus.OK);
	}

}
