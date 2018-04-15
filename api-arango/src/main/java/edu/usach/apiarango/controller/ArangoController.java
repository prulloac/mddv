package edu.usach.apiarango.controller;

import edu.usach.apiarango.extractor.ArangoExtractor;
import edu.usach.apiarango.service.IArangoService;
import edu.usach.apicommons.controller.ExtractorController;
import edu.usach.apicommons.service.IExtractorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ArangoController extends ExtractorController<ArangoExtractor> {

	@Autowired
	private IArangoService service;

	@Override
	protected IExtractorService<ArangoExtractor> getService() {
		return service;
	}
}
