package edu.usach.apicommons.extractor;

import org.json.simple.JSONObject;

import java.util.Map;

public interface IExtractor {
	String databaseEngine();
	String databaseType();
	String[] supportedVersions();
	JSONObject connectionParameters();
	Object extract(Map<String, Object> connectionParams);
	boolean isRelational();
}
