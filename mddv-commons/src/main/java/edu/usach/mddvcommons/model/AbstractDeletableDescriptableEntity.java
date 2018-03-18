package edu.usach.mddvcommons.model;

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
