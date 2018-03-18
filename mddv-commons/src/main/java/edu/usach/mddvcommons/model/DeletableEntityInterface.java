package edu.usach.mddvcommons.model;

import java.time.LocalDateTime;

public interface DeletableEntityInterface {

	boolean isDeleted();
	void setDeleted(boolean deleted);
	LocalDateTime getDeletedTimestamp();
	void setDeletedTimestamp(LocalDateTime deletedTimestamp);

}
