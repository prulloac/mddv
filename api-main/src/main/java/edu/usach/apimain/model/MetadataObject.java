package edu.usach.apimain.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import edu.usach.apicommons.model.IEntity;
import edu.usach.apicommons.model.ISecureEntity;
import edu.usach.apicommons.model.AbstractAuditableDescriptableEntity;
import edu.usach.apicommons.model.AbstractNamedEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "objectType")
@Table(name = "metadataObjects")
@Getter
@Setter
@ToString
public class MetadataObject extends AbstractAuditableDescriptableEntity implements IEntity, ISecureEntity {
    private static final long serialVersionUID = 1L;

    private String version;
    private String type;
    @ManyToMany
    @JoinTable(
            name = "documentsObjects",
            joinColumns = {@JoinColumn(name = "objectId", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "documentId", referencedColumnName = "id")}
    )
    @JsonIgnore
    private List<Document> documentList;

    @ManyToMany
    @JoinTable(
            name = "linkedObjects",
            joinColumns = {@JoinColumn(name = "objectA", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "documentB", referencedColumnName = "id")}
    )
    @JsonIgnore
    private Set<MetadataObject> linkedObjects;

    @ManyToMany(mappedBy = "linkedObjects")
    @JsonIgnore
    private Set<MetadataObject> backLinkedObjects;

    @ManyToMany
    @JoinTable(
            name = "objectsRoles",
            joinColumns = {@JoinColumn(name = "objectId", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "roleId", referencedColumnName = "id")}
    )
    @JsonIgnore
    private List<Role> accessRoles;

    @Transient
    public Set<Long> getLinkedTo() {
        Set<Long> linkedTo = new HashSet<>();
        if (null != this.linkedObjects)
            linkedTo.addAll(this.linkedObjects.stream().map(MetadataObject::getId).collect(Collectors.toSet()));
        if (null != this.backLinkedObjects)
            linkedTo.addAll(this.backLinkedObjects.stream().map(MetadataObject::getId).collect(Collectors.toSet()));
        return linkedTo;
    }

    public void addLinkedObject(MetadataObject metadataObject) {
        if (null == linkedObjects)
            linkedObjects = new HashSet<>();
        linkedObjects.add(metadataObject);
    }

    @Override
    public List<String> roleNames() {
        return this.accessRoles.stream().map(AbstractNamedEntity::getName).collect(Collectors.toList());
    }

}
