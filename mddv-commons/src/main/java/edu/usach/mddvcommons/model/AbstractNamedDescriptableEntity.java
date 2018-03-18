package edu.usach.mddvcommons.model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractNamedDescriptableEntity extends AbstractIdentityEntity implements NamedEntityInterface, DescriptableEntityInterface {

	private String name;
	private String description;

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

}
