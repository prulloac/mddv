package edu.usach.apimain.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import edu.usach.apicommons.model.AbstractAuditableNamedDescriptableEntity;
import edu.usach.apicommons.model.IEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class OrganizationalUnit extends AbstractAuditableNamedDescriptableEntity implements IEntity {

	@ManyToOne
	@JoinColumn(name = "parentUnitId")
	private OrganizationalUnit parentUnit;

	@ManyToMany(mappedBy = "organizationalUnitList")
	private List<User> userList;

	@ManyToMany(mappedBy = "organizationalUnitList")
	private List<Role> roleList;

}
