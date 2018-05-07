package edu.usach.apimain.service;

import edu.usach.apicommons.errorhandling.ApiException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Map;

public interface IExtractorService {

	JSONArray getSupportedEngines() throws ApiException;

	String getExtractorEntrypoint(String engine, String version);

	JSONObject callExtractor(String engine, String version, JSONObject connectionParams) throws ApiException;

	JSONObject getExtractorParams(String engine, String version) throws ApiException;
}
