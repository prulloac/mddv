package edu.usach.apicommons.service;

import edu.usach.apicommons.errorhandling.ApiException;
import edu.usach.apicommons.errorhandling.ErrorCode;
import edu.usach.apicommons.extractor.IExtractor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
	public List<JSONObject> getParameters() throws ApiException {
		try {
			Map<String, Object> parameters = buildExtractor().connectionParameters();
			return parameters
					.keySet()
					.stream()
					.map(x -> {
						JSONObject param = new JSONObject();
						param.put("name", x.toString());
						param.put("type", parameters.get(x).toString());
						return param;
					})
					.collect(Collectors.toList());
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
}
