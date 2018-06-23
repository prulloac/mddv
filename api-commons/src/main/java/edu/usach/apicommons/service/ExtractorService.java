package edu.usach.apicommons.service;

import edu.usach.apicommons.errorhandling.ApiException;
import edu.usach.apicommons.errorhandling.ErrorCode;
import edu.usach.apicommons.extractor.IExtractor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Transactional
@Slf4j
public abstract class ExtractorService<T extends IExtractor> extends AbstractService implements IExtractorService<T> {

	protected abstract T buildExtractor();

	@Override
	public Object extract(Map<String, Object> connectionParams) throws ApiException {
		try {
			return buildExtractor().extract(connectionParams);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ApiException(ErrorCode.CONNECTION_ERROR);
		}
	}

	@Override
	public List<Map<String,Object>> getParameters() throws ApiException {
		try {
			return buildExtractor().connectionParameters();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
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
			log.error(e.getMessage(), e);
			throw new ApiException(ErrorCode.UNEXPECTED_ERROR);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public Boolean testConnection(Map<String,Object> connectionParamsDTO) throws ApiException {
		try {
			return buildExtractor().testConnection(connectionParamsDTO);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ApiException(ErrorCode.INVALID_CONNECTION_PARAMETERS);
		}
	}
}
