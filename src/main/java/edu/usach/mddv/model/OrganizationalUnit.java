package edu.usach.mddv.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class OrganizationalUnit extends AbstractDescriptable {

    @ManyToMany
    @JoinTable(name = "organizationalUnits_users",joinColumns = {@JoinColumn(name = "organizationalUnit_id")},inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private Set<User> users;

    @ManyToMany
    @JoinTable(name = "organizationalUnits_roles",joinColumns = {@JoinColumn(name = "organizationalUnit_id")},inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles;

    @ManyToOne
    private OrganizationalUnit belongsTo;

    public OrganizationalUnit(String name, String description) {
        super(name, description);
    }

    public OrganizationalUnit getBelongsTo() {
        return belongsTo;
    }

    public void setBelongsTo(OrganizationalUnit belongsTo) {
        this.belongsTo = belongsTo;
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

}
