package edu.usach.apiarango.service.impl;

import edu.usach.apiarango.errorhandling.ErrorCode;
import edu.usach.apiarango.extractor.ArangoExtractor;
import edu.usach.apiarango.service.IArangoService;
import edu.usach.apicommons.dto.ConnectionParamsDTO;
import edu.usach.apicommons.errorhandling.ApiException;
import edu.usach.apicommons.service.ExtractorService;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class ArangoService extends ExtractorService<ArangoExtractor> implements IArangoService {
	@Override
	public JSONObject extract(ConnectionParamsDTO connectionParamsDTO) throws ApiException {
		try {
			ArangoExtractor extractor = new ArangoExtractor();
			return extractor.extract(connectionParamsDTO);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ApiException(ErrorCode.CONNECTION_ERROR);
		}
	}
}
