package edu.usach.apimain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.usach.apicommons.model.IEntity;
import edu.usach.apicommons.model.impl.AbstractAuditableNamedEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class Repository extends AbstractAuditableNamedEntity implements IEntity {
  private static final long serialVersionUID = 1L;

	private String location;
	private Boolean outsourced;
	private String type;
	private String engine;
	@OneToMany(mappedBy = "repository")
	@JsonIgnore
	private List<ConnectionParameter> connectionParameters;
	@ManyToMany(mappedBy = "linkedRepositories")
	@JsonIgnore
	private List<BusinessObject> businessObjectList;
	@OneToMany(mappedBy = "repository")
	@JsonIgnore
	private List<TechnicalObject> technicalObjectList;
	private String version;
}
