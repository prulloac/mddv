package edu.usach.mddv.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Inheritance
public abstract class MetadataObject extends AbstractVersionable {
    private String type;

    @OneToMany(mappedBy = "relatedObject")
    private Set<UploadFile> documents;

    @ManyToMany
    @JoinTable(name = "objectLinks",joinColumns = {@JoinColumn(name = "object1_id")},inverseJoinColumns = {@JoinColumn(name = "object2_id")})
    private Set<MetadataObject> linkedObjects;

    @ManyToMany
    @JoinTable(name = "objects_roles",joinColumns = {@JoinColumn(name = "object_id")},inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> accessRoles;

    public MetadataObject(String name, String description, String version, String type) {
        super(name, description, version);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<UploadFile> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<UploadFile> documents) {
        this.documents = documents;
    }

    public Set<MetadataObject> getLinkedObjects() {
        return linkedObjects;
    }

    public void setLinkedObjects(Set<MetadataObject> linkedObjects) {
        this.linkedObjects = linkedObjects;
    }

    public Set<Role> getAccessRoles() {
        return accessRoles;
    }

    public void setAccessRoles(Set<Role> accessRoles) {
        this.accessRoles = accessRoles;
    }

}
