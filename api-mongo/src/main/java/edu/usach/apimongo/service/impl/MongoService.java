package edu.usach.apimongo.service.impl;

import edu.usach.apicommons.service.ExtractorService;
import edu.usach.apimongo.extractor.MongoExtractor;
import edu.usach.apimongo.service.IMongoService;
import org.springframework.stereotype.Service;

@Service
public class MongoService extends ExtractorService<MongoExtractor> implements IMongoService {
	@Override
	protected MongoExtractor buildExtractor() {
		return new MongoExtractor();
	}
}
