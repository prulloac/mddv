package edu.usach.apicommons.service;

import edu.usach.apicommons.errorhandling.ApiException;
import edu.usach.apicommons.extractor.IExtractor;
import org.json.simple.JSONObject;

import java.util.Map;

public interface IExtractorService<T extends IExtractor> {

	JSONObject extract(Map<String, Object> connectionParams) throws ApiException;

	JSONObject getParameters() throws ApiException;
}
