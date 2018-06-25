package edu.usach.apicommons.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public abstract class AbstractAuditableEntity extends AbstractIdentityEntity implements IEntity {
  private static final long serialVersionUID = 1L;

	@JsonIgnore
	@Column(columnDefinition = "timestamp default current_timestamp", nullable = false)
	private LocalDateTime modificationTimestamp;
	@Column(columnDefinition = "timestamp default current_timestamp", updatable = false)
	@JsonIgnore
	private LocalDateTime creationTimestamp;

	public AbstractAuditableEntity() {
		this.creationTimestamp = LocalDateTime.now();
		this.modificationTimestamp = LocalDateTime.now();
	}

}
