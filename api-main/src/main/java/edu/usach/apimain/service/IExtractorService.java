package edu.usach.apimain.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Map;

public interface IExtractorService {

	JSONArray getExtractors(String engine, String version, String token) ;

	JSONObject callExtractor(String engine, String version, JSONObject connectionParams, String token) ;

	JSONArray getExtractorParams(String engine, String version, String token) ;

	JSONObject getExtractorInfo(String engine, String version, String token);

	Boolean testConnection(String type, String version, String token, Map<String, Object> connectionParams);
}
