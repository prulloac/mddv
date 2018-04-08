package edu.usach.apicommons.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.transaction.Transactional;

@Transactional
public abstract class ExtractorService implements IExtractorService {
	protected final Logger logger = LogManager.getLogger(getClass());

}
