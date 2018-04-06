package edu.usach.apicommons.model.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.usach.apicommons.model.AuditableEntityInterface;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class AbstractAuditableEntity extends AbstractIdentityEntity implements AuditableEntityInterface {

	@JsonIgnore
	private LocalDateTime modificationTimestamp;
	@Column(updatable = false)
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
