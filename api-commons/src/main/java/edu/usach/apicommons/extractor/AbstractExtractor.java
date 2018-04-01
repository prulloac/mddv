package edu.usach.apicommons.extractor;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractExtractor {
	protected String databaseName;
	protected String databaseType;
	protected String databaseVersion;
	protected Map<String, Object> databaseParams;
	public AbstractExtractor() {
		this.databaseParams = new HashMap<>();
	}
	public void addParams(String key, Object value) {
		this.databaseParams.put(key, value);
	}
	public abstract void connect();
	public abstract MetaObject extract();

}
