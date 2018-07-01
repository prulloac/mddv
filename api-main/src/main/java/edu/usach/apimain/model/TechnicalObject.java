package edu.usach.apimain.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import edu.usach.apicommons.model.IEntity;
import edu.usach.apicommons.model.ISecureEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue(value = "techincalObject")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Getter
@Setter
public class TechnicalObject extends MetadataObject implements IEntity, ISecureEntity {
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "parentObjectId")
    private TechnicalObject parentObject;
    @ManyToOne
    @JoinColumn(name = "repositoryId")
    @JsonIgnore
    private Repository repository;
    @OneToMany(mappedBy = "parentObject")
    @JsonIgnore
    private List<TechnicalObject> childrenObjects;
}
