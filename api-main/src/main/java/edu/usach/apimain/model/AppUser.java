package edu.usach.apimain.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import edu.usach.apicommons.model.IEntity;
import edu.usach.apicommons.model.impl.AbstractIdentityEntity;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@ApiModel(parent = AbstractIdentityEntity.class)
public class AppUser extends AbstractIdentityEntity implements IEntity {

	private String firstName;
	private String lastName;
	@Column(nullable = false)
	@JsonIgnore
	private String password;
	@Column(unique = true, nullable = false)
	private String username;
	@Column(unique = true, nullable = false)
	private String email;
	@ManyToMany(mappedBy = "appUserList")
	private List<Role> roleList;
	@ManyToMany
	@JoinTable(
			name = "organizationalUnitUsers",
			joinColumns = {@JoinColumn(name = "userId", referencedColumnName = "id")},
			inverseJoinColumns = {@JoinColumn(name = "organizationalUnitId", referencedColumnName = "id")}
	)
	private List<OrganizationalUnit> organizationalUnitList;
	@OneToMany(mappedBy = "uploader")
	@JsonIgnore
	private List<Document> documentList;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public List<OrganizationalUnit> getOrganizationalUnitList() {
		return organizationalUnitList;
	}

	public void setOrganizationalUnitList(List<OrganizationalUnit> organizationalUnitList) {
		this.organizationalUnitList = organizationalUnitList;
	}

	public List<Document> getDocumentList() {
		return documentList;
	}

	public void setDocumentList(List<Document> documentList) {
		this.documentList = documentList;
	}
}
