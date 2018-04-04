package edu.usach.apicommons.extractor;

import org.json.simple.JSONObject;

import java.util.Map;

public interface IExtractor {
	void connect();
	void connect(Map<String, String> connectionParams);
	JSONObject extract();
}
