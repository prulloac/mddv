package edu.usach.apimain.service;

import edu.usach.apicommons.errorhandling.ApiException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public interface IExtractorService {

	JSONArray getExtractors(String engine, String version) throws ApiException;

	JSONObject callExtractor(String engine, String version, JSONObject connectionParams) throws ApiException;

	JSONObject getExtractorParams(String engine, String version) throws ApiException;
}
