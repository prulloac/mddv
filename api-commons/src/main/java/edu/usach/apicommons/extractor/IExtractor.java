package edu.usach.apicommons.extractor;

import org.json.simple.JSONObject;

import java.util.Map;

public interface IExtractor {
	void connect();
	JSONObject extract();
	String databaseType();
	boolean isRelational();
	String[] supportedVersions();
}
