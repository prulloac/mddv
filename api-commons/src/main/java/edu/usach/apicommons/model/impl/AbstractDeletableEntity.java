package edu.usach.apicommons.model.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.usach.apicommons.model.DeletableEntityInterface;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class AbstractDeletableEntity extends AbstractAuditableEntity implements DeletableEntityInterface {
  private static final long serialVersionUID = 1L;

	@JsonIgnore
	private Boolean deleted;
	@JsonIgnore
	private LocalDateTime deletedTimestamp;

	public AbstractDeletableEntity() {
		this.deleted = false;
	}

	@Override
	public boolean isDeleted() {
		return deleted;
	}

	@Override
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	@Override
	public LocalDateTime getDeletedTimestamp() {
		return deletedTimestamp;
	}

	@Override
	public void setDeletedTimestamp(LocalDateTime deletedTimestamp) {
		this.deletedTimestamp = deletedTimestamp;
	}

}
