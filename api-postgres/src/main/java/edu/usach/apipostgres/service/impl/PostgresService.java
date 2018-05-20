package edu.usach.apipostgres.service.impl;

import edu.usach.apicommons.service.ExtractorService;
import edu.usach.apipostgres.extractor.PostgresExtractor;
import edu.usach.apipostgres.service.IPostgresService;
import org.springframework.stereotype.Service;

@Service
public class PostgresService extends ExtractorService<PostgresExtractor> implements IPostgresService {

	@Override
	protected PostgresExtractor buildExtractor() {
		return new PostgresExtractor();
	}

}
