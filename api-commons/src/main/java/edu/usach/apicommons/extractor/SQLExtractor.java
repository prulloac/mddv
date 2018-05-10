package edu.usach.apicommons.extractor;

public interface SQLExtractor extends IExtractor {
	@Override
	default boolean isRelational() {
		return true;
	}
}
