package edu.usach.apiarango.service.impl;

import edu.usach.apiarango.extractor.ArangoExtractor;
import edu.usach.apiarango.service.IArangoService;
import edu.usach.apicommons.service.ExtractorService;
import org.springframework.stereotype.Service;

@Service
public class ArangoService extends ExtractorService<ArangoExtractor> implements IArangoService {

	@Override
	protected ArangoExtractor buildExtractor() {
		return new ArangoExtractor();
	}
}
