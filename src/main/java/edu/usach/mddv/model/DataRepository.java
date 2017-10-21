package edu.usach.mddv.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "repositories")
public class DataRepository {

    @Id
    @GeneratedValue
    private long repositoryId;

    @Column(unique = true,nullable = false)
    private String name;

    private String location;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private boolean outSourced;

    @Column(nullable = false)
    private String version;

    @ManyToMany
    @JoinTable(
            name = "objectsRepositories",
            joinColumns = {@JoinColumn(name = "repositoryId")},
            inverseJoinColumns = {@JoinColumn(name = "objectId")}
    )
    private Set<AbstractObjectMetadata> objects;

    @ManyToMany
    @JoinTable(
            name = "repositoriesRoles",
            joinColumns = {@JoinColumn(name = "repositoryId")},
            inverseJoinColumns = {@JoinColumn(name = "roleId")}
    )
    private Set<Role> roles;

    @Column(columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
    private Timestamp creationTime;

    @OneToMany(mappedBy = "dataRepository",fetch = FetchType.EAGER,orphanRemoval = true)
    private List<ConnectionParam> connectionParams;

    public DataRepository() {
    }

    public long getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(long repositoryId) {
        this.repositoryId = repositoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isOutSourced() {
        return outSourced;
    }

    public void setOutSourced(boolean outSourced) {
        this.outSourced = outSourced;
    }

    public Set<AbstractObjectMetadata> getObjects() {
        return objects;
    }

    public void setObjects(Set<AbstractObjectMetadata> objects) {
        this.objects = objects;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }

    public List<ConnectionParam> getConnectionParams() {
        return connectionParams;
    }

    public void setConnectionParams(List<ConnectionParam> connectionParams) {
        this.connectionParams = connectionParams;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getConnectionParam(String param) {
        for (int i = 0; i < connectionParams.size(); i++) {
            if(connectionParams.get(i).getName().equals(param)){
                return connectionParams.get(i).getValue();
            }
        }
        return null;
    }
}
