package edu.usach.apicommons.extractor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractExtractor implements IExtractor{
	protected final Logger logger = LogManager.getLogger(getClass());

	protected String databaseName;
	protected String databaseType;
	protected String databaseVersion;
	protected Map<String, String> databaseParams;

	public AbstractExtractor(String url, String username, String password) {
		this.databaseParams = new HashMap<>();
		this.databaseParams.put("url",url);
		this.databaseParams.put("username", username);
		this.databaseParams.put("password", password);
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
