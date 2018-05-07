package edu.usach.apimysql.controller;

import edu.usach.apicommons.controller.ExtractorController;
import edu.usach.apicommons.service.IExtractorService;
import edu.usach.apimysql.extractor.MySQLExtractor;
import edu.usach.apimysql.service.IMySQLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = "*", maxAge = 7200)
@RequestMapping("extractor")
public class MySQLController extends ExtractorController<MySQLExtractor> {

	@Autowired
	private IMySQLService mySQLService;

	@Override
	protected IExtractorService<MySQLExtractor> getService() {
		return mySQLService;
	}

}
