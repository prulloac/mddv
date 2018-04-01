package edu.usach.apicommons.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public interface AuditableEntityInterface extends Serializable {

		LocalDateTime getModificationTimestamp();
		void setModificationTimestamp(LocalDateTime modicationTimestamp);
		LocalDateTime getCreationTimestamp();
		void setCreationTimestamp(LocalDateTime creationTimestamp);

}
