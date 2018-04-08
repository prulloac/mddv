package edu.usach.apimain.model;

import edu.usach.apicommons.model.IEntity;
import edu.usach.apicommons.model.impl.AbstractNamedEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ConnectionParameter extends AbstractNamedEntity implements IEntity {
  private static final long serialVersionUID = 1L;

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
