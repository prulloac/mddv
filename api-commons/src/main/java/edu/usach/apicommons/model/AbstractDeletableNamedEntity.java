package edu.usach.apicommons.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
public abstract class AbstractDeletableNamedEntity extends AbstractDeletableEntity implements IEntity {
  private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private String name;

}
