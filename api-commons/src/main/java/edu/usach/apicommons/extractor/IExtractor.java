package edu.usach.apicommons.extractor;

public interface IExtractor {
	String databaseEngine();
	String databaseType();
	String[] supportedVersions();
}
