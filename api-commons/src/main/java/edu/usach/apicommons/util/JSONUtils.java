package edu.usach.apicommons.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.Serializable;

@Slf4j
public class JSONUtils {

	public static JSONObject toJSONObject(Serializable entity) {
		ObjectMapper mapper = new ObjectMapper();
		JSONParser parser = new JSONParser();
		try {
			return (JSONObject) parser.parse(mapper.writeValueAsString(entity));
		} catch (ParseException | JsonProcessingException e) {
			log.error(e.getMessage(), e);
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
