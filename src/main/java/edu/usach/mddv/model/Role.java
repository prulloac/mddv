package edu.usach.mddv.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role{

    @Id
    @GeneratedValue
    private long roleId;
    private String name;
    private String description;

    @ManyToMany(mappedBy = "accessRoles")
    private Set<MetadataObject> accesibles;

    @ManyToMany(mappedBy = "roles")
    private Set<User> userSet;

    @ManyToMany(mappedBy = "roles")
    private Set<OrganizationalUnit> organizationalUnitSet;

    public Role(String name, String description) {
        this.name = name;
        this.description = description;
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

    public Set<MetadataObject> getAccesibles() {
        return accesibles;
    }

    public void setAccesibles(Set<MetadataObject> accesibles) {
        this.accesibles = accesibles;
    }

    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }

    public Set<OrganizationalUnit> getOrganizationalUnitSet() {
        return organizationalUnitSet;
    }

    public void setOrganizationalUnitSet(Set<OrganizationalUnit> organizationalUnitSet) {
        this.organizationalUnitSet = organizationalUnitSet;
    }
}
