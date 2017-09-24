package edu.usach.mddv.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "organizationalUnits")
public class OrganizationalUnit {

    @Id
    @GeneratedValue
    private long organizationalUnitId;

    private String name;

    private String description;

    @ManyToMany
    @JoinTable(
            name = "organizationalUnits_users",
            joinColumns = {@JoinColumn(name = "organizationalUnit_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private Set<User> users;

    @ManyToMany
    @JoinTable(
            name = "organizationalUnits_roles",
            joinColumns = {@JoinColumn(name = "organizationalUnit_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private Set<Role> roles;

    @ManyToOne
    @JoinColumn(name = "belongsTo")
    private OrganizationalUnit belongsTo;

    public OrganizationalUnit(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public long getOrganizationalUnitId() {
        return organizationalUnitId;
    }

    public void setOrganizationalUnitId(long organizationalUnitId) {
        this.organizationalUnitId = organizationalUnitId;
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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public OrganizationalUnit getBelongsTo() {
        return belongsTo;
    }

    public void setBelongsTo(OrganizationalUnit belongsTo) {
        this.belongsTo = belongsTo;
    }
}
