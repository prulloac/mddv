package edu.usach.apimain.model;

import edu.usach.apicommons.model.AbstractAuditableNamedEntity;
import edu.usach.apicommons.model.IEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Entity
public class Repository extends AbstractAuditableNamedEntity implements IEntity {

	private String location;
	private Boolean outsourced;
	private String type;
	@OneToMany(mappedBy = "repository")
	private List<ConnectionParameter> connectionParameters;
	@ManyToMany(mappedBy = "linkedRepositories")
	private List<BusinessObject> businessObjectList;
	@OneToMany(mappedBy = "repository")
	private List<TechnicalObject> technicalObjectList;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Boolean getOutsourced() {
		return outsourced;
	}

	public void setOutsourced(Boolean outsourced) {
		this.outsourced = outsourced;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<ConnectionParameter> getConnectionParameters() {
		return connectionParameters;
	}

	public void setConnectionParameters(List<ConnectionParameter> connectionParameters) {
		this.connectionParameters = connectionParameters;
	}

	public List<BusinessObject> getBusinessObjectList() {
		return businessObjectList;
	}

	public void setBusinessObjectList(List<BusinessObject> businessObjectList) {
		this.businessObjectList = businessObjectList;
	}

	public List<TechnicalObject> getTechnicalObjectList() {
		return technicalObjectList;
	}

	public void setTechnicalObjectList(List<TechnicalObject> technicalObjectList) {
		this.technicalObjectList = technicalObjectList;
	}
}
