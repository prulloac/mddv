package edu.usach.apicommons.util;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Constants {

	public static final JSONObject OBJECT = new JSONObject();
	public static final JSONArray ARRAY = new JSONArray();

	private Constants() throws IllegalAccessException {
		throw new IllegalAccessException("Utility class");
	}

}
