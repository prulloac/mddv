package edu.usach.apicommons.service;

import edu.usach.apicommons.errorhandling.ApiException;
import edu.usach.apicommons.errorhandling.ErrorCode;
import edu.usach.apicommons.extractor.IExtractor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;

import javax.transaction.Transactional;
import java.util.Map;

@Transactional
public abstract class ExtractorService<T extends IExtractor> implements IExtractorService<T> {
	protected final Logger logger = LogManager.getLogger(getClass());

	protected abstract T buildExtractor();

	@Override
	public JSONObject extract(Map<String, Object> connectionParams) throws ApiException {
		try {
			return buildExtractor().extract(connectionParams);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ApiException(ErrorCode.CONNECTION_ERROR);
		}
	}

	@Override
	public JSONObject getParameters() throws ApiException {
		try {
			return buildExtractor().connectionParameters();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ApiException(ErrorCode.UNEXPECTED_ERROR);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public JSONObject getExtractorInfo() throws ApiException {
		try {
			T t = buildExtractor();
			JSONObject extractorInfo = new JSONObject();
			extractorInfo.put("engine",t.databaseEngine());
			extractorInfo.put("versions", t.supportedVersions());
			extractorInfo.put("relational", t.isRelational());
			extractorInfo.put("type", t.databaseType());
			return extractorInfo;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ApiException(ErrorCode.UNEXPECTED_ERROR);
		}
	}
}
