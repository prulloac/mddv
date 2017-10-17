package edu.usach.mddv.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role{

    @Id
    @GeneratedValue
    private long roleId;

    @Column(unique = true, nullable = false)
    private String name;

    private String description;

    @ManyToMany(mappedBy = "roles")
    private Set<AbstractObjectMetadata> accesibles;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    @ManyToMany(mappedBy = "roles")
    private Set<DataRepository> repositories;

    @Column(columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
    private Timestamp creationTime;

    public Role() {
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
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

    public Set<AbstractObjectMetadata> getAccesibles() {
        return accesibles;
    }

    public void setAccesibles(Set<AbstractObjectMetadata> accesibles) {
        this.accesibles = accesibles;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<DataRepository> getRepositories() {
        return repositories;
    }

    public void setRepositories(Set<DataRepository> repositories) {
        this.repositories = repositories;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }
}
