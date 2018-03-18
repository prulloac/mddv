package edu.usach.mddvcommons.model;

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
