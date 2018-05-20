package edu.usach.apicommons.model;

import java.util.List;

public interface ISecureEntity extends IEntity {
	List<String> roleNames();
}
