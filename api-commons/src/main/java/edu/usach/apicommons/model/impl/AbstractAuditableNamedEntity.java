package edu.usach.apicommons.model.impl;

import edu.usach.apicommons.model.IEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
public abstract class AbstractAuditableNamedEntity extends AbstractAuditableEntity implements IEntity {
  private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private String name;

}
