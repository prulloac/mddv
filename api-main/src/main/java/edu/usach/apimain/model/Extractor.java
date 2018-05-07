package edu.usach.apimain.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import edu.usach.apicommons.model.IEntity;
import edu.usach.apicommons.model.impl.AbstractDeletableNamedEntity;

import javax.persistence.Entity;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Extractor extends AbstractDeletableNamedEntity implements IEntity {

	private String supportedEngine;
	private String supportedVersions;
	private String apiUrl;

	public String getSupportedEngine() {
		return supportedEngine;
	}

	public void setSupportedEngine(String supportedEngine) {
		this.supportedEngine = supportedEngine;
	}

	public String getSupportedVersions() {
		return supportedVersions;
	}

	public void setSupportedVersions(String supportedVersions) {
		this.supportedVersions = supportedVersions;
	}

	public String getApiUrl() {
		return apiUrl;
	}

	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}

}
