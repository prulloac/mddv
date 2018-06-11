package edu.usach.apicommons.extractor;

import java.util.Map;

public interface IExtractor {
	String databaseEngine();
	String databaseType();
	String[] supportedVersions();
	Map<String, Object> connectionParameters();
	Object extract(Map<String, Object> connectionParams);
	boolean isRelational();
}
