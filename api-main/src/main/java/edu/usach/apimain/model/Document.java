package edu.usach.apimain.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import edu.usach.apicommons.model.AbstractAuditableDescriptableEntity;
import edu.usach.apicommons.model.IEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Getter
@Setter
@ToString
public class Document extends AbstractAuditableDescriptableEntity implements IEntity {
    private static final long serialVersionUID = 1L;

    private String version;
    private String size;
    private String url;
    @ManyToOne
    @JoinColumn(name = "uploaderId")
    @JsonIgnore
    private AppUser uploader;
    @ManyToMany(mappedBy = "documentList")
    @JsonIgnore
    private List<MetadataObject> metadataObjectList;

}
