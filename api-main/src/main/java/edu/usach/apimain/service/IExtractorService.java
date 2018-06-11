package edu.usach.apimain.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public interface IExtractorService {

	JSONArray getExtractors(String engine, String version, String token) ;

	JSONObject callExtractor(String engine, String version, JSONObject connectionParams, String token) ;

	JSONObject getExtractorParams(String engine, String version, String token) ;
}
