package edu.usach.apicommons.extractor;

import edu.usach.apicommons.dto.NoSQLDocumentExtractionDTO;

import java.util.Map;

public interface NoSQLDocumentExtractor extends IExtractor {
    @Override
    default boolean isRelational() {
        return false;
    }
    NoSQLDocumentExtractionDTO extract(Map<String, Object> connectionParams);

}
