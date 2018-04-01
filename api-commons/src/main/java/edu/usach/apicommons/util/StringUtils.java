package edu.usach.apicommons.util;

public class StringUtils {

	public static String capitalize(String string) {
		return string.substring(0,1).toUpperCase().concat(string.substring(1));
	}

}
