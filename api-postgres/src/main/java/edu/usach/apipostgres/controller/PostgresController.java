package edu.usach.apipostgres.controller;

import edu.usach.apicommons.controller.ExtractorController;
import edu.usach.apicommons.service.IExtractorService;
import edu.usach.apipostgres.extractor.PostgresExtractor;
import edu.usach.apipostgres.service.IPostgresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = "*", maxAge = 7200)
@RequestMapping("extractor")
public class PostgresController extends ExtractorController<PostgresExtractor> {

	@Autowired
	private IPostgresService mySQLService;

	@Override
	protected IExtractorService<PostgresExtractor> getService() {
		return mySQLService;
	}

}
