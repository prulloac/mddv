package edu.usach.apimain.dto;

import edu.usach.apicommons.dto.TokenizableDTO;
import edu.usach.apimain.model.AppUser;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class UserTokenDataDTO implements TokenizableDTO {
  private static final long serialVersionUID = 1L;

	private String username;
	private String email;
	private String lastName;
	private String firstName;
	private Set<String> roles;
	private Set<String> organizationalUnits;

	public UserTokenDataDTO(AppUser entity) {
		this.roles = new HashSet<>();
		this.organizationalUnits = new HashSet<>();
		this.username = entity.getUsername();
		this.email = entity.getEmail();
		this.firstName = entity.getFirstName();
		this.lastName = entity.getLastName();
		this.roles.addAll(entity.getRoleList().stream().map(x -> x.getName()).collect(Collectors.toSet()));
		entity.getOrganizationalUnitList().stream().forEach(x -> this.roles.addAll(x.getRoleList().stream().map(y -> y.getName()).collect(Collectors.toSet())));
		this.organizationalUnits= entity.getOrganizationalUnitList().stream().map(x -> x.getName()).collect(Collectors.toSet());
	}
}
