package edu.usach.apimain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.usach.apicommons.model.IEntity;
import edu.usach.apicommons.model.ISecureEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue(value = "businessObject")
public class BusinessObject extends MetadataObject implements IEntity, ISecureEntity {
  private static final long serialVersionUID = 1L;

	@ManyToMany
	@JoinTable(
			name = "businessObjectsRepositories",
			joinColumns = {@JoinColumn(name = "businessObjectId", referencedColumnName = "id")},
			inverseJoinColumns = {@JoinColumn(name = "repositoryId", referencedColumnName = "id")}
	)
	@JsonIgnore
	private List<Repository> linkedRepositories;

	public List<Repository> getLinkedRepositories() {
		return linkedRepositories;
	}

	public void setLinkedRepositories(List<Repository> linkedRepositories) {
		this.linkedRepositories = linkedRepositories;
	}
}
