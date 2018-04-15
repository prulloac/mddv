package edu.usach.apicommons.dto;

import edu.usach.apicommons.extractor.ConnectionParams;

public class ConnectionParamsDTO extends ConnectionParams implements TokenizableDTO {
	@Override
	public String getSubject() {
		return "repository-connection";
	}
}
