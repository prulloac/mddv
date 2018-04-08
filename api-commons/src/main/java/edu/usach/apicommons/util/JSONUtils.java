package edu.usach.apicommons.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.Serializable;

public class JSONUtils {

	private static Logger logger = LogManager.getLogger(JSONUtils.class);

	public static JSONObject toJSONObject(Serializable entity) {
		ObjectMapper mapper = new ObjectMapper();
		JSONParser parser = new JSONParser();
		try {
			return (JSONObject) parser.parse(mapper.writeValueAsString(entity));
		} catch (ParseException | JsonProcessingException e) {
			logger.error(e.getMessage(), e);
			return new JSONObject();
		}
	}

	public static String toJSONString(Serializable entity) {
		return toJSONObject(entity).toJSONString();
	}

	private JSONUtils() throws IllegalAccessError {
		throw new IllegalAccessError("Utility class");
	}

}
