package edu.usach.apicommons.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
public abstract class AbstractDeletableDescriptableEntity extends AbstractDeletableNamedEntity implements IEntity {
  private static final long serialVersionUID = 1L;

	private String description;

}
