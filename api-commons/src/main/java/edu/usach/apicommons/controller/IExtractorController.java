package edu.usach.apicommons.controller;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface IExtractorController {

	ResponseEntity<Object> extract(Map<String, Object> connectionParamsDTO);

	ResponseEntity<Object> getParameters();

}
