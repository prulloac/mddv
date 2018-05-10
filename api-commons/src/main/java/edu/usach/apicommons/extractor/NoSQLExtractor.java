package edu.usach.apicommons.extractor;

public interface NoSQLExtractor extends IExtractor {
	@Override
	default boolean isRelational() {
		return false;
	}
}
