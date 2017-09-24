package edu.usach.mddv.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("business")
public class BusinessObject extends MetadataObject {

    public BusinessObject(String name, String description, String version, String type) {
        super(name, description, version, type);
    }
}
