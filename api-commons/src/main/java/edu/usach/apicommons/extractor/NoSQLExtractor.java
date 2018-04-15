package edu.usach.apicommons.extractor;

import edu.usach.apicommons.dto.ConnectionParamsDTO;
import org.json.simple.JSONObject;

public interface NoSQLExtractor extends IExtractor {
		boolean isRelational = false;
		JSONObject extract(ConnectionParamsDTO connectionParams);
}
