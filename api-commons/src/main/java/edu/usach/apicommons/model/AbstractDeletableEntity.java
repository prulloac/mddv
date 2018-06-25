package edu.usach.apicommons.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
	private Boolean deleted = false;
	@JsonIgnore
	private LocalDateTime deletedTimestamp;

}
