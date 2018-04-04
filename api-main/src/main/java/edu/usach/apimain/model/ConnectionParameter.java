package edu.usach.apimain.model;

import edu.usach.apicommons.model.AbstractNamedEntity;
import edu.usach.apicommons.model.IEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
public class ConnectionParameter extends AbstractNamedEntity implements IEntity {

	private String value;
	@ManyToOne
	@JoinColumn(name = "repositoryId")
	private Repository repository;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Repository getRepository() {
		return repository;
	}

	public void setRepository(Repository repository) {
		this.repository = repository;
	}
}
