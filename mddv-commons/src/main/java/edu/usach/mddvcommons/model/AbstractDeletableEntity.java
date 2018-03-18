package edu.usach.mddvcommons.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class AbstractDeletableEntity extends AbstractAuditableEntity implements DeletableEntityInterface {

	private Boolean deleted;
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
