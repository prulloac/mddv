package edu.usach.apicommons.extractor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractExtractor implements IExtractor{
	protected final Logger logger = LogManager.getLogger(getClass());

}
