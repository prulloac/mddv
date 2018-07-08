package edu.usach.apimain.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import edu.usach.apicommons.model.AbstractDescriptableEntity;
import edu.usach.apicommons.model.IEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Getter
@Setter
@ToString
public class Role extends AbstractDescriptableEntity implements IEntity {
    private static final long serialVersionUID = 1L;

    @ManyToMany
    @JoinTable(
            name = "userRoles",
            joinColumns = {@JoinColumn(name = "roleId", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "userId", referencedColumnName = "id")}
    )
    @JsonIgnore
    private List<AppUser> appUserList;
    @ManyToMany
    @JoinTable(
            name = "organizationalUnitRoles",
            joinColumns = {@JoinColumn(name = "roleId", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "organizationalUnitId", referencedColumnName = "id")}
    )
    @JsonIgnore
    private List<OrganizationalUnit> organizationalUnitList;
    @ManyToMany(mappedBy = "accessRoles")
    @JsonIgnore
    private List<MetadataObject> metadataObjectList;
}
