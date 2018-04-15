package edu.usach.apih2.service.impl;

import edu.usach.apicommons.dto.ConnectionParamsDTO;
import edu.usach.apicommons.errorhandling.ApiException;
import edu.usach.apicommons.service.ExtractorService;
import edu.usach.apih2.errorhandling.ErrorCode;
import edu.usach.apih2.extractor.H2Extractor;
import edu.usach.apih2.service.IH2Service;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class H2Service extends ExtractorService<H2Extractor> implements IH2Service {

	@Override
	public JSONObject extract(ConnectionParamsDTO connectionParamsDTO) throws ApiException {
		try {
			H2Extractor extractor = new H2Extractor();
			return extractor.extract(connectionParamsDTO);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ApiException(ErrorCode.CONNECTION_ERROR);
		}
	}
}
