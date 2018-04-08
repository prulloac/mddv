package edu.usach.apicommons.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class ExtractorController implements IExtractorController {
	protected final Logger logger = LogManager.getLogger(getClass());

}
