package edu.usach.mddvcommons.model;

import javax.persistence.Column;

public interface NamedEntityInterface extends IdentityEntityInterface {

	String getName();
	void setName(String name);

}
