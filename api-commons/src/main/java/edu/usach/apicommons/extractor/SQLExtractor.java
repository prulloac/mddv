package edu.usach.apicommons.extractor;

import edu.usach.apicommons.dto.ConnectionParamsDTO;
import org.json.simple.JSONObject;

public interface SQLExtractor extends IExtractor {
    boolean isRelational = true;
    JSONObject extract(ConnectionParamsDTO connectionParams);
}
