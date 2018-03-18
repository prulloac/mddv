package edu.usach.mddvcommons.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public interface IdentityEntityInterface extends Serializable {

	Long getId();
	void setId(Long id);

}
