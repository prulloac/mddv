package edu.usach.mddv.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Role extends AbstractDescriptable{

    @ManyToMany(mappedBy = "accessRoles")
    private Set<MetadataObject> accesibles;

    @ManyToMany(mappedBy = "roles")
    private Set<User> userSet;

    @ManyToMany(mappedBy = "roles")
    private Set<OrganizationalUnit> organizationalUnitSet;

    public Role(String name, String description) {
        super(name, description);
    }

}
