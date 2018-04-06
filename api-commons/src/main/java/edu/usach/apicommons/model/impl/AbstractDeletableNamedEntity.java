package edu.usach.apicommons.model.impl;

import edu.usach.apicommons.model.NamedEntityInterface;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractDeletableNamedEntity extends AbstractDeletableEntity implements NamedEntityInterface {

	@Column(nullable = false, unique = true)
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
