package edu.usach.apicommons.model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractDeletableDescriptableEntity extends AbstractDeletableEntity implements DescriptableEntityInterface {

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
