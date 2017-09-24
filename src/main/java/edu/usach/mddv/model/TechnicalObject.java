package edu.usach.mddv.model;

import javax.persistence.*;

@Entity
@DiscriminatorValue("technical")
public class TechnicalObject extends MetadataObject{

    @ManyToOne
    @JoinColumn(name = "parentObject")
    private TechnicalObject parentObject;

    public TechnicalObject(String name, String description, String version, String type) {
        super(name, description, version, type);
    }

    public TechnicalObject getParentObject() {
        return parentObject;
    }

    public void setParentObject(TechnicalObject parentObject) {
        this.parentObject = parentObject;
    }
}
