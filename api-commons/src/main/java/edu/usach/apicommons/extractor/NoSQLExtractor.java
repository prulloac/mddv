package edu.usach.apicommons.extractor;

import org.json.simple.JSONArray;

public interface NoSQLExtractor extends IExtractor {
    boolean isRelational = false;
    JSONArray extract();
}
