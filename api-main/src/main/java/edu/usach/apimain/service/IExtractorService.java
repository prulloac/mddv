package edu.usach.apimain.service;

import edu.usach.apicommons.errorhandling.ApiException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public interface IExtractorService {

	JSONArray getExtractors(String engine, String version) ;

	JSONObject callExtractor(String engine, String version, JSONObject connectionParams) ;

	JSONObject getExtractorParams(String engine, String version) ;
}
