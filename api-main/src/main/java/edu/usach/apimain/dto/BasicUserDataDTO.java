package edu.usach.apimain.dto;

import edu.usach.apicommons.dto.AbstractDTO;
import edu.usach.apicommons.dto.IDTO;
import edu.usach.apimain.model.User;


public class BasicUserDataDTO extends AbstractDTO<User> implements IDTO {

	private String username;
	private String email;
	private String lastName;
	private String firstName;

	BasicUserDataDTO(User entity) {
		this.username = entity.getUsername();
		this.email = entity.getEmail();
		this.firstName = entity.getFirstName();
		this.lastName = entity.getLastName();
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
}
