package edu.usach.apimain.dto;

import edu.usach.apicommons.dto.TokenizableDTO;
import edu.usach.apimain.model.AppUser;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


public class UserTokenDataDTO implements TokenizableDTO {

	private String username;
	private String email;
	private String lastName;
	private String firstName;
	private Set<String> roles;
	private Set<String> organizationalUnits;
	private String subject;

	public UserTokenDataDTO(AppUser entity) {
		this.roles = new HashSet<>();
		this.organizationalUnits = new HashSet<>();
		this.subject = "userdata";
		this.username = entity.getUsername();
		this.email = entity.getEmail();
		this.firstName = entity.getFirstName();
		this.lastName = entity.getLastName();
		this.roles.addAll(entity.getRoleList().stream().map(x -> x.getName()).collect(Collectors.toSet()));
		entity.getOrganizationalUnitList().stream().forEach(x -> this.roles.addAll(x.getRoleList().stream().map(y -> y.getName()).collect(Collectors.toSet())));
		this.organizationalUnits= entity.getOrganizationalUnitList().stream().map(x -> x.getName()).collect(Collectors.toSet());
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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	public Set<String> getOrganizationalUnits() {
		return organizationalUnits;
	}

	public void setOrganizationalUnits(Set<String> organizationalUnits) {
		this.organizationalUnits = organizationalUnits;
	}

	@Override
	public String getSubject() {
		return subject;
	}
}
