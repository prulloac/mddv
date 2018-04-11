package edu.usach.apicommons.service;

import edu.usach.apicommons.extractor.IExtractor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.transaction.Transactional;

@Transactional
public abstract class ExtractorService<T extends IExtractor> implements IExtractorService<T> {
	protected final Logger logger = LogManager.getLogger(getClass());

}
