package edu.usach.apicommons.model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractAuditableNamedEntity extends AbstractAuditableEntity implements NamedEntityInterface {

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
