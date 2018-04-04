package edu.usach.apicommons.extractor;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractExtractor implements IExtractor{
	private String databaseName;
	private String databaseType;
	private String databaseVersion;
	private Map<String, String> databaseParams;
	public AbstractExtractor() {
		this.databaseParams = new HashMap<>();
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public String getDatabaseType() {
		return databaseType;
	}

	public void setDatabaseType(String databaseType) {
		this.databaseType = databaseType;
	}

	public String getDatabaseVersion() {
		return databaseVersion;
	}

	public void setDatabaseVersion(String databaseVersion) {
		this.databaseVersion = databaseVersion;
	}

	public Map<String, String> getDatabaseParams() {
		return databaseParams;
	}

	public void setDatabaseParams(Map<String, String> databaseParams) {
		this.databaseParams = databaseParams;
	}

	public void addParams(String key, String value) {
		this.databaseParams.put(key, value);
	}

}
