package edu.usach.apicommons.dto;

import edu.usach.apicommons.errorhandling.ApiException;
import org.json.simple.JSONObject;

public interface IDTO {

	JSONObject asJSONObject(IDTO instance) throws ApiException;

}
