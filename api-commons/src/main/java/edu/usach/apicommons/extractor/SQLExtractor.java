package edu.usach.apicommons.extractor;

import edu.usach.apicommons.dto.SQLExtractionDTO;

import java.util.Map;

public interface SQLExtractor extends IExtractor {
	@Override
	default boolean isRelational() {
		return true;
	}
	SQLExtractionDTO extract(Map<String, Object> connectionParams);
}
