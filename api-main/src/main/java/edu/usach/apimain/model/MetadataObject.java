package edu.usach.apimain.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import edu.usach.apicommons.model.IEntity;
import edu.usach.apicommons.model.ISecureEntity;
import edu.usach.apicommons.model.AbstractAuditableDescriptableEntity;
import edu.usach.apicommons.model.AbstractNamedEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "objectType")
@Table(name = "metadataObjects")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Getter
@Setter
@ToString
public class MetadataObject extends AbstractAuditableDescriptableEntity implements IEntity, ISecureEntity {
  private static final long serialVersionUID = 1L;

  private String version;
	private String type;
	@ManyToMany
	@JoinTable(
			name = "documentsObjects",
			joinColumns = {@JoinColumn(name = "objectId", referencedColumnName = "id")},
			inverseJoinColumns = {@JoinColumn(name = "documentId", referencedColumnName = "id")}
	)
	@JsonIgnore
	private List<Document> documentList;
	@ManyToMany
	@JoinTable(
			name = "linkedObjects",
			joinColumns = {@JoinColumn(name = "objectA", referencedColumnName = "id")},
			inverseJoinColumns = {@JoinColumn(name = "documentB", referencedColumnName = "id")}
	)
	private List<MetadataObject> linkedObjects;
	@ManyToMany
	@JoinTable(
			name = "objectsRoles",
			joinColumns = {@JoinColumn(name = "objectId", referencedColumnName = "id")},
			inverseJoinColumns = {@JoinColumn(name = "roleId", referencedColumnName = "id")}
	)
	@JsonIgnore
	private List<Role> accessRoles;

	@Override
	public List<String> roleNames() {
		return this.accessRoles.stream().map(AbstractNamedEntity::getName).collect(Collectors.toList());
	}

}
