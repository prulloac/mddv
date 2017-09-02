package edu.usach.mddv.model;

import javax.persistence.Entity;

@Entity
public class Attribute extends TechnicalObject {
    public Attribute(String name, String description, String version, String type) {
        super(name, description, version, type);
    }
}
