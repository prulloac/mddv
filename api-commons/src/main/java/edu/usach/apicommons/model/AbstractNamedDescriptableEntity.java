package edu.usach.apicommons.model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractNamedDescriptableEntity extends AbstractNamedEntity implements DescriptableEntityInterface {

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
