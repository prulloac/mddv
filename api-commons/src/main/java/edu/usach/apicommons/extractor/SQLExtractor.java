package edu.usach.apicommons.extractor;

import org.json.simple.JSONArray;

public interface SQLExtractor extends IExtractor {
    boolean isRelational = true;
    JSONArray extractTables();
    JSONArray extractColumns();
    JSONArray extractRelations();
}
