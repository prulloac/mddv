package edu.usach.apicommons.model;

import java.time.LocalDateTime;

public interface DeletableEntityInterface extends AuditableEntityInterface{

	boolean isDeleted();
	void setDeleted(boolean deleted);
	LocalDateTime getDeletedTimestamp();
	void setDeletedTimestamp(LocalDateTime deletedTimestamp);

}
