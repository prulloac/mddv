package edu.usach.mddv.model;

import javax.persistence.*;

@Entity
@DiscriminatorValue("technical")
public class TechnicalObjectMetadata extends AbstractObjectMetadata{

    @ManyToOne
    @JoinColumn(name = "parentObject")
    private TechnicalObjectMetadata parentObject;

    public TechnicalObjectMetadata() {
    }

    public TechnicalObjectMetadata getParentObject() {
        return parentObject;
    }

    public void setParentObject(TechnicalObjectMetadata parentObject) {
        this.parentObject = parentObject;
    }
}
