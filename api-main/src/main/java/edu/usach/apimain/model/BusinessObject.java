package edu.usach.apimain.model;

import edu.usach.apicommons.model.IEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@DiscriminatorValue(value = "businessObject")
public class BusinessObject extends AbstractMetadataObject implements IEntity {

	@ManyToMany
	@JoinTable(
			name = "businessObjectsRepositories",
			joinColumns = {@JoinColumn(name = "businessObjectId", referencedColumnName = "id")},
			inverseJoinColumns = {@JoinColumn(name = "repositoryId", referencedColumnName = "id")}
	)
	private List<Repository> linkedRepositories;

	public List<Repository> getLinkedRepositories() {
		return linkedRepositories;
	}

	public void setLinkedRepositories(List<Repository> linkedRepositories) {
		this.linkedRepositories = linkedRepositories;
	}
}
