package edu.usach.apimain.model;

import edu.usach.apicommons.model.IEntity;
import edu.usach.apicommons.model.impl.AbstractNamedEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@ToString
public class ConnectionParameter extends AbstractNamedEntity implements IEntity {
  private static final long serialVersionUID = 1L;

	private String value;
	@ManyToOne
	@JoinColumn(name = "repositoryId")
	private Repository repository;

}
