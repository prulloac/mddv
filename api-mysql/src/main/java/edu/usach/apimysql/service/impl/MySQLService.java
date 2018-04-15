package edu.usach.apimysql.service.impl;

import edu.usach.apicommons.dto.ConnectionParamsDTO;
import edu.usach.apicommons.errorhandling.ApiException;
import edu.usach.apicommons.service.ExtractorService;
import edu.usach.apimysql.errorhandling.ErrorCode;
import edu.usach.apimysql.extractor.MySQLExtractor;
import edu.usach.apimysql.service.IMySQLService;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class MySQLService extends ExtractorService<MySQLExtractor> implements IMySQLService {

	private boolean connected = false;

	@Override
	public JSONObject extract(ConnectionParamsDTO connectionParamsDTO) throws ApiException {
		try {
			MySQLExtractor extractor = new MySQLExtractor();
			return extractor.extract(connectionParamsDTO);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ApiException(ErrorCode.CONNECTION_ERROR);
		}
	}
}
