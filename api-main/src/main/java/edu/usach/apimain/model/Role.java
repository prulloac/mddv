package edu.usach.apimain.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import edu.usach.apicommons.model.AbstractDescriptableEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Role extends AbstractDescriptableEntity implements Serializable {

	private String roleName;
	@ManyToMany
	@JoinTable(
			name = "userRoles",
			joinColumns = {@JoinColumn(name = "roleId", referencedColumnName = "id")},
			inverseJoinColumns = {@JoinColumn(name = "userId", referencedColumnName = "id")}
	)
	private List<User> userList;
	@ManyToMany
	@JoinTable(
			name = "organizationalUnitRoles",
			joinColumns = {@JoinColumn(name = "roleId", referencedColumnName = "id")},
			inverseJoinColumns = {@JoinColumn(name = "organizationalUnitId", referencedColumnName = "id")}
	)
	private List<OrganizationalUnit> organizationalUnitList;
	@ManyToMany(mappedBy = "accessRoles")
	private List<AbstractMetadataObject> metadataObjectList;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public List<OrganizationalUnit> getOrganizationalUnitList() {
		return organizationalUnitList;
	}

	public void setOrganizationalUnitList(List<OrganizationalUnit> organizationalUnitList) {
		this.organizationalUnitList = organizationalUnitList;
	}

	public List<AbstractMetadataObject> getMetadataObjectList() {
		return metadataObjectList;
	}

	public void setMetadataObjectList(List<AbstractMetadataObject> metadataObjectList) {
		this.metadataObjectList = metadataObjectList;
	}
}
