package edu.usach.apicommons.model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class AbstractDeletableNamedDescriptableEntity extends AbstractDeletableNamedEntity implements DescriptableEntityInterface {

	private String descrition;

	@Override
	public String getDescription() {
		return descrition;
	}

	@Override
	public void setDescription(String description) {
		this.descrition = description;
	}

}
