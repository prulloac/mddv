package edu.usach.apicommons.model.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.usach.apicommons.model.AuditableEntityInterface;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class AbstractAuditableEntity extends AbstractIdentityEntity implements AuditableEntityInterface {
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

	@Override
	public LocalDateTime getModificationTimestamp() {
		return modificationTimestamp;
	}

	@Override
	public void setModificationTimestamp(LocalDateTime modicationTimestamp) {
		this.modificationTimestamp = modicationTimestamp;
	}

	@Override
	public LocalDateTime getCreationTimestamp() {
		return creationTimestamp;
	}

	@Override
	public void setCreationTimestamp(LocalDateTime creationTimestamp) {
		this.creationTimestamp = creationTimestamp;
	}

}
