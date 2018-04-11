package edu.usach.apicommons.extractor;

public interface IExtractor {
	void connect();
	String databaseEngine();
	String databaseType();
	String[] supportedVersions();
}
