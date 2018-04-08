package edu.usach.apicommons.model.impl;

import edu.usach.apicommons.model.DescriptableEntityInterface;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractDeletableDescriptableEntity extends AbstractDeletableNamedEntity implements DescriptableEntityInterface {
  private static final long serialVersionUID = 1L;

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
