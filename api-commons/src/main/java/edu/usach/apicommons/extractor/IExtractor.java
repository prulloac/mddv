package edu.usach.apicommons.extractor;

import org.json.simple.JSONObject;

import java.util.Map;

public interface IExtractor {
	String databaseEngine();
	String databaseType();
	String[] supportedVersions();
	JSONObject extract(Map<String, Object> connectionParams);
	JSONObject connectionParameters();
}
