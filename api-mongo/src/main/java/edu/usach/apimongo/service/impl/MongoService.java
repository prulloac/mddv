package edu.usach.apimongo.service.impl;

import edu.usach.apicommons.dto.ConnectionParamsDTO;
import edu.usach.apicommons.errorhandling.ApiException;
import edu.usach.apicommons.service.ExtractorService;
import edu.usach.apimongo.dto.MongoConnectionParamsDTO;
import edu.usach.apimongo.errorhandling.ErrorCode;
import edu.usach.apimongo.extractor.MongoExtractor;
import edu.usach.apimongo.service.IMongoService;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class MongoService extends ExtractorService<MongoExtractor> implements IMongoService {
	@Override
	@Deprecated
	public JSONObject extract(ConnectionParamsDTO connectionParamsDTO) throws ApiException {
		return null;
	}

	@Transactional
	public JSONObject extract(MongoConnectionParamsDTO connectionParamsDTO) throws ApiException {
		try {
			MongoExtractor extractor = new MongoExtractor();
			return extractor.extract(connectionParamsDTO);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ApiException(ErrorCode.CONNECTION_ERROR);
		}
	}
}
