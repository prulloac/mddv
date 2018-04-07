package edu.usach.apimain.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import edu.usach.apicommons.model.IEntity;
import edu.usach.apicommons.model.impl.AbstractDescriptableEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Role extends AbstractDescriptableEntity implements IEntity {

	@ManyToMany
	@JoinTable(
			name = "userRoles",
			joinColumns = {@JoinColumn(name = "roleId", referencedColumnName = "id")},
			inverseJoinColumns = {@JoinColumn(name = "userId", referencedColumnName = "id")}
	)
	@JsonIgnore
	private List<AppUser> appUserList;
	@ManyToMany
	@JoinTable(
			name = "organizationalUnitRoles",
			joinColumns = {@JoinColumn(name = "roleId", referencedColumnName = "id")},
			inverseJoinColumns = {@JoinColumn(name = "organizationalUnitId", referencedColumnName = "id")}
	)
	@JsonIgnore
	private List<OrganizationalUnit> organizationalUnitList;
	@ManyToMany(mappedBy = "accessRoles")
	@JsonIgnore
	private List<MetadataObject> metadataObjectList;

	public List<AppUser> getAppUserList() {
		return appUserList;
	}

	public void setAppUserList(List<AppUser> appUserList) {
		this.appUserList = appUserList;
	}

	public List<OrganizationalUnit> getOrganizationalUnitList() {
		return organizationalUnitList;
	}

	public void setOrganizationalUnitList(List<OrganizationalUnit> organizationalUnitList) {
		this.organizationalUnitList = organizationalUnitList;
	}

	public List<MetadataObject> getMetadataObjectList() {
		return metadataObjectList;
	}

	public void setMetadataObjectList(List<MetadataObject> metadataObjectList) {
		this.metadataObjectList = metadataObjectList;
	}
}
