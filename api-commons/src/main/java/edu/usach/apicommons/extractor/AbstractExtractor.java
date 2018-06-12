package edu.usach.apicommons.extractor;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public abstract class AbstractExtractor implements IExtractor{

	protected Map<String, Object> newConnectionParameter(String type, String name, String label) {
		Map<String, Object> param = new HashMap<>();
		param.put("type", type);
		param.put("name", name);
		param.put("label", label);
		return param;
	}

}
