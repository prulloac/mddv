package edu.usach.apimongo.service;

import edu.usach.apicommons.dto.ConnectionParamsDTO;
import edu.usach.apicommons.errorhandling.ApiException;
import edu.usach.apicommons.service.IExtractorService;
import edu.usach.apimongo.dto.MongoConnectionParamsDTO;
import edu.usach.apimongo.extractor.MongoExtractor;
import org.json.simple.JSONObject;

public interface IMongoService extends IExtractorService<MongoExtractor> {
	@Override
	@Deprecated
	JSONObject extract(ConnectionParamsDTO connectionParamsDTO) throws ApiException;

	JSONObject extract(MongoConnectionParamsDTO connectionParamsDTO) throws ApiException;
}
