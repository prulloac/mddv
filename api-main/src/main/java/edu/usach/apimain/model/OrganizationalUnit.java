package edu.usach.apimain.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import edu.usach.apicommons.model.IEntity;
import edu.usach.apicommons.model.impl.AbstractAuditableDescriptableEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class OrganizationalUnit extends AbstractAuditableDescriptableEntity implements IEntity {

	@ManyToOne
	@JoinColumn(name = "parentUnitId")
	@JsonIgnore
	private OrganizationalUnit parentUnit;

	@ManyToMany(mappedBy = "organizationalUnitList")
	@JsonIgnore
	private List<User> userList;

	@ManyToMany(mappedBy = "organizationalUnitList")
	private List<Role> roleList;

	public OrganizationalUnit getParentUnit() {
		return parentUnit;
	}

	public void setParentUnit(OrganizationalUnit parentUnit) {
		this.parentUnit = parentUnit;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
}