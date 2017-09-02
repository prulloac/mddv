package edu.usach.mddv.model;

import javax.persistence.*;

@Entity
@Inheritance
public abstract class TechnicalObject extends MetadataObject{
    public TechnicalObject(String name, String description, String version, String type) {
        super(name, description, version, type);
    }

    @ManyToOne
    private Structure parentObject;

    public Structure getParentObject() {
        return parentObject;
    }

    public void setParentObject(Structure parentObject) {
        this.parentObject = parentObject;
    }
}
