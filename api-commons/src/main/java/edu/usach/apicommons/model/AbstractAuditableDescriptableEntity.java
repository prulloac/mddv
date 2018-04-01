package edu.usach.apicommons.model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractAuditableDescriptableEntity extends AbstractIdentityEntity implements DescriptableEntityInterface {

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
