package edu.usach.apih2.controller;

import edu.usach.apicommons.controller.ExtractorController;
import edu.usach.apicommons.dto.ConnectionParamsDTO;
import edu.usach.apicommons.service.IExtractorService;
import edu.usach.apih2.extractor.H2Extractor;
import edu.usach.apih2.service.IH2Service;
import edu.usach.apih2.service.impl.H2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class H2Controller extends ExtractorController<H2Extractor> {

	@Autowired
	private IH2Service h2Service;


	@Override
	protected IExtractorService<H2Extractor> getService() {
		return h2Service;
	}
}
