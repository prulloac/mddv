package edu.usach.apimain.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import edu.usach.apicommons.model.AbstractIdentityEntity;
import edu.usach.apicommons.model.IEntity;
import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@ApiModel(parent = AbstractIdentityEntity.class)
@Getter
@Setter
@ToString
public class AppUser extends AbstractIdentityEntity implements IEntity {
  private static final long serialVersionUID = 1L;

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

}
