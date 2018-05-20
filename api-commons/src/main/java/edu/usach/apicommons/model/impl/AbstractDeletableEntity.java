package edu.usach.apicommons.model.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.usach.apicommons.model.IEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public abstract class AbstractDeletableEntity extends AbstractAuditableEntity implements IEntity {
  private static final long serialVersionUID = 1L;

	@JsonIgnore
	private boolean deleted;
	@JsonIgnore
	private LocalDateTime deletedTimestamp;

	public AbstractDeletableEntity() {
		this.deleted = false;
	}

}
