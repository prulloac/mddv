package edu.usach.mddvcommons.model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class AbstractDeletableNamedEntity extends AbstractDeletableEntity implements NamedEntityInterface{

	private String name;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

}
