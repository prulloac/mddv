package edu.usach.apicommons.service;

import edu.usach.apicommons.dto.ConnectionParamsDTO;
import edu.usach.apicommons.errorhandling.ApiException;
import edu.usach.apicommons.extractor.IExtractor;
import org.json.simple.JSONObject;

public interface IExtractorService<T extends IExtractor> {

	JSONObject extract(ConnectionParamsDTO connectionParams) throws ApiException;

}
