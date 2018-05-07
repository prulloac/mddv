package edu.usach.apimongo.controller;

import edu.usach.apicommons.controller.ExtractorController;
import edu.usach.apicommons.service.IExtractorService;
import edu.usach.apimongo.extractor.MongoExtractor;
import edu.usach.apimongo.service.IMongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(value = "*", maxAge = 7200)
@RequestMapping("extractor")
public class MongoController extends ExtractorController<MongoExtractor> {

	@Autowired
	private IMongoService service;

	@Override
	protected IExtractorService<MongoExtractor> getService() {
		return service;
	}
}
