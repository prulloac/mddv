package edu.usach.mddvcommons.model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractAuditableNamedDescriptableEntity extends AbstractAuditableNamedEntity implements DescriptableEntityInterface {

	private String description;

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

}
