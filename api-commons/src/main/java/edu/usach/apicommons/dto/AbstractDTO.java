package edu.usach.apicommons.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.usach.apicommons.errorhandling.ApiException;
import edu.usach.apicommons.errorhandling.ErrorCode;
import edu.usach.apicommons.model.IEntity;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public abstract class AbstractDTO<T extends IEntity> implements IDTO {

	public JSONObject asJSONObject(IDTO instance) throws ApiException {
		try {
			ObjectMapper mapper	= new ObjectMapper();
			JSONParser parser = new JSONParser();
			return (JSONObject) parser.parse(mapper.writeValueAsString(instance));
		} catch (Exception e) {
			throw new ApiException(ErrorCode.UNEXPECTED_ERROR, e);
		}
	}

}
