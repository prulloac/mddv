package edu.usach.apimain.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
@DiscriminatorValue(value = "techincalObject")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class TechnicalObject extends AbstractMetadataObject implements Serializable {
	@ManyToOne
	@JoinColumn(name = "parentObjectId")
	private TechnicalObject parentObject;
	@ManyToOne
	@JoinColumn(name = "repositoryId")
	private Repository repository;

	public TechnicalObject getParentObject() {
		return parentObject;
	}

	public void setParentObject(TechnicalObject parentObject) {
		this.parentObject = parentObject;
	}

	public Repository getRepository() {
		return repository;
	}

	public void setRepository(Repository repository) {
		this.repository = repository;
	}
}
