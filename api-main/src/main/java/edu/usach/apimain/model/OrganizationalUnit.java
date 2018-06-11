package edu.usach.apimain.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import edu.usach.apicommons.model.IEntity;
import edu.usach.apicommons.model.impl.AbstractAuditableDescriptableEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Getter
@Setter
@ToString
public class OrganizationalUnit extends AbstractAuditableDescriptableEntity implements IEntity {
  private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "parentUnitId")
	@JsonIgnore
	private OrganizationalUnit parentUnit;

	@ManyToMany(mappedBy = "organizationalUnitList")
	@JsonIgnore
	private List<AppUser> appUserList;

	@ManyToMany(mappedBy = "organizationalUnitList")
	private List<Role> roleList;
}
