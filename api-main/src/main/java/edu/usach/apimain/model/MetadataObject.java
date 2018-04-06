package edu.usach.apimain.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import edu.usach.apicommons.model.IEntity;
import edu.usach.apicommons.model.impl.AbstractAuditableDescriptableEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "objectType")
@Table(name = "metadataObjects")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class MetadataObject extends AbstractAuditableDescriptableEntity implements IEntity {
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

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Document> getDocumentList() {
		return documentList;
	}

	public void setDocumentList(List<Document> documentList) {
		this.documentList = documentList;
	}

	public List<MetadataObject> getLinkedObjects() {
		return linkedObjects;
	}

	public void setLinkedObjects(List<MetadataObject> linkedObjects) {
		this.linkedObjects = linkedObjects;
	}

	public List<Role> getAccessRoles() {
		return accessRoles;
	}

	public void setAccessRoles(List<Role> accessRoles) {
		this.accessRoles = accessRoles;
	}
}
