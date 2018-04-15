package edu.usach.apimongo.dto;

import edu.usach.apicommons.extractor.ConnectionParams;

public class MongoConnectionParamsDTO extends ConnectionParams {
	private String authDatabase;

	public String getAuthDatabase() {
		return authDatabase;
	}

	public void setAuthDatabase(String authDatabase) {
		this.authDatabase = authDatabase;
	}
}
