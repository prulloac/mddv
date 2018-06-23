package edu.usach.apicommons.service;

import edu.usach.apicommons.errorhandling.ApiException;
import edu.usach.apicommons.extractor.IExtractor;

import java.util.List;
import java.util.Map;

public interface IExtractorService<T extends IExtractor> {

	Object extract(Map<String, Object> connectionParams) throws ApiException;

	List<Map<String,Object>> getParameters() throws ApiException;

	Map<String,Object> getExtractorInfo() throws ApiException;

	Boolean testConnection(Map<String,Object> connectionParamsDTO);
}
