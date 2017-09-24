package edu.usach.mddv.model;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "objects")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "META_TYPE")
public abstract class MetadataObject {

    @Id
    @GeneratedValue
    private long objectId;

    private String name;

    private String description;

    private String version;

    private String type;

    @ManyToMany(mappedBy = "relatedObject")
    private Set<UploadFile> uploadFiles;

    @ManyToMany
    @JoinTable(
            name = "linkedObjects",
            joinColumns = {@JoinColumn(name = "objectA_id")},
            inverseJoinColumns = {@JoinColumn(name = "objectB_id")}
    )
    private Set<MetadataObject> linkedObjects;

    @ManyToMany
    @JoinTable(
            name = "objects_roles",
            joinColumns = {@JoinColumn(name = "object_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private Set<Role> accessRoles;

    public MetadataObject(String name, String description, String version, String type) {
        this.name = name;
        this.description = description;
        this.version = version;
        this.type = type;
    }

    public long getObjectId() {
        return objectId;
    }

    public void setObjectId(long objectId) {
        this.objectId = objectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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

    public Set<UploadFile> getUploadFiles() {
        return uploadFiles;
    }

    public void setUploadFiles(Set<UploadFile> uploadFiles) {
        this.uploadFiles = uploadFiles;
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
