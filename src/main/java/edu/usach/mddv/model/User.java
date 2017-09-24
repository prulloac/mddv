package edu.usach.mddv.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private long id;
    private String username;
    private String name;
    private String password;
    private String mail;
    private boolean enabled;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private Set<Role> roles;

    @OneToMany(mappedBy = "author",orphanRemoval = true)
    private Set<UploadFile> userUploads;

    @ManyToMany(mappedBy = "users")
    private Set<OrganizationalUnit> organizationalUnitSet;

    public User(String username, String name, String password, String mail) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.mail = mail;
        this.enabled = true;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<UploadFile> getUserUploads() {
        return userUploads;
    }

    public void setUserUploads(Set<UploadFile> userUploads) {
        this.userUploads = userUploads;
    }

    public Set<OrganizationalUnit> getOrganizationalUnitSet() {
        return organizationalUnitSet;
    }

    public void setOrganizationalUnitSet(Set<OrganizationalUnit> organizationalUnitSet) {
        this.organizationalUnitSet = organizationalUnitSet;
    }
}
