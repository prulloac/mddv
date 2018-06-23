package edu.usach.apicommons.extractor;

import java.util.List;
import java.util.Map;

public interface IExtractor {
	String databaseEngine();
	String databaseType();
	String[] supportedVersions();
	List<Map<String, Object>> connectionParameters();
	Object extract(Map<String, Object> connectionParams);
	boolean isRelational();
	Boolean testConnection(Map<String, Object> connectionParams);
}
