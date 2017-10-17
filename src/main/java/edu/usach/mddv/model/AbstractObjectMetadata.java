package edu.usach.mddv.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "objects")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "objectType")
public abstract class AbstractObjectMetadata {

    @Id
    @GeneratedValue
    private long objectId;

    @Column(columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP",nullable = false)
    private Timestamp creationTime;

    @ManyToMany
    @JoinTable(
            name = "relations",
            joinColumns = {@JoinColumn(name = "objectA")},
            inverseJoinColumns = {@JoinColumn(name = "objectB")}
    )
    private Set<AbstractObjectMetadata> relatedTo;

    @OneToMany(mappedBy = "objectMetadata")
    private Set<ObjectInstance> instances;

    @ManyToMany
    @JoinTable(
            name = "objectsRoles",
            joinColumns = {@JoinColumn(name = "objectId")},
            inverseJoinColumns = {@JoinColumn(name = "roleId")}
    )
    private Set<Role> roles;

    @ManyToMany(mappedBy = "objects")
    private Set<DataRepository> repositories;

    public AbstractObjectMetadata() {
    }

    public long getObjectId() {
        return objectId;
    }

    public void setObjectId(long objectId) {
        this.objectId = objectId;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }

    public Set<AbstractObjectMetadata> getRelatedTo() {
        return relatedTo;
    }

    public void setRelatedTo(Set<AbstractObjectMetadata> relatedTo) {
        this.relatedTo = relatedTo;
    }

    public Set<ObjectInstance> getInstances() {
        return instances;
    }

    public void setInstances(Set<ObjectInstance> instances) {
        this.instances = instances;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<DataRepository> getRepositories() {
        return repositories;
    }

    public void setRepositories(Set<DataRepository> repositories) {
        this.repositories = repositories;
    }
}
