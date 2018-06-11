package edu.usach.apicommons.service;

import edu.usach.apicommons.errorhandling.ApiException;
import edu.usach.apicommons.extractor.IExtractor;
import org.json.simple.JSONObject;

import java.util.List;
import java.util.Map;

public interface IExtractorService<T extends IExtractor> {

	Object extract(Map<String, Object> connectionParams) throws ApiException;

	List<JSONObject> getParameters() throws ApiException;

	JSONObject getExtractorInfo() throws ApiException;
}
