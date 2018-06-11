package edu.usach.apicommons.controller;

import edu.usach.apicommons.errorhandling.ApiException;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface IExtractorController {

	ResponseEntity<Object> extract(Map<String, Object> connectionParamsDTO) throws ApiException;

	ResponseEntity<Object> getParameters() throws ApiException;

	ResponseEntity<Object> getExtractorInfo() throws ApiException;

}
