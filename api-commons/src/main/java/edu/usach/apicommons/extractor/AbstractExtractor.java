package edu.usach.apicommons.extractor;

import edu.usach.apicommons.dto.ConnectionParamsDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractExtractor implements IExtractor{
	protected final Logger logger = LogManager.getLogger(getClass());
	private ConnectionParams connectionParams;

	public AbstractExtractor(ConnectionParamsDTO connectionParams) {
		this.connectionParams = connectionParams;
	}

	protected ConnectionParams connectionParams() {
		return connectionParams;
	}

}
